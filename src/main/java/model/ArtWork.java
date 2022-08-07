package model;

public class ArtWork {
	public String aID;
	public String artID;
	public String title;
	public String creationDate;
	public String acquisitionDate;
	public String price;
	public String form;
	public ArtWork(String aID, String artID, String title, String creationDate, String acquisitionDate, String price,
			String form) {
		this.aID = aID;
		this.artID = artID;
		this.title = title;
		this.creationDate = creationDate;
		this.acquisitionDate = acquisitionDate;
		this.price = price;
		this.form = form;
	}
	public ArtWork() {
	}
	
	public ArtWork(String title,  String price,
			String form) {
		this.title = title;
		this.price = price;
		this.form = form;
	}
	
	
	
	
}
