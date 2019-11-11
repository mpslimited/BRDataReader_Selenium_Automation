package Greatminds;
import java.io.IOException;
import java.text.DateFormat;
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
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
 


public class Test4 {
	static Db Mdb=new Db();
    static MongoDatabase db = Mdb.mongo();
   
    static WebDriver driver=new FirefoxDriver();
    static JavascriptExecutor js = (JavascriptExecutor) driver; 
    static  int count=0;
    public static void main(String[] args) throws InterruptedException, ParseException {
    	System.out.println("process start");
	    MongoCollection<Document> collection = db.getCollection("bynder_jobs");
	   // BasicDBObject gtQuery = (BasicDBObject) new BasicDBObject().put("isUpdated", false);
	    	BasicDBObject whereQuery = new BasicDBObject();
	    	// code for update that job which is compated after 27 jun
	    	//{$gte :new Date('2019-05-26')}
	    	//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	//whereQuery.put("Preset_Stages.start_date", new BasicDBObject("$gte", new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-15")));
	    	List<String> list = new ArrayList<String>();
		        list.add("5d1af8e5eaa446d28bd343e7236194b7");
		        list.add("a6287082e2464128ae5a85a65f2c39a3");
		        list.add("b3adfdfcdea6429aa79742c00e436f16");
		        list.add("45d465512a7c4f7db950fa9803adc8ef");
		        list.add("57f29554510a4c7c838ccb45865ccb23");
		        list.add("656928058a424bf5b451c92777b70bbe");
		        list.add("839a6d1be383452f9a4122c368d63f9d");
		        list.add("a2e6b1c05bdb4653814bc2529d98d9ce");
		        list.add("a4ef9028ab124f828a44849f4de4024c");
		        list.add("c84ee8bee4fb4507ad23273c92570fa5");
		        list.add("cc8cb97f71054812821021d8c1c9f32c");
		        list.add("d93e50fb41d14029a24b0c447c12329d");
		        list.add("ec0092cee93d4dcd89d09f459e658b86");
		        list.add("f501b4ddd3b6453fa7dafad9dbb9a7d0");
		        list.add("502869f9bad8412489052de1876851a4");
	        
	        
		        whereQuery.put("jobID", new BasicDBObject( "$in",list));
	    	
	    	//whereQuery.put("isMerged", false);
	    	//whereQuery.put("isUpdated", false);
		        
	    	//whereQuery.put("campaignID", "ee19e14d-bdb9-407b-ab56-17292d585787"); // Marketing +22
	    	//whereQuery.put("campaignID", "3d39f53b-3123-4eb1-a3f1-274cd4160efe");//Wit & Wisdom
	    	//whereQuery.put("campaignID", "12087c22-260a-4fb8-834e-d231c4c277a3"); //+//108 //GEOD
	    	//whereQuery.put("campaignID", "9618db88-fc78-47a5-9916-e864e696ae11"); //Eureka Math 2
	    	////whereQuery.put("campaignID", "4924dc05-03c5-4086-90ce-41d8bf501684");// PhD Science
	    	
	    	//whereQuery.put("campaignID", "4924dc05-03c5-4086-90ce-41d8bf501684");
	    	//whereQuery.put("campaignID", "ee19e14d-bdb9-407b-ab56-17292d585787");// markting
	    	///"job_active_stage.status":"Approved"
	    	////whereQuery.put("job_active_stage.status", "Approved");
	    	//whereQuery.put("job_active_stage.position", "{$ne, 1}");
		        System.out.println( whereQuery  );
	        FindIterable<Document> fi = collection.find( whereQuery ).noCursorTimeout(true);
	   
	//------472bbd38580d4a20b7b36851af97f392, 
//	    System.out.println(" data finding under bynder_jobs:"+ whereQuery.toString() );
    	int count=0;
	    MongoCursor<Document> cursor = fi.iterator();
        while(cursor.hasNext()) {
        	count++;
           String jobID=cursor.next().get("jobID").toString();
           System.out.println("JOB ID: "+jobID +"\n");
           getdataFromURL(jobID);
        }
	    	//3cd74dd86e244549aaf00f1b5e4ec397
	    	//SCI-1060
       // 02656136801 sanket ==> hdfc
	   // getdataFromURL("f9c78d8f4f2c4a32958f3932c379bf1a");
        System.out.println("complated: "+ count);
        driver.close();
    }
   
