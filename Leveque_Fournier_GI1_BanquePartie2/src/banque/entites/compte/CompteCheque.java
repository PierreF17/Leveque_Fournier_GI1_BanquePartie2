package banque.entites.compte;

import java.util.Date;

import banque.exceptions.EcritureRefuseeException;

public class CompteCheque extends CompteAbstrait {

	private float autorisationDecouvert;

	/**
	 * @param id
	 * @param numeroCompte
	 * @param solde
	 * @param autorisationDecouvert
	 */
	public CompteCheque(int id, String numeroCompte, float solde,
			float autorisationDecouvert) {
		super(id, numeroCompte, solde);
		if (autorisationDecouvert <= 0) {
			this.autorisationDecouvert = autorisationDecouvert;
		} else {
			throw new IllegalArgumentException(
					"le decouvert autorise doit etre negatif");
		}
	}

	/**
	 * @param numeroCompte
	 * @param solde
	 * @param autorisationDecouvert
	 */
	public CompteCheque(String numeroCompte, float solde,
			float autorisationDecouvert) {
		super(numeroCompte, solde);
		if (autorisationDecouvert <= 0) {
			this.autorisationDecouvert = autorisationDecouvert;
		} else {
			throw new IllegalArgumentException(
					"le decouvert autorise doit etre negatif");
		}
	}

	public void addEcriture(Ecriture e) throws EcritureRefuseeException {
		if (this.solde + e.getMontant() > this.autorisationDecouvert) {
			this.solde += e.getMontant();
			this.lesEcritures.add(e);
		} else {
			throw new EcritureRefuseeException("le decouvert autorise serait depasse");
		}
	}

	/**
	 * @return the autorisationDecouvert
	 */
	public float getAutorisationDecouvert() {
		return autorisationDecouvert;
	}

	/**
	 * @param autorisationDecouvert the autorisationDecouvert to set
	 */
	public void setAutorisationDecouvert(float autorisationDecouvert) {
		if (autorisationDecouvert <= 0) {
			this.autorisationDecouvert = autorisationDecouvert;
		} else {
			throw new IllegalArgumentException(
					"le decouvert autorise doit etre negatif");
		}
	}

	@Override
	public Virement effectuerVirement(Compte crediteur, float montant,
			String libelle, Date dateVirement) throws EcritureRefuseeException {
		if (this.solde - montant > this.autorisationDecouvert) {
			crediteur.addEcriture(new Ecriture(dateVirement, libelle, montant));
			// si pas d'exception lev�e, on peut faire le d�bit
			this.solde -= montant;
			this.lesEcritures.add(new Ecriture(dateVirement, "VIR " + libelle, -montant));
			return new Virement(this, crediteur, montant, libelle, dateVirement);
		} else {
			throw new EcritureRefuseeException("le decouvert autorise serait depasse");
		}
	}
	
	

}
