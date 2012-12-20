package banque.entites.client;

public class PersonneId implements Identite {

	private String nom;
	private String prenom;

	/**
	 * @param nom
	 * @param prenom
	 */
	public PersonneId(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public String toString() {
		return this.nom + " " + this.prenom;
	}

	public boolean equals(Object o) {
		if (o instanceof PersonneId) {
			return this.nom.equalsIgnoreCase(((PersonneId) o).nom)
					&& this.prenom.equalsIgnoreCase(((PersonneId) o).prenom);
		}
		return false;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return this.prenom;
	}

	@Override
	public String getRaisonSociale() {
		// TODO Auto-generated method stub
		return null;
	}

}
