package banque.entites.compte;

public class Ecriture {

	private int id;
	private java.util.Date date;
	private String intitule;
	private float montant;

	/**
	 * @param date
	 * @param intitule
	 * @param montant
	 */
	public Ecriture(java.util.Date date, String intitule, float montant) {
		this.date = date;
		this.intitule = intitule;
		this.montant = montant;
		this.id = -1;
	}
	
	/**
	 * @param id
	 * @param date
	 * @param intitule
	 * @param montant
	 */
	public Ecriture(int id, java.util.Date date, String intitule, float montant) {
		this.id = id;
		this.date = date;
		this.intitule = intitule;
		this.montant = montant;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public java.util.Date getDate() {
		return date;
	}

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @return the montant
	 */
	public float getMontant() {
		return montant;
	}

	public String toString() {
		return this.getIntitule() + " du " + this.getDate() + " montant : "
				+ this.getMontant();
	}

}