   private static void getdataFromURL(String jobID) {
    	try {
	    	String URL="https://greatminds.getbynder.com/workflow/job/view/"+jobID+"/";
	        driver.get(URL);
	        if(count==0) {
	        	driver.findElement(By.id("inputEmail")).sendKeys("praveen.nair@adi-mps.com");
	    		driver.findElement(By.id("inputPassword")).sendKeys("gm@1234");
	    		driver.findElement(By.className("login-btn")).click();
	    		Thread.sleep(3000);
	        }
	        count++;
	        gotoFirst();
	        
	        WebElement  ele=driver.findElement(By.xpath("//*[@id=\"collection-detail\"]/div/ul/li[3]/strong"));
	        String presetName=ele.getText().trim();
	        List <WebElement>  li=driver.findElements(By.xpath("//*[@id=\"collection-media\"]/div[1]/ul/li"));
	        ArrayList <DBObject> StageInfoData= new ArrayList<DBObject>(); 
	         
	         BasicDBObject whereQuery = new BasicDBObject();
			 whereQuery.put("jobID", jobID);
			 List<Document> data = (List<Document>) db.getCollection("bynder_jobs").find(whereQuery).into(new ArrayList<Document>());
			 //if(data.size() >0) {
				 List<Document> job_stagesdata= (List<Document>) data.get(0).get("job_stages");
			 //}

	        for(int j=0; j < li.size(); j++) {
	        	String href = li.get(j).findElement(By.tagName("a")).getAttribute("href");
	        	
	        	String stageID="";
	        	if(href.indexOf("stageid=") >-1) {
					stageID= href.split("stageid=")[1];
				}
	        	
	        	//System.out.println( " ID:"+ stageID + " Link:"+ href);
	        	
	        	String stage=li.get(j).findElement(By.className("stage-name")).getText().trim();
	        	if(stageID.isEmpty()) {
	        		System.out.println(job_stagesdata);
	        		System.out.println(job_stagesdata.size());
	        		for(int ll=0; ll < job_stagesdata.size(); ll++ ) {
	        			if( (Integer)job_stagesdata.get(ll).get("position") == (j+1)  ) {
	        				stageID=job_stagesdata.get(ll).get("id").toString();
	        				break;
	        			}
	        		}
	        	}
	        	//System.out.println( " stages data:==> position:"+ (j+1) + " Name:"+li.get(j).getText().trim() +" ==>"+stage.trim() );
	        	DBObject stageInfo=new BasicDBObject("ID", decode(stageID));
	        	stageInfo.put("name", stage);
	        	stageInfo.put("position", (j+1));
	        	StageInfoData.add(j, stageInfo);
	        	//StageInfoData= new BasicDBObject("$push", stageInfo);
	        	if(li.get(j).isDisplayed()) {
	        		WebElement elementsR =driver.findElement(By.className("timeline-right"));
	        		if(elementsR.isDisplayed()) {
	        			elementsR.click();
	        		}
	        	}
	        	//stageInfo
	        }
	        System.out.println(Filters.eq("jobID", jobID ));
	        MongoCollection<Document> collection = db.getCollection("bynder_jobs");
	        UpdateResult rs= collection.updateOne( Filters.eq("jobID", jobID ),
				     Updates.combine( 
				    		 Updates.set("presetName", presetName),
				    		 Updates.set("presetstages",  StageInfoData ),
				    		 Updates.set("temppresetstages",  StageInfoData ),
				    		 Updates.set("autoStage",   new ArrayList<DBObject>()),
				    		 Updates.set("isUpdated",  true )
				    		 )
		    );
	        
	       // gotoFirst();
	        System.out.println( StageInfoData );
	        System.out.println( rs.getModifiedCount()+"::"+ rs.getMatchedCount() );
	       // System.exit(0);
	        
	        List <WebElement> elements =driver.findElements(By.className("sort"));
	       //for( Integer i=0; i< elements.size(); i++) {
	    	 elements.get(1).click();
			 WebElement followingSibling = elements.get(1).findElement(By.xpath("following-sibling::*"));
			 if(checkElement( followingSibling, "Progress")) {
				 followingSibling.findElement(By.partialLinkText("Progress")).click();
			 }else {
				 elements.get(1).click();
				 followingSibling = elements.get(1).findElement(By.xpath("following-sibling::*"));
				 followingSibling.findElement(By.partialLinkText("Progress")).click();
			 }
			 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1200)");
			 Thread.sleep(800);
			 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-2000)");
			 Thread.sleep(1000);
			 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)");
			 Thread.sleep(1000);
			 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,4000)");
			 Thread.sleep(1000);
			 WebElement UL= driver.findElement(By.className("activity-list"));
			 Thread.sleep(1000);
				 List <WebElement> LIs = UL.findElements(By.className("activity"));
				 System.out.println("Total Lis is: "+ LIs.size() );
				 String jobKey= driver.findElement(By.className("job-link")).getText();
			
				 if(LIs.size() >0 ) {
					 WebElement activityTitle1=LIs.get(0).findElement(By.className("activity-title"));
					 List <WebElement> atags1= activityTitle1.findElements(By.tagName("a"));
					 
					 String job_date_finished="";
					 BasicDBObject whereQuerys = new BasicDBObject();
					 whereQuerys.put("jobID", jobID);
					 
					 List<Document> employees = (List<Document>) db.getCollection("bynder_jobs").find(whereQuerys).into(new ArrayList<Document>());
					 System.out.println(employees.get(0).get("presetID"));
					 BasicDBObject whereQuery2 = new BasicDBObject();
					 whereQuery2.put("ID", employees.get(0).get("presetID"));
//					 
					 List<Document> job_presets = (List<Document>) db.getCollection("job_presets").find(whereQuery2).into(new ArrayList<Document>());
//					 if(job_presets.size() >0) {
//						 List<Document> presetstages = (List<Document>) job_presets.get(0).get("presetstages");
//						 List<Document> job_stages =(List<Document>) job_presets.get(0).get("job_stages");
////						
//					 }
					 
					 String FirstStageName= (String)StageInfoData.get(0).get("name");//(String)presetstages.get(0).get("name");
					 Document jobMetaproperties= ( Document ) employees.get(0).get("jobMetaproperties");
					 String metaDate = (String) jobMetaproperties.get("e9074f5b472f41d4a92ac511e53da775");//e9074f5b472f41d4a92ac511e53da775
					 Date MetadateCreated= new SimpleDateFormat("yyyy-MM-dd").parse(metaDate);
					 WebElement activityTitlef=LIs.get( LIs.size()-1 ).findElement(By.tagName("time"));
					 String FirstFinished= activityTitlef.getAttribute("data-created");
					 System.out.println("first stage name : "+ FirstStageName + " and metaDate :"+ metaDate + ": and FirstFinished :"+ FirstFinished);
					 if(metaDate.isEmpty()) {
						  Date NotMetaDate=(Date)employees.get(0).get("dateCreated");
						  metaDate= new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(NotMetaDate);
					 }
					 if(FirstStageName.trim().equalsIgnoreCase("Complete Request Form")){
						 Date dateCreated= new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( FirstFinished );
						 updateDateCreated( jobID , dateCreated); 
					 }else {
						 Date dateCreated= new SimpleDateFormat("yyyy-MM-dd").parse(metaDate);
						 String stageName=(String) StageInfoData.get(0).get("name");
						 String ID=(String) StageInfoData.get(0).get("ID");
						 updateData(whereQuery, ID, 1, stageName, dateCreated, FirstFinished );
					 }
					
					 Integer LoopItrator=0; 
					 String activityTitle= LIs.get(0).findElement(By.className("activity-title")).getText();
					 if( activityTitle.indexOf("finished") >-1 ) {
						 LoopItrator=1;
					 }
					 for(Integer temp= (LIs.size()-1); temp >= LoopItrator; temp --) {
						  getInfoFromLI( LIs, temp , whereQuery,  StageInfoData);
					 }
					 // update NoSql query
					 if(activityTitle.indexOf("finished") >-1 ) {
						 job_date_finished= LIs.get(0).findElement(By.tagName("time")).getText();
						 if(LIs.size() > 1 && LIs.get(1).getText().indexOf("finished") >-1 ) {
							 job_date_finished= LIs.get(1).findElement(By.tagName("time")).getText();
						 }
						 Date job_dateFinished=new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse(job_date_finished);
					     MongoCollection<Document> collection1 = db.getCollection("bynder_jobs");
					     collection1.updateOne( Filters.eq("jobID", jobID ),
						     Updates.combine(Updates.set("job_key", jobKey),
						     Updates.set("dateCreated",  MetadateCreated ),
						     Updates.set("job_date_finished",  job_dateFinished ),
						     Updates.set("isUpdated",  true )
						 ));
					 }else {
						 MongoCollection<Document> collection1 = db.getCollection("bynder_jobs");
					     collection1.updateOne( Filters.eq("jobID", jobID ),
						     Updates.combine(Updates.set("job_key", jobKey),
						     Updates.set("dateCreated",  MetadateCreated ),
						     Updates.set("isUpdated",  true )
						 ));
					 }
				 }else {
						System.out.println("log not found");
						/// we have need to set job_key in this case//
						MongoCollection<Document> collection1 = db.getCollection("bynder_jobs");
					     collection1.updateOne( Filters.eq("jobID", jobID ),
						     Updates.combine(Updates.set("job_key", jobKey)
						 ));
					 }
				 
	       
	        System.out.println("Total jobs "+ count +" done");
	       // driver.close();
    	}catch(Exception e) {
    		System.out.println(e);
    		e.printStackTrace();
    	}
    }

