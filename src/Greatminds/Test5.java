package Greatminds;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
 
public class Test5 {
	static Db Mdb=new Db();
    static MongoDatabase db = Mdb.mongo();
    static WebDriver driver=new FirefoxDriver();
    static JavascriptExecutor js = (JavascriptExecutor) driver; 
    public static void main(String[] args) throws InterruptedException, ParseException {
	  
       
       //TimeZone.setDefault(TimeZone.getTimeZone("IST"));
       
       String ClickedJobID="";
       
       Js js =new Js();
        try {
        	driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            //String URL="https://greatmindsbynderdemo.mpstechnologies.com/jobcardview";
            String URL="http://localhost:8080/jobcardview";
            driver.get(URL);
        }catch(Exception e) {
        	System.out.println("Error in==>"+ e);
        }
        Thread.sleep(1000);
        Integer tempItrator=0;
        Select workflow = new Select(driver.findElement(By.id("formGroupExampleInput2")));
        //workflow.selectByValue("Permission");
        //workflow.selectByValue("Created Image");
        workflow.selectByValue("Shutterstock"); // ++
        //workflow.selectByValue("Clip Art"); //++
        
        Select jobType = new Select(driver.findElement(By.id("jobstage")));
        //jobType.selectByValue("05dedb54-4418-4ea7-89c6-18ef1d188bd5"); //Public domain
        //jobType.selectByValue("65e0e914-a3e3-4cad-b069-37b87b8468ca"); //Creative commonts 
         //jobType.selectByValue("ebd040f1-9eeb-4b83-b651-1bb44588d2ef"); // Text Quote
         //jobType.selectByValue("1689aacf-9c63-42dd-8735-b8f6000e734d");  //Stock Image
        //jobType.selectByValue("fc256dbb-0f19-4970-9c59-b177ff5ad3f6");  // Licensed Text
        //jobType.selectByValue("03600acb-4c72-4a68-ab50-aecd29a32266");  //  Licensed Image //dd
        //jobType.selectByValue("9a0e89f0-4cd3-4cea-b98c-b33d1523c598");  //Fine Art
        //jobType.selectByValue("45a2d52b-3b5f-4743-b3b8-d201ef966ce9");  //Link
        //jobType.selectByValue("448a7d85-d932-4070-80cd-6dd80dd143eb");  //Clip Art //0 jobs 
        //jobType.selectByValue("bfe9c569-c484-4a06-815f-8973d586e3f8");  // Fair Use
        //jobType.selectByValue("Unallocated");  // Unallocated
        
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(20000);
        WebElement tbody = driver.findElement(By.cssSelector(".table > tbody"));
        //Thread.sleep(3000);
        System.out.println("finding at tbody");
        List<WebElement> tr= tbody.findElements(By.tagName("tr"));
        System.out.println("total tr is :"+ tr.size());
        
        for(WebElement name : tr) {
        	List<WebElement> th= name.findElements(By.tagName("td"));
        	//System.out.println("th size :"+th.size());
        	System.out.println("0:"+th.get(0).getText() + "1:"+th.get(1).getText() +"2:"+th.get(2).getText() +"3:"+th.get(3).getText() );
        	System.out.println("test is:"+ th.get(3).getText().trim()+ "FFFF==>>"+ th.get(3).getText().trim().equalsIgnoreCase(""));
        	//
        	//if(th.get(3).getText().trim().equalsIgnoreCase("")) {
    		Thread.sleep(2000); 
    		ClickedJobID=th.get(2).findElement(By.tagName("a")).getAttribute("href").split("view/")[1];
    		th.get(2).findElement(By.tagName("a")).click();
			Thread.sleep(5000); 
			String mainWindow=driver.getWindowHandle();
			// It returns no. of windows opened by WebDriver and will return Set of Strings
			Set<String> set =driver.getWindowHandles();
			Iterator<String> itr= set.iterator();
			while(itr.hasNext()){
				String childWindow=itr.next();
			   	// Compare whether the main windows is not equal to child window. If not equal, we will close.
				if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
				if(tempItrator == 0) {
					Thread.sleep(3000);
					driver.findElement(By.id("inputEmail")).sendKeys("praveen.nair@adi-mps.com");
					driver.findElement(By.id("inputPassword")).sendKeys("gm@1234");
					driver.findElement(By.className("login-btn")).click();
					Thread.sleep(2000);
				}
				tempItrator++;
				js.scrollTop( driver);

				 List <WebElement> elements =driver.findElements(By.className("sort"));
				 for( Integer i=0; i< elements.size(); i++) {
					// System.out.println("source >>>"+ elements.get(i).getText() );
					 if(i==1) {
						 elements.get(i).click();
						 //Thread.sleep(2000);
						// js.readProgeress(driver);
						 WebElement followingSibling = elements.get(i).findElement(By.xpath("following-sibling::*"));
						 if(checkElement( followingSibling, "Show activities about Progress")) {
							 followingSibling.findElement(By.linkText("Show activities about Progress")).click();
						 }else {
							 elements.get(i).click();
							 followingSibling = elements.get(i).findElement(By.xpath("following-sibling::*"));
							 followingSibling.findElement(By.linkText("Show activities about Progress")).click();
						 }
						 WebElement UL= driver.findElement(By.className("activity-list"));
						 Thread.sleep(3000);
						 
						 List <WebElement> LIs = UL.findElements(By.className("activity"));
						 System.out.println("Total Lis is: "+ LIs.size() );
						 String jobKey= driver.findElement(By.className("job-link")).getText();
					
						 if(LIs.size() >0 ) {
							 WebElement activityTitle1=LIs.get(0).findElement(By.className("activity-title"));
							 List <WebElement> atags1= activityTitle1.findElements(By.tagName("a"));
							 String  jobID=atags1.get(0).getAttribute("data-jobid");
							 String job_date_finished="";
							 BasicDBObject whereQuery = new BasicDBObject();
							 whereQuery.put("jobID", jobID);
							 //js code
							// driver.navigate().to(driver.getCurrentUrl());
							  // ((JavascriptExecutor) driver).executeScript("while($(\".timeline-left\").is(\":hidden\")){ $(\".timeline-left\").click(); }");
							 List<Document> employees = (List<Document>) db.getCollection("bynder_jobs").find(whereQuery).into(new ArrayList<Document>());
							 
							 BasicDBObject whereQuery2 = new BasicDBObject();
							 whereQuery2.put("ID", employees.get(0).get("presetID"));
							 
							 List<Document> job_presets = (List<Document>) db.getCollection("job_presets").find(whereQuery2).into(new ArrayList<Document>());
							 List<Document> presetstages = (List<Document>) job_presets.get(0).get("presetstages");
							 String FirstStageName= (String)presetstages.get(0).get("name");
							 Document jobMetaproperties= ( Document ) employees.get(0).get("jobMetaproperties");
							 String metaDate = (String) jobMetaproperties.get("e9074f5b472f41d4a92ac511e53da775");
							 WebElement activityTitlef=LIs.get( LIs.size()-1 ).findElement(By.tagName("time"));
							 String FirstFinished= activityTitlef.getAttribute("data-created");
							 System.out.println("first stage name : "+ FirstStageName + " and metaDate :"+ metaDate + ": and FirstFinished :"+ FirstFinished);
							 
							 if(FirstStageName.trim().equalsIgnoreCase("Complete Request Form")){
								 Date dateCreated= new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( FirstFinished );
								 updateDateCreated( jobID , dateCreated); 
							 }else {
								 Date dateCreated= new SimpleDateFormat("yyyy-MM-dd").parse(metaDate);
								 List <String> firstStageInfo=  getFirstStageInfo(driver);
								 System.out.println( firstStageInfo );
								 updateData(  whereQuery, "", "", jobID, "", firstStageInfo.get(1), firstStageInfo.get(0), "", dateCreated, FirstFinished);
							 }
							
							 Integer LoopItrator=0; 
							 String activityTitle= LIs.get(0).findElement(By.className("activity-title")).getText();
							 if( activityTitle.indexOf("finished") >-1 ) {
								 LoopItrator=1;
							 }
							 for(Integer temp= (LIs.size()-1); temp >= LoopItrator; temp --) {
								  getInfoFromLI( LIs, temp , whereQuery);
							 }
						 // update NoSql query
						 if(activityTitle.indexOf("finished") >-1 ) {
							 job_date_finished= LIs.get(0 ).findElement(By.tagName("time")).getText();
							 Date job_dateFinished=new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse(job_date_finished);
						     MongoCollection<Document> collection = db.getCollection("bynder_jobs");
						     collection.updateMany( Filters.eq("jobID", jobID ),
							     Updates.combine(Updates.set("job_key", jobKey),
							     Updates.set("job_date_finished",  job_dateFinished ),
							     Updates.set("isUpdated",  true )
							 ));
						 }else {
							 MongoCollection<Document> collection = db.getCollection("bynder_jobs");
						     collection.updateMany( Filters.eq("jobID", jobID ),
							     Updates.combine(Updates.set("job_key", jobKey),
							     Updates.set("isUpdated",  true )
							 ));
						 }
					 }else {
						System.out.println("log not found");
						/// we have need to set job_key in this case//
						MongoCollection<Document> collection = db.getCollection("bynder_jobs");
					     collection.updateMany( Filters.eq("jobID", ClickedJobID ),
						     Updates.combine(Updates.set("job_key", jobKey)
						 ));
					 }
					 }
				 }
				}
			}
				for(String winHandle : driver.getWindowHandles()) {
			        driver.switchTo().window(winHandle);
			    }
			    driver.close();
			    driver.switchTo().window(mainWindow);
			    System.out.println("hello testing ==>>"+ driver.getCurrentUrl());
			//
        	//break;
        	    
        	//}
        }
        System.out.println("hello testing done");
        driver.close();
    }
  	private static void getInfoFromLI(List <WebElement> LIs, Integer temp , BasicDBObject whereQuery){
  		//List <String> data=  new ArrayList();
  		try {
  		String StageName="", stageLink="",stageID="", JobName="", dateCreated, id, data_id, data_jobID, StageResponcivle, job_dateFinished="";
  		WebElement activityTitle=LIs.get(temp).findElement(By.className("activity-title"));
		 List <WebElement> atags= activityTitle.findElements(By.tagName("a"));
		 data_id=atags.get(0).getAttribute("data-id");
		 id=LIs.get(temp).getAttribute("id");
		 data_jobID=atags.get(0).getAttribute("data-jobid");
		 StageResponcivle=atags.get(0).getText(); 
		 if(atags.size()==3){
			  StageName=atags.get(2).getText();
			  // this is generated wrong right now and also stageLink
			  stageLink=atags.get(2).getAttribute("href");
			  if(stageLink.indexOf("?stageID=") >-1) {
					 stageID= stageLink.split("stageID=")[1];
			  }
			  dateCreated=LIs.get(temp).findElement(By.tagName("time")).getText();
				 Date start_date =new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( dateCreated);
				 if( temp-1 > -1) {
					 job_dateFinished= LIs.get(temp -1 ).findElement(By.tagName("time")).getText();
				 }
				updateData( whereQuery, id, data_id, data_jobID, StageResponcivle, StageName, stageID, stageLink, start_date, job_dateFinished);
		 } 
  		}catch(Exception e) {
  			System.out.println(e);
  		}
  	}
  	private static List <String> getFirstStageInfo( WebDriver  driver ) {
		 String stageID="", StageName="";
		 List <String> FirstStageData= new ArrayList();
		 WebElement Ultop=	driver.findElement(By.xpath("//*[@id=\"collection-media\"]/div[1]/ul"));
		 List <WebElement> li=   Ultop.findElements(By.tagName("li"));
		 if(li.size() >0) {
			System.out.println(li.get(0).getText());
			WebElement atag= li.get(0).findElement(By.tagName("a"));
			String href= atag.getAttribute("href");
			if(href.indexOf("stageid=") > -1) {
				stageID=href.split("stageid=")[1];
			}
			StageName=atag.getText();
			if(StageName.isEmpty()) {
				StageName= (String) js.executeScript("$('.stage-timeline ul').find('li').eq(0).find('a').text().trim()");
			}
			System.out.println("stage name is "+ StageName );
		}
		 
		 FirstStageData.add( stageID );
		 FirstStageData.add(StageName);
		 return FirstStageData; 
  	}
  	private static void updateDateCreated(String jobID , Date dateCreated) {
  		
  		 MongoCollection<Document> collection = db.getCollection("bynder_jobs");
	     collection.updateMany( Filters.eq("jobID", jobID ),
		     Updates.combine(Updates.set("dateCreated", dateCreated)
		 ));
  		 //db.getCollection("bynder_jobs").updateOne(whereQuery, (Bson) new BasicDBObject("dateCreated",dateCreated));;
  	}
	private static void updateData( BasicDBObject whereQuery, String id, String data_id, String data_jobID,
			String StageResponcivle, String StageName, String stageID, String stageLink, Date start_date,
			String job_dateFinished) throws ParseException {
		DBObject updateQuery;
		if( job_dateFinished.isEmpty() ) {
			DBObject listItem = new BasicDBObject("autoStage", new BasicDBObject("id", decode(data_id))
					 .append("tempid",id)
					 .append("jobID",data_jobID)
					 .append("StageRes",StageResponcivle)
					 .append("StageName", StageName)
					 .append("stageLink", stageLink)
					 .append("stageID", stageID)
					 .append("start_date",start_date));
			  updateQuery = new BasicDBObject("$push", listItem);
		}else {
			 Date dateFinished =new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( job_dateFinished);
			DBObject listItem = new BasicDBObject("autoStage", new BasicDBObject("id",decode(data_id))
					 .append("tempid",id)
					 .append("jobID",data_jobID)
					 .append("StageRes",StageResponcivle)
					 .append("StageName", StageName)
					 .append("stageLink", stageLink)
					 .append("stageID", stageID)
					 .append("start_date",start_date)
					 .append("job_date_finished",dateFinished));
			  updateQuery = new BasicDBObject("$push", listItem);
		}
		
		 db.getCollection("bynder_jobs").updateOne(whereQuery, (Bson) updateQuery);;
	}
	public static boolean checkElement( WebElement element, String str) {
		   boolean present;
		   try {
			   element.findElement(By.linkText("Show activities about Progress"));
		       present = true;
		   } catch ( Exception e) {
		      present = false;
		   }
		   return present;
	   }
   public static boolean checkLogin( WebDriver driver, String selector) {
	   boolean present;
	   try {
		   driver.findElement(By.id(selector));
	       present = true;
	   } catch ( Exception e) {
	      present = false;
	   }
	   return present;
   }
   public static String decode( String ID) {
	   String d;
	   StringBuilder s = new StringBuilder(ID);
	   try {
		   //cbc93c0bfb8a417b871ac930a6974080 change to cbc93c0b-fb8a-417b-871a-c930a6974080
		    s.insert(8, "-");
	        s.insert(13, "-");
	        s.insert(18, "-");
	        s.insert(23, "-");
	        d=s.toString();
	   }catch(Exception e){
	      d=ID;
	   }
	   return d;
   }
}
