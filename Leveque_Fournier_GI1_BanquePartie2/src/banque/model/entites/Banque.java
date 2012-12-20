package banque.model.entites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import banque.exceptions.CompteDejaExistantException;
import banque.exceptions.CompteInexistantException;
import banque.entites.client.Client;
import banque.entites.client.ComparateurSoldeCumule;
import banque.entites.compte.Compte;
import banque.entites.compte.CompteCheque;
import banque.entites.compte.CompteEpargne;




/** La classe Banque est le modèle de l'application.
* Elle regroupe la liste des clients et une Map associant les clients à leurs diffèrents comptes. 
* @author Hugo
*/

public class Banque {
	
	/** Le nom de la banque */
	private String nom;
	/**La liste des clients de la banque*/
	private List<Client> lesClients;
	/** Conteneur Map associant un compte au client auquel il appartient*/
	private Map<String, Client> lesComptesClient;
	
	/** Constructeur de la classe Banque
	 * 
	 * @param nom
	 * 		    Nom de la banque
	 */
	public Banque(String nom){
		this.nom = nom;
		this.lesClients = new ArrayList<Client>();
		this.lesComptesClient = new HashMap<String, Client>();
	}
	/** Getter de l'attribut Nom */
	public String getNom(){
		return this.nom;
	}
	
	/** Getter de la liste des clients*/
	public List<Client> getClients() {
		return this.lesClients;
	} 
	
	/** Méthode permettant d'ajouter un client à la liste des clients.
	 * Cette méthode insère les couples (Compte, Client) dans la Map.
	 * @param unClient Cleint ajouté à la liste et à la Map.
	 */
	public void ajouterClient(Client unClient) {
		this.lesClients.add(unClient);
		for (Compte unCompte : unClient.getComptes()){
			this.lesComptesClient.put(unCompte.getNumeroCompte(), unClient);
		}
	}
	/** Methode utiliser pour créer une instance de CompteCheque
	 * 
	 * @param numCpte Numero du compte
	 * @param solde Solde de départ du compte
	 * @param decouvert Découvert autorisé sur ce compte
	 * @param client Client auquel appartient ce compte
	 * @throws CompteDejaExistantException Exception se déclenchant si le compte à ajouter existe déjà.
	 */
	public void creeCompteCheque(String numCpte, Float solde, Float decouvert, Client client) throws CompteDejaExistantException { 
		if ( this.lesComptesClient.containsKey(numCpte)){
			throw new CompteDejaExistantException("Le compte numero " + numCpte + " existe deja");
		}
		CompteCheque cpteChq = new CompteCheque(numCpte, solde, decouvert); 
		client.ajouterCompte(cpteChq); 
		this.lesComptesClient.put(numCpte,client); 
	} 
	/** Méthode utilisée pour créer une instance de CompteEpargne
 * 
 * @param numCpte Numero du compte
 * @param solde Solde de départ du compte
 * @param client Client auquel appartient ce compte
 * @throws CompteDejaExistantException Exception se déclenchant si le compte à ajouter existe déjà.
 */
	public void creeCompteEpargne(String numCpte, Float solde,
			Client client) throws CompteDejaExistantException {
		if ( this.lesComptesClient.containsKey(numCpte)){
			throw new CompteDejaExistantException("Le compte numero " + numCpte + " existe deja");
		}
		CompteEpargne cpteEp = new CompteEpargne(numCpte, solde);
		client.ajouterCompte(cpteEp);
		this.lesComptesClient.put(numCpte, client);
	}
	
	/** Méthode utilisée pour renvoyer une liste des clients dont au moins un compte est débiteur.
	 * 
	 * @return Liste des clients dont au moins un compte est débiteur.
	 */
	public List<Client> clientsARisque() { 
		List<Client> result = new ArrayList<Client>(); 
		Iterator<Client> it = lesClients.iterator(); 
		while (it.hasNext()) { 
			Client client = it.next(); 
			boolean clientArisque = false;
			Iterator<Compte> cptIt = client.getComptes().iterator(); 
			while(cptIt.hasNext() && ! clientArisque) { 
				Compte cpt = cptIt.next(); 
				if (cpt.getSolde() < 0) {
					clientArisque = true;
					result.add(client); 
				}
			} 
		} 
		return result; 
	} 
	/** Méthode permettant d'accéder au propriétaire d'un compte dont on connait le numéro.
 * 
 * @param numCpte Numéro du compte dont on cherche le propriétaire
 * @return Instance de lient propriétaire du compte recherché
 * @throws CompteInexistantException Exception se déclenchant si le compte recherché n'existe pas.
 */
	public Client getClient(String numCpte) throws CompteInexistantException { 
		if ( ! this.lesComptesClient.containsKey(numCpte)){
			throw new CompteInexistantException("Le compte numero " + numCpte + " n'existe pas");
		}
		return this.lesComptesClient.get(numCpte);
	}
 
	/** Méthode retournant les 10 client cumulant le plus grand solde sur les comptes de la banque.
	 * 
	 * @return Liste des 10 clients dont le cumul des soldes des comptes est le plus élevé.
	 */
	public Client[] dixMeilleursClients() { 
		List<Client> lesClients = new ArrayList<Client>(this.lesClients);
		// on trie les clients par ordre croissant
		// le premier de la liste aura le plus petit solde cumul�
		Collections.sort(lesClients, new ComparateurSoldeCumule());
		Client[] dixMeilleurs = new Client[10]; 
		ListIterator<Client> it = lesClients.listIterator(lesClients.size());
		int idx = 0;
		while (it.hasPrevious() && idx < 10) { 
			dixMeilleurs[idx] = it.previous(); 
			idx++; 
		}
		return dixMeilleurs; 
	}


}
