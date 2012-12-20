package banque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banque.entites.client.Client;
import banque.entites.client.Particulier;

/**
 * ClientDAO est la classe permettant d'exécuter la requette sql de selection des clients et d'ajouter un client sur la base de données.
 * 
 * @author Pierre
 *
 */
public class ClientDAO extends DAO {

/**
 * Constructeur ClientDao. Il fait appelle au constructeur de la classe DAO.
 */
	public ClientDAO() {
		super();
	}

/**
 * Retourne la liste des clients de la banque.
 * 
 * @return La liste des clients sous forme d'une liste.
 */
	public List<Client> recupererClientsParticuliers() {
		List<Client> lesClients = new ArrayList<Client>();
		try {
			Connection con = this.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID_PARTICULIER, NOM, PRENOM, ADRESSE FROM particulier");
			while (rs.next()) {
				Particulier p = new Particulier(rs.getString("NOM"),
						rs.getString("PRENOM"), rs.getString("ADRESSE"));
				p.setId(rs.getInt("ID_PARTICULIER"));
				lesClients.add(p);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		return lesClients;
	}
/**
 * Sauvegarde un client dans la base de données.
 * 
 * @param unClient
 * 			Le client à sauvegarder.
 */
	public void sauvegarder(Client unClient) {
		Connection con;
		try {
			con = this.getConnection();
			Statement st = con.createStatement() ;
			ResultSet rs = st.executeQuery("SELECT MAX(ID_PARTICULIER) FROM PARTICULIER") ;
			int id = 0 ;
			while (rs.next()) {
				id = rs.getInt("MAX(ID_PARTICULIER)") + 1 ;
			}
			st.executeUpdate("INSERT INTO PARTICULIER VALUES(" + 
							id + ",'" +
							unClient.getIdentite().getNom() + "','" +
							unClient.getIdentite().getPrenom() + "','" +
							unClient.getAdresse() + "')") ;
			rs.close() ;
			st.close() ;
			con.close() ;
			System.out.println("Client " + unClient + " sauvegardé") ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
