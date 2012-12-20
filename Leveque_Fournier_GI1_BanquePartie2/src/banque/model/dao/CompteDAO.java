package banque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import banque.exceptions.CompteDejaExistantException;
import banque.exceptions.CompteInexistantException;
import banque.entites.client.Client;
import banque.entites.compte.CompteCheque;
import banque.entites.compte.CompteEpargne;

/**
 * CompteDAO est la classe permettant d'exécuter la requette sql de selection des comptes et d'ajouter un compte sur la base de données.
 * 
 * @author Pierre
 *
 */
public class CompteDAO extends DAO {
/**
 * Constructeur de la classe CompteDAO. Il fait appelle au constructeur de la classe DAO.
 */
	public CompteDAO() {
		super() ;
	}

/**
 * Récupère la liste des Comptes de la banque.
 * 
 * @param unClient 
 * 			Un client auquel on veut récuperer les comptes.
 * 
 * @return Un client
 */
	public Client recupererComptes(Client unClient) {
		if (unClient.getComptes().isEmpty()) {
			try {
				Connection con = this.getConnection() ;
				Statement st = con.createStatement() ;
				ResultSet rs = st.executeQuery("SELECT id_compte, numero, solde, type, decouvert " + 
												"FROM COMPTE " +
												"WHERE ID_PARTICULIER = " + unClient.getId()) ;
				while (rs.next()) {
					if (rs.getString("type").equalsIgnoreCase("cheque")) {
						try {
							unClient.ajouterCompte(new CompteCheque(rs.getInt("id_compte"), rs.getString("numero"), rs.getFloat("solde"), rs.getFloat("decouvert"))) ;
						} catch (CompteDejaExistantException e) {
							e.printStackTrace();
						}
					}else {
						try {
							unClient.ajouterCompte(new CompteEpargne(rs.getInt("id_compte"), rs.getString("numero"), rs.getFloat("solde"))) ;
						} catch (CompteDejaExistantException e) {
							e.printStackTrace();
						}
					}
				}
				
				rs.close() ;
				st.close() ;
				con.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return unClient ;
	}
	
/**
 * Sauvegarde un Compte Chèque dans la base de données.
 * 
 * @param numCompte
 * 			Le numéro du Compte à sauvegarder.
 * 
 * @param unClient
 * 			Le Client associé au compte à sauvegarder.
 */
	public void sauvegarderCompteCheque(String numCompte, Client unClient) {
		try {
			CompteCheque leCompte = (CompteCheque) unClient.getCompte(numCompte);
			try {
				Connection con = this.getConnection() ;
				Statement st = con.createStatement() ;
				leCompte.setId(this.getIDmax() + 1) ;
				st.executeUpdate("INSERT INTO COMPTE " +
								"VALUES (" + leCompte.getId() + ", '" + leCompte.getNumeroCompte() + "', " + leCompte.getSolde() + ", " + unClient.getId() + ", 'CHEQUE', " + leCompte.getAutorisationDecouvert() + ")") ;
				st.close() ;
				con.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (CompteInexistantException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sauvegarde un Compte Epargne dans la base de données.
	 * 
	 * @param numCompte
	 * 			Le numéro du compte à sauvergarder.
	 * @param unClient
	 * 			Le Client associé au compte à sauvegarder.
	 */
	public void sauvegarderCompteEpargne(String numCompte, Client unClient) {
		try {
			CompteEpargne leCompte = (CompteEpargne) unClient.getCompte(numCompte);
			try {
				Connection con = this.getConnection() ;
				Statement st = con.createStatement() ;
				leCompte.setId(this.getIDmax() + 1) ;
				st.executeUpdate("INSERT INTO COMPTE " +
								"VALUES (" + leCompte.getId() + ", '" + leCompte.getNumeroCompte() + "', " + leCompte.getSolde() + ", " + unClient.getId() + ", 'CHEQUE', " + 0 + ")") ;
				st.close() ;
				con.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (CompteInexistantException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Récuperer l'ID max dans la liste des comptes.
	 * 
	 * @return L'id maximum sous forme d'un entier
	 */
	private int getIDmax() {
		int id = 0 ;
		try {
			Connection con = this.getConnection() ;
			Statement st = con.createStatement() ;
			ResultSet rs = st.executeQuery("SELECT MAX(ID_COMPTE) FROM COMPTE") ;
			while (rs.next()) {
				id = rs.getInt("MAX(ID_COMPTE)") ;
			}
			rs.close() ;
			st.close() ;
			con.close() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id ;
	}

}
