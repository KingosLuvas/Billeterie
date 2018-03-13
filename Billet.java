package billeterie;

public class Billet {
	private double prix;
	private String artiste;
	private String date;
	
	public Billet (String a, double p, String d) {
		this.prix = p;
		this.artiste = a;
		this.date = d;
	}
	
	public String toString() {
		return this.artiste+" prix de "+this.prix+"€ à la date du "+this.date;
	}
	
	public String getArtiste() {
		return this.artiste;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public String getDate() {
		return this.date;
	}
}
