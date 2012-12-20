package banque.entites.client;

public class Particulier extends ClientAbstrait {

	public Particulier(String nom, String prenom, String adresse) {
		super(new PersonneId(nom, prenom), adresse);
	}

}