private static void gotoFirst() {
	List <WebElement> elements =driver.findElements(By.className("timeline-left"));
	if(elements.size() >0) {
		while(elements.get(0).isDisplayed()) {
			 elements.get(0).click();
			}
	}
}
  	private static void getInfoFromLI(List <WebElement> LIs, Integer temp , BasicDBObject whereQuery, ArrayList <DBObject> StageInfoData){
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
			  if(!stageLink.isEmpty() && stageLink.indexOf("?stageID=") >-1) {
					 stageID= stageLink.split("stageID=")[1];
			  }
			  dateCreated=LIs.get(temp).findElement(By.tagName("time")).getText();
			  Date start_date =new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( dateCreated);
			  if( temp-1 > -1) {
				 job_dateFinished= LIs.get(temp -1 ).findElement(By.tagName("time")).getText();
			  }
			  int position=0;
			  if(StageInfoData.size() >0 && !StageName.isEmpty()) {
				  for(int tempr=1; tempr < StageInfoData.size(); tempr++) {
					 if( StageInfoData.get(tempr).get("name").toString().equalsIgnoreCase(StageName)) {
						 position= (int)StageInfoData.get(tempr).get("position");
					 }
				  }
			  }
			  updateData( whereQuery, data_id, position,  StageName, start_date, job_dateFinished);
			
		 } 
  		}catch(Exception e) {
  			System.out.println(e);
  			e.printStackTrace();
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
				//StageName= atag.findElement().getText();
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
	     collection.updateOne( Filters.eq("jobID", jobID ),
		     Updates.combine(Updates.set("dateCreated", dateCreated)
		 ));
  		 //db.getCollection("bynder_jobs").updateOne(whereQuery, (Bson) new BasicDBObject("dateCreated",dateCreated));;
  	}
	private static void updateData( BasicDBObject whereQuery, String stageid, int position, String StageName,  Date start_date,
			String job_dateFinished) throws ParseException {
		DBObject updateQuery;
		if( job_dateFinished.isEmpty() ) {
			DBObject listItem = new BasicDBObject("autoStage", new BasicDBObject("id", decode(stageid))
					 .append("name", StageName)
					 .append("position", position)
					 .append("start_date",start_date));
			  updateQuery = new BasicDBObject("$push", listItem);
		}else {
			 Date dateFinished =new SimpleDateFormat("yyyy-MM-dd hh:mm a").parse( job_dateFinished);
			DBObject listItem = new BasicDBObject("autoStage", new BasicDBObject("id",decode(stageid))
					 .append("name", StageName)
					 .append("position", position)
					 .append("start_date",start_date)
					 .append("job_date_finished",dateFinished));
			  updateQuery = new BasicDBObject("$push", listItem);
		}
		 db.getCollection("bynder_jobs").updateOne(whereQuery, (Bson) updateQuery);;
	}
	public static boolean checkElement( WebElement element, String str) {
		   boolean present;
		   try {
			   element.findElement(By.partialLinkText(str));
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
		   if(ID.indexOf("-") == -1) {
			   	s.insert(8, "-");
		        s.insert(13, "-");
		        s.insert(18, "-");
		        s.insert(23, "-");
		        d=s.toString();
		   }else {
			   d=ID;
		   }
		   //cbc93c0bfb8a417b871ac930a6974080 change to cbc93c0b-fb8a-417b-871a-c930a6974080
		    
	   }catch(Exception e){
	      d=ID;
	   }
	   return d;
   }
}
