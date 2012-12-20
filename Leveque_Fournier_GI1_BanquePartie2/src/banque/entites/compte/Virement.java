package banque.entites.compte;

import java.util.Date;

public class Virement {
	
	private Compte debiteur;
	private Compte crediteur;
	private float montant;
	private String libelle;
	private java.util.Date dateVirement;
	
	public Virement(Compte debiteur, Compte crediteur, float montant,
			String libelle, Date dateVirement) {
		this.debiteur = debiteur;
		this.crediteur = crediteur;
		this.montant = montant;
		this.libelle = libelle;
		this.dateVirement = dateVirement;
	}

	public Compte getDebiteur() {
		return debiteur;
	}

	public Compte getCrediteur() {
		return crediteur;
	}

	public float getMontant() {
		return montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public java.util.Date getDateVirement() {
		return dateVirement;
	}
	

}
