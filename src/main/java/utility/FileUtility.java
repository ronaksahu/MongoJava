package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.*;


public class FileUtility {

	public static List<Artist> readArtistFile() throws IOException {
		List<Artist> artistList = new ArrayList<Artist>();
		Scanner sc = new Scanner(new File("data/ARTIST.csv"));
		sc.useDelimiter("\n");   //sets the delimiter pattern
		int i = 0;
		
		while (sc.hasNext())  //returns a boolean value  
		{  
			  if(i == 0) {
				  sc.next();
				  i++;
				  continue;
			  }
				  
			  i++;
			 
			  String[] inps = sc.next().split(",");
			  artistList.add(new Artist(inps[0], inps[1], inps[2], inps[3], inps[4],inps[5],inps[6], inps[7],inps[8]));
		}   
		sc.close();  //closes the scanner  
		System.out.println("Artist Size "+artistList.size());
		return artistList;
		
	}
	
	public static List<ArtWork> readArtWorkFile() throws IOException {
		List<ArtWork> artWorkList = new ArrayList<ArtWork>();
		//Map<String, List<ArtWork>> artworkMap = new HashMap<String, List<ArtWork>>();
		Scanner sc = new Scanner(new File("data/ARTWORK.csv"));
		sc.useDelimiter("\n");   //sets the delimiter pattern
		int i = 0;
		
		while (sc.hasNext())  //returns a boolean value  
		{  
			  if(i == 0) {
				  sc.next();
				  i++;
				  continue;
			  }
				  
			  i++;
			 
			  String[] inps = sc.next().split(",");
			  artWorkList.add(new ArtWork(inps[0], inps[1], inps[2], inps[3], inps[4],inps[5],inps[6]));
//			  List<ArtWork> artList = new ArrayList<ArtWork>();
//			  if(artworkMap.containsKey(inps[0])) {
//				  artList = artworkMap.get(inps[0]);
//			  } 
//			  artList.add(new ArtWork(inps[0], inps[1], inps[2], inps[3], inps[4],inps[5],inps[6]));
//			  artworkMap.put(inps[0], artList);
		}   
		sc.close();  //closes the scanner  
	//	System.out.println("ArtWork Size "+artList.size());
		return artWorkList;
		
	}
	
	public static Map<String, List<ArtWork>> createArtWorkmap(List<ArtWork> artWorkList) {
		Map<String, List<ArtWork>> artworkMap = new HashMap<String, List<ArtWork>>();
		
		for (ArtWork artWork : artWorkList) {
			List<ArtWork> artList = new ArrayList<ArtWork>();
			if(artworkMap.containsKey(artWork.aID)) {
				artList = artworkMap.get(artWork.aID);
			} 
			artList.add(artWork);
			artworkMap.put(artWork.aID, artList);
		}
		return artworkMap;
		
	}
	
	public static List<Customer> readCustomerFile() throws IOException {
		List<Customer> customerList = new ArrayList<Customer>();
		
		
		Scanner sc = new Scanner(new File("data/CUSTOMER.csv"));
		sc.useDelimiter("\n");   //sets the delimiter pattern
		int i = 0;
		
		while (sc.hasNext())  //returns a boolean value  
		{  
			  if(i == 0) {
				  sc.next();
				  i++;
				  continue;
			  }
				  
			  i++;
			 
			  String[] inps = sc.next().split(",");
			  customerList.add(new Customer(inps[0], inps[1], inps[2], inps[3], inps[4],inps[5]));
		}   
		sc.close();  //closes the scanner  
		System.out.println("Customer Size "+customerList.size());
		return customerList;
		
	}
	
	public static List<Bought> readBoughtFile() throws IOException {
		List<Bought> boughtList = new ArrayList<Bought>();
		
		Scanner sc = new Scanner(new File("data/BOUGHT.csv"));
		sc.useDelimiter("\n");   //sets the delimiter pattern
		int i = 0;
		
		while (sc.hasNext())  //returns a boolean value  
		{  
			  if(i == 0) {
				  sc.next();
				  i++;
				  continue;
			  }
				  
			  i++;
			 
			  String[] inps = sc.next().split(",");
			  boughtList.add(new Bought(inps[0], inps[1], inps[2]));
		}   
		sc.close();  //closes the scanner  
		System.out.println("Bought Size "+boughtList.size());
		return boughtList;
		
	}
	
	public static Map<String, String> readStatetFile() throws IOException {
		Map<String, String> stateMap = new HashMap<String, String>();
		Scanner sc = new Scanner(new File("data/STATE.csv"));
		sc.useDelimiter("\n");   //sets the delimiter pattern
		int i = 0;
		
		while (sc.hasNext())  //returns a boolean value  
		{  
			  if(i == 0) {
				  sc.next();
				  i++;
				  continue;
			  }
				  
			  i++;
			 
			  String[] inps = sc.next().split(",");
			  //stateList.add(new State(inps[0], inps[1]));
			  stateMap.put(inps[0], inps[1]);
		}   
		sc.close();  //closes the scanner  
		//System.out.println("State Size "+stateList.size());
		return stateMap;
		
	}
	
	
}
