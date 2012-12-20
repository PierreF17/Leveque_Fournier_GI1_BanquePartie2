package banque.entites.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import banque.exceptions.CompteDejaExistantException;
import banque.exceptions.CompteInexistantException;
import banque.exceptions.EcritureRefuseeException;
import banque.entites.compte.Compte;
import banque.entites.compte.Ecriture;
import banque.entites.compte.Virement;

public abstract class ClientAbstrait implements Client {

	protected int id;
	protected Identite identite;
	protected String adresse;
	protected List<Compte> comptes;

	/**
	 * @param id
	 * @param identite
	 * @param adresse
	 */
	public ClientAbstrait(Identite identite, String adresse) {
		this.identite = identite;
		this.adresse = adresse;
		this.comptes = new ArrayList<Compte>();
	}

	public boolean equals(Object o) {
		if (o instanceof Client) {
			return this.identite.equals(((Client) o).getIdentite())
					&& this.adresse.equalsIgnoreCase(((Client) o).getAdresse());
		}
		return false;
	}

	public String toString() {
		return this.identite.toString();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public Identite getIdentite() {
		return this.identite;
	}

	@Override
	public String getAdresse() {
		return this.adresse;
	}

	@Override
	public List<Compte> getComptes() {
		return this.comptes;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public void ajouterCompte(Compte unCompte)
			throws CompteDejaExistantException {
		if (this.comptes.contains(unCompte)) {
			throw new CompteDejaExistantException("Le compte existe deja");
		}
		this.comptes.add(unCompte);
	}

	@Override
	public Compte getCompte(String numero) throws CompteInexistantException {
		for (Compte unCompte : this.comptes) {
			if (unCompte.getNumeroCompte().equals(numero))
				return unCompte;
		}
		throw new CompteInexistantException("le compte " + numero
				+ " n'existe pas");
	}

	@Override
	public void addEcriture(String numCompte, String intitule, Date date,
			float montant) throws EcritureRefuseeException,
			CompteInexistantException {
		Compte leCompte = this.getCompte(numCompte);
		leCompte.addEcriture(new Ecriture(date, intitule, montant));
	}

	@Override
	public Virement effectuerVirement(Compte debiteur, Compte crediteur,
			float montant, String libelle, Date dateVirement) throws EcritureRefuseeException {
		return debiteur.effectuerVirement(crediteur, montant, libelle, dateVirement);
	}

	public float soldeCumule() {
		float result = 0;
		for (Compte unCompte : this.comptes) {
			result = result + unCompte.getSolde();
		}
		return result;
	}

}
