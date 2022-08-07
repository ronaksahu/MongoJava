import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.*;
import utility.FileUtility;

public class JavaMongo {
	
	public static List<Artist> artistList = new ArrayList<Artist>();
	public static Map<String, List<ArtWork>> artworkMap = new HashMap<String, List<ArtWork>>();
	public static List<ArtWork> artWorkList = new ArrayList<ArtWork>();
	public static List<Customer> customerList = new ArrayList<Customer>();
	public static List<Bought> boughtList = new ArrayList<Bought>();
	public static Map<String, String> stateMap = new HashMap<String, String>();
	
	public static void main(String[] args) throws IOException {
		
		MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27017);
		System.out.println("created Mongo");
		CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoDatabase database = mongoClient.getDatabase("test").withCodecRegistry(pojoCodecRegistry);;
		System.out.println("Database success!!");
		loadAllFiles();
		
		database.getCollection("ARTISTS").drop();
		database.getCollection("ARTWORK").drop();
		
		database.createCollection("ARTISTS");
		database.createCollection("ARTWORK");
		System.out.println("Collection Created Successfully!! ");
		
		MongoCollection<Document> artistCollection = database.getCollection("ARTISTS");
		artistCollection.insertMany(loadArtistCollection());
		loadArtwork(artistCollection);
		
		System.out.println("Artist Data loaded Successfully!!");
		
		MongoCollection<Document> artworkCollection = database.getCollection("ARTWORK");
		artworkCollection.insertMany(loadArtworkCollection());
		loadCustData(artworkCollection);
		
		System.out.println("Artwork Data loaded Successfully!!");

		System.out.println("Querying Data ");
		getArtworkOfArtistById(artistCollection);
		getAllCustomerBoughtArtwork(artworkCollection);
		
	}
	
	public static void loadAllFiles() throws IOException {
		artistList = FileUtility.readArtistFile();
		artWorkList = FileUtility.readArtWorkFile();
		customerList = FileUtility.readCustomerFile();
		boughtList = FileUtility.readBoughtFile();
		stateMap = FileUtility.readStatetFile();
	}

	public static List<Document> loadArtworkCollection() {
		List<Document> artworkDoc = new ArrayList<Document>();
		//System.out.println(artistList.size());
		for (ArtWork artwork : artWorkList) {
			Document document = new Document();
			document.append("artID", artwork.artID);
			document.append("title", artwork.title);
			document.append("creationDate", artwork.creationDate);
			document.append("price", artwork.price);
			document.append("form", artwork.form);
			document.append("artistName", getArtistName(artwork.aID));

			//document.append("artWorks", artworkMap.get(artist.aID));
			artworkDoc.add(document);
		}
		return artworkDoc;
	}
	
	private static String getArtistName(String aID) {
		// TODO Auto-generated method stub
		for (Artist artist : artistList) {
			if(artist.aID.equals(aID)) {
				return artist.name;
			}
		}
		return null;
	}

	public static List<Document> loadArtistCollection() {
		List<Document> artworkColl = new ArrayList<Document>();
		for (Artist artist : artistList) {
			Document document = new Document();
			document.append("aID", artist.aID);
			document.append("artistName", artist.name);
			document.append("birthDate", artist.birthDate);
			document.append("stateName", stateMap.get(artist.stateAb));
			//document.append("artWorks", artworkMap.get(artist.aID));
			artworkColl.add(document);
		}
		return artworkColl;
	}
	
	public static void loadArtwork(MongoCollection<Document> collection) {
		artworkMap = FileUtility.createArtWorkmap(artWorkList);
		for (String name : artworkMap.keySet()) 
        {
            // search  for value
			List<ArtWork> artworksList = artworkMap.get(name);
			List<ArtWork> artworks = new ArrayList<ArtWork>();
			for (ArtWork artWork : artworksList) {
				artworks.add(new ArtWork(artWork.title, artWork.price, artWork.form));
				
			}
			Document query = new Document();
	        query.append("aID" , name);
	        Document setData = new Document();
	        setData.append("artworks", artworks);
	        Document update = new Document();
	        update.append("$set", setData);
	        //To update single Document  
	        collection.updateOne(query, update);

        }
	}
	
	
	
	public static List<Customer> getCustomerDetails(List<String> custList) {
		List<Customer> custBoughtList = new ArrayList<Customer>();
		
		for (String id : custList) {
			custBoughtList.add(getCustomerById(id));
		}
		
		return custBoughtList;

	}
	
	public static Customer getCustomerById(String id) {
		for (Customer customer : customerList) {
			if(customer.cID.equals(id)) {
				return new Customer(customer.name, customer.city, stateMap.get(customer.stateAb));
			}
		}
		return null;
	}
	
	public static List<Customer> getCustomerBoughtById(String artId) {
		List<Customer> custList = new ArrayList<Customer>();
		
		for (Bought bought : boughtList) {
			if(bought.artID.equals(artId)) {
				custList.add(getCustomerById(bought.cID));
			}
		}
		return custList;
	}
	
	public static void loadCustData(MongoCollection<Document> collection) {
		for (ArtWork artwork : artWorkList) {
			List<Customer> custList = getCustomerBoughtById(artwork.artID);
			if(custList.size() < 1)
				continue;
			Document query = new Document();
	        query.append("artID" , artwork.artID);
	        Document setData = new Document();
	        setData.append("customers", custList);
	        Document update = new Document();
	        update.append("$set", setData);
	        //To update single Document  
	        collection.updateOne(query, update);
			
		}
	}
	
	public static void getArtworkOfArtistById(MongoCollection<Document> collection) {
//		Document query = new Document();
//        query.append("aID" , "1");
		String aId = "1";
        BasicDBObject query = new BasicDBObject();
        query.append("aID" , aId);
        Document fields = new Document();
        fields.put("status", 1);
        fields.put("_id", 0);
        FindIterable<Document> cursor = collection.find(query);	
        for(Document doc : cursor) {
            //access documents e.g. doc.get()
        	System.out.println("List of Artwork by Artist Id "+aId +" are "+ doc.get("artworks"));
        }
        
	}
	
	public static void getAllCustomerBoughtArtwork(MongoCollection<Document> collection) {
		BasicDBObject query = new BasicDBObject();
		String artId = "1";
        query.append("artID" , artId);
//        Document fields = new Document();
//        fields.put("status", 1);
//        fields.put("_id", 0);
        FindIterable<Document> cursor = collection.find(query);	
        for(Document doc : cursor) {
            //access documents e.g. doc.get()
        	System.out.println("List of customerd who bought artwork ID "+artId);
        	System.out.println(doc.get("customers"));
        }
	}
	
}







