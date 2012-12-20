package banque.entites.client;

public interface Identite {
	
	public String toString();
	
	public boolean equals(Object o);

	public String getNom();
	
	public String getPrenom();
	
	public String getRaisonSociale();
}
