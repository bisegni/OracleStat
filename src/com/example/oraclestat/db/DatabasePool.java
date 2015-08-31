/**
 * 
 */
package com.example.oraclestat.db;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 * Gestione centralizzata per la scelta del metodo per la creazioni delle
 * connesisoni al database dell'infrastuttura
 * 
 * @author Bisegni Classe
 */
public class DatabasePool extends Thread {
	private static DatabasePool instance = null;
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private PoolDataSource pds = null;

	/**
	 * Return the singleton instance
	 * 
	 * @return
	 * @throws SQLException 
	 */
	static public DatabasePool getInstace() throws SQLException {
		if (instance == null) {
			instance = new DatabasePool();
			instance.initOracleDataSource();
		}
		return instance;
	}

	/**
	 * @return Connessione al database di default
	 * @throws SQLException
	 */
	public Connection getPooledConnection() throws SQLException {
		Connection conn = pds.getConnection();
		
		if (conn != null)
			conn.setAutoCommit(false);
		return conn;
	}

	/**
	 * @return Returns the driver.
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            The driver to set.
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Prepare th eoracle data source
	 * 
	 * @return
	 * @throws SQLException
	 */
	private void initOracleDataSource() throws SQLException {
		pds = PoolDataSourceFactory.getPoolDataSource();
		pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		pds.setURL(getUrl());
		pds.setUser(getUsername());
		pds.setPassword(getPassword());
	}

}
