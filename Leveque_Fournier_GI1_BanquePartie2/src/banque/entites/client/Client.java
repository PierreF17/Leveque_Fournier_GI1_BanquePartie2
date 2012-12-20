package banque.entites.client;

import java.util.Date;
import java.util.List;

import banque.exceptions.CompteDejaExistantException;
import banque.exceptions.CompteInexistantException;
import banque.exceptions.EcritureRefuseeException;
import banque.entites.compte.Compte;
import banque.entites.compte.Virement;

public interface Client {
	
	public int getId();
	public void setId(int id);
	public Identite getIdentite();
	public String getAdresse();
	public List<Compte> getComptes();
	public boolean equals(Object o);
	public void ajouterCompte(Compte unCompte) throws CompteDejaExistantException;
	public Compte getCompte(String numero) throws CompteInexistantException;
	public void addEcriture(String numCompte, String intitule, java.util.Date date, float montant) throws CompteInexistantException, EcritureRefuseeException;
	public float soldeCumule();
	public void setAdresse(String adresse);
	public Virement effectuerVirement(Compte debiteur, Compte crediteur, float montant,
			String libelle, Date dateVirement) throws EcritureRefuseeException;
	
}
