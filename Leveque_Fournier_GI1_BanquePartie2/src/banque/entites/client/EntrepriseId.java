package banque.entites.client;

public class EntrepriseId implements Identite {

	private String raisonSociale;
	private PersonneId responsable;

	/**
	 * @param raisonSociale
	 */
	public EntrepriseId(String raisonSociale, PersonneId responsable) {
		this.raisonSociale = raisonSociale;
		this.responsable = responsable;
	}

	public String toString() {
		return this.raisonSociale;
	}

	public boolean equals(Object o) {
		if (o instanceof EntrepriseId) {
			return this.raisonSociale
					.equalsIgnoreCase(((EntrepriseId) o).raisonSociale)
					&& this.responsable.equals(((EntrepriseId) o).responsable);
		}
		return false;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.responsable.getNom();
	}

	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return this.responsable.getPrenom();
	}

	@Override
	public String getRaisonSociale() {
		// TODO Auto-generated method stub
		return this.raisonSociale;
	}

}
