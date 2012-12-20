package banque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import banque.exceptions.EcritureRefuseeException;
import banque.entites.compte.Compte;
import banque.entites.compte.Ecriture;
/**
 * EcritureDAO est la classe permettant d'exécuter la requette sql de selection des écritures d'un compte.
 * 
 * @author Pierre
 *
 */
public class EcritureDAO extends DAO {
	
/**
* Constructeur de la classe EcritureDAO. Il fait appelle au constructeur de la classe DAO.
*/
	public EcritureDAO() {
		super() ;
	}
	
/**
* Charge les écritures d'un compte
* 
* @param unCompte
* 			Compte à charger
* 
* @return Les écritures du comptes
*/
	public Compte chargerEcritures(Compte unCompte) {
		if (unCompte.getEcritures().isEmpty()) {
			try {
				Connection con = this.getConnection() ;
				Statement st = con.createStatement() ;
				ResultSet rs = st.executeQuery("SELECT id_ecriture, date_ecriture, libelle, montant " + 
												"FROM ECRITURE " +
												"WHERE id_compte = " + unCompte.getId()) ;
				while (rs.next()) {
					try {
						unCompte.addEcriture(new Ecriture(rs.getInt("id_ecriture"), rs.getDate("date_ecriture"), rs.getString("libelle"), rs.getFloat("montant"))) ;
					} catch (EcritureRefuseeException e) {
						e.printStackTrace();
					}
				}
				rs.close() ;
				st.close() ;
				con.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return unCompte ;
	}

/**
 * Sauvegarde l'écriture sur un Compte.
 * 
 * @param uneEcriture
 * 			Ecriture à sauvegarder
 * 
 * @param unCompte
 * 			Compte sur lequel l'écriture est à sauvegarder.
 */
	public void sauvegarder(Ecriture uneEcriture, Compte unCompte) {
		Connection con;
		try {
			con = this.getConnection();
			Statement st = con.createStatement() ;
			st.executeUpdate("INSERT INTO ECRITURE VALUES(" + 
							uneEcriture.getId() + ",'" + uneEcriture.getDate() +
							"'," + uneEcriture.getMontant() + "," + uneEcriture.getMontant() + "," +unCompte.getId() + ")") ;
			st.close() ;
			con.close() ;
			System.out.println("Ecriture " + uneEcriture.getId() + " sauvegardé") ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
