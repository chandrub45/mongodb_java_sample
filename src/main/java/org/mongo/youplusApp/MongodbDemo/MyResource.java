package org.mongo.youplusApp.MongodbDemo;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET

	@Produces(MediaType.TEXT_PLAIN)

	//@Path("test")
	public String getIt() {

		return "Got it!";
	}

	@Path("test_2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountName() {   
		// open DB connection          
		MongoClient mc = new MongoClient("localhost",27017);
		MongoDatabase db = mc.getDatabase("college");
		MongoCollection<Document> tb = db.getCollection("student");
		// query the required data
		FindIterable<Document> c = tb.find(Filters.eq("name", "suraj"));
		Document res = c.first();
		return Response.ok(res.toString()).build();
	}

	@Path("add_student")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("department") String department) {   
		// open DB connection          
		MongoClient mc = new MongoClient("localhost",27017);
		MongoDatabase db = mc.getDatabase("college");
		MongoCollection<Document> tb = db.getCollection("student");
		
		Document document = new Document();
		document.put("id", id);
		document.put("name", name);
		document.put("dept", department);
		 
		tb.insertOne(document);
		
		
		return Response.ok("").build();
	}
	
	@Path("update_student")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent() {   
		// open DB connection          
		MongoClient mc = new MongoClient("localhost",27017);
		MongoDatabase db = mc.getDatabase("college");
		MongoCollection<Document> tb = db.getCollection("student");
		
		
		Bson filter = new Document("name", "suraj");
		Bson newValue = new Document("dept", "testing");
		Bson updateOperationDocument = new Document("$set", newValue);
        tb.updateOne(filter, updateOperationDocument);
        
		
		return Response.ok("").build();
	}
/*
 * public void getAccountData(){ MongoClient mc = new MongoClient("localhost",
 * 27017); MongoCredential credential = null;
 * credential=MongoCredential.createCredential("sampleUser","hostelmng",
 * "password".toCharArray()); MongoDatabase db = mc.getDatabase("hostelmng");
 * MongoCollection<Document> tb = db.getCollection("account");
 * 
 * FindIterable<Document> itr = tb.find(); int i = 1; Iterator
 * it=itr.iterator();while(it.hasNext()) { System.out.println(it.next()); i++; }
 * 
 * }
 * 
 * public void insertAccountData(){ 
 * MongoClient mc = new MongoClient("localhost", 27017);
 *  MongoCredential credential = null;
 * credential=MongoCredential.createCredential("sampleUser","hostelmng","password".toCharArray()); MongoDatabase db = mc.getDatabase("hostelmng");
 * MongoCollection<Document> tb = db.getCollection("account");
 * Document doc=new Document("id",34);
 * doc.append("acc_no", 342525);
 * doc.append("balance", 5000);
 * doc.append("paid_amt", 2500);
 * doc.append("mess_bill", 1350);
 * 
 * FindIterable<Document> itr = tb.find(); int i = 1; Iterator
 * it=itr.iterator();while(it.hasNext()) { System.out.println(it.next()); i++; }
 * 
 * tb.insertOne(doc); }
 * 
 * public void deleteAccountData(){ MongoClient mc = new
 * MongoClient("localhost", 27017); MongoCredential credential = null;
 * credential=MongoCredential.createCredential("sampleUser","college","password"
 * .toCharArray()); MongoDatabase db = mc.getDatabase("college");
 * MongoCollection<Document> tb = db.getCollection("student"); Document doc=new
 * Document();
 * 
 * tb.deleteOne(new Document("name","chandru"));
 * 
 * } public void updateAccountData(){ MongoClient mc = new
 * MongoClient("localhost", 27017); MongoCredential credential = null;
 * credential=MongoCredential.createCredential("sampleUser","college","password"
 * .toCharArray()); MongoDatabase db = mc.getDatabase("college");
 * MongoCollection<Document> tb = db.getCollection("student"); Document doc=new
 * Document();
 * 
 * BasicDBObject newDocument = new BasicDBObject(); newDocument.append("$set",
 * new BasicDBObject().append("name","kiran1"));
 * 
 * BasicDBObject searchQuery = new BasicDBObject().append("name", "kiran");
 * 
 * tb.updateOne(searchQuery, newDocument);
 * 
 * }
  
  public static void main(String[] args )
  { 
	  MyResource rs=new MyResource();
  //rs.getAccountData();
  //rs.insertAccountData();
  //rs.deleteAccountData();
  //rs.updateAccountData();
  rs.getAccountName();
  
  
  }*/
  }
 
