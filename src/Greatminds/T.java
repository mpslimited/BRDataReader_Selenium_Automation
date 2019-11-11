package Greatminds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class T {

	public static void main(String[] args) {
		 Db Mdb=new Db();
	     MongoDatabase db = Mdb.mongo();
		 BasicDBObject whereQuery = new BasicDBObject();
		 whereQuery.put("jobID", "054d9b905dd24ed4941cb093b11fa3f7");
		 
		 List<Document> employees = (List<Document>) db.getCollection("bynder_jobs").find(whereQuery).into(new ArrayList<Document>());
		 List<Document> scoreList =  (List<Document>) employees.get(0).get("autoStage");
		 System.out.println(scoreList.size());
		 if(scoreList.size() >0) {
			 for( Integer i=0; i < scoreList.size(); i++) {
				// scoreList.get(i)
			 }
			 MongoCollection<Document> collection = db.getCollection("bynder_jobs");
			 // DBObject listItem = new BasicDBObject("autoStage", new BasicDBObject());
			 DBObject updateQuery = new BasicDBObject("$pop", -1);
			 
			 db.getCollection("bynder_jobs").updateOne(whereQuery, (Bson) updateQuery ); 
		 }

	}

}
