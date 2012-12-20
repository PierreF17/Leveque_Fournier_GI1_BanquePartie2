package banque.entites.compte;

import java.util.ArrayList;
import java.util.List;

public abstract class CompteAbstrait implements Compte {

	protected int id;
	protected String numeroCompte;
	protected float solde;
	protected List<Ecriture> lesEcritures;

	
	/**
	 * @param id
	 * @param numeroCompte
	 * @param solde
	 */
	public CompteAbstrait(int id, String numeroCompte, float solde) {
		this.id = id;
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.lesEcritures = new ArrayList<Ecriture>();
	}

	/**
	 * @param numeroCompte
	 * @param solde
	 */
	public CompteAbstrait(String numeroCompte, float solde) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.lesEcritures = new ArrayList<Ecriture>();
	}

	public boolean equals(Object o){
		if ( o instanceof Compte ){
			return this.numeroCompte.equals(((Compte)o).getNumeroCompte());
		}
		return false;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	@Override
	public String getNumeroCompte() {
		return this.numeroCompte;
	}

	@Override
	public float getSolde() {
		return this.solde;
	}

	@Override
	public List<Ecriture> getEcritures() {
		return this.lesEcritures;
	}
	
	public void setEcritures(List<Ecriture> lesEcritures){
		this.lesEcritures = lesEcritures;
	}

}