package Greatminds;

import java.util.Arrays;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Db {
	private String user="mpst";     // the user name
	private String source="mpsdb";   // the source where the user is defined
	private char[] password="HBjgmdT649(T2".toCharArray(); // the password as a character array
	public MongoDatabase mongo() {
		 MongoDatabase db ;
		 try {	
			 MongoCredential credential = MongoCredential.createCredential(user, source, password);
				MongoClient mongoClient =  MongoClients.create(MongoClientSettings.builder()
				.applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("10.31.1.143", 27017))))
				.credential(credential).build());
				db = mongoClient.getDatabase("mpsdb");
		} catch (Exception e) {
				e.printStackTrace();
			return null;	
		} 
		return db;
	 }
}
