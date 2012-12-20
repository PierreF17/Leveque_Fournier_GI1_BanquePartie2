package banque.entites.compte;

import java.util.Date;
import java.util.List;

import banque.exceptions.EcritureRefuseeException;

public interface Compte {
	
	// retourne l'identifiant du compte
	public int getId();
	
	// fixe la valeur de l'identifiant
	public void setId(int id);
	
	// retourne le num�ro de compte
	public String getNumeroCompte();

	// retourne le solde de ce compte
	public float getSolde();

	// retourne la liste des ecritures pour le compte
	public List<Ecriture> getEcritures();

	// ajoute une ecriture pour ce compte
	public void addEcriture(Ecriture e) throws EcritureRefuseeException ;

	// fixe la liste des �critures
	public void setEcritures(List<Ecriture> lesEcritures);
	
	public Virement effectuerVirement(Compte crediteur,
			float montant, String libelle, Date dateVirement) throws EcritureRefuseeException;

}
