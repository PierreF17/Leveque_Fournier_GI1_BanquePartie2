package banque.entites.client;

import java.util.Comparator;

public class ComparateurSoldeCumule implements Comparator<Client> {

	@Override
	public int compare(Client c1, Client c2) {
		return Math.round(c1.soldeCumule() - c2.soldeCumule());
	}

}
