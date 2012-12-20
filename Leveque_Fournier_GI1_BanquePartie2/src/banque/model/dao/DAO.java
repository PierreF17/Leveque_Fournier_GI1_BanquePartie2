package banque.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DAO est la classe permettant de se connecter à la base de données. Elle fait le lien entre l'application et la base de données.
 * 
 * @author Pierre
 *
 */
public class DAO {

	DataSource ds;

/**
 * Constructeur de la classe DAO, il fait appelle à la méthode initProperties pour charger le fichier de propriété.
 */
	public DAO() {
			// charger les propri�t�s depuis le fichier properties
			// charger le driver
		
		try {
			Context initCtx;
			initCtx = new InitialContext();
			Context envContext  =    
				(Context)initCtx.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/bdJEE");
					
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne la connection à la base de données.
	 * 
	 * @return La connexion à la base.
	 * 
	 * @throws SQLException Si jamais la connection échoue.
	 */
	protected Connection getConnection() throws SQLException {
		// retourne une connexion � la base
		Connection con = ds.getConnection();
		
		return con;
	}


}