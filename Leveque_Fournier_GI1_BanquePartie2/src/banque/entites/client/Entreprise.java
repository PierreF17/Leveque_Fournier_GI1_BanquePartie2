package banque.entites.client;

public class Entreprise extends ClientAbstrait {
	
	protected String numeroSiret;

	public Entreprise(String raisonSociale, String nomResp, String prenomResp, String adresse, String numeroSiret) {
		super(new EntrepriseId(raisonSociale, new PersonneId(nomResp, prenomResp)), adresse);
		this.numeroSiret = numeroSiret;
	}

	/**
	 * @return the numeroSiret
	 */
	public String getNumeroSiret() {
		return numeroSiret;
	}

}
