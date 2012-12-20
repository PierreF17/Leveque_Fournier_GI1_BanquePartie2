package banque.entites.compte;

import java.util.Date;

import banque.exceptions.EcritureRefuseeException;

public class CompteEpargne extends CompteAbstrait {

	public static final float PLAFOND = 7700;

	public CompteEpargne(int id, String numeroCompte, float solde) {
		super(id, numeroCompte, solde);
	}

	public CompteEpargne(String numeroCompte, float solde) {
		super(numeroCompte, solde);
	}

	@Override
	public void addEcriture(Ecriture e) throws EcritureRefuseeException {
		// TODO Auto-generated method stub
		if (this.solde + e.getMontant() < 0) {
			throw new EcritureRefuseeException(
					"La somme debitee est trop importante");
		}
		if (this.solde + e.getMontant() > CompteEpargne.PLAFOND) {
			throw new EcritureRefuseeException(
					"La somme creditee depasse le plafond");
		}
		this.solde += e.getMontant();
		this.lesEcritures.add(e);
	}

	@Override
	public Virement effectuerVirement(Compte crediteur, float montant,
			String libelle, Date dateVirement) throws EcritureRefuseeException {
		if (this.solde - montant < 0) {
			throw new EcritureRefuseeException(
					"La somme debitee est trop importante");
		}
		crediteur.addEcriture(new Ecriture(dateVirement, libelle, montant));
		// si pas d'exception lev�e, on peut faire le d�bit
		this.solde -= montant;
		this.lesEcritures.add(new Ecriture(dateVirement, "VIR " + libelle, -montant));

		return new Virement(this, crediteur, montant, libelle, dateVirement);
	}

}
