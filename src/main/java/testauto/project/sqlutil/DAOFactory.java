package testauto.project.sqlutil;

public class DAOFactory {
	public static MySQLDAO getMySQLDAO() {
		MySQLDAO result;
		try {
			result = new MySQLDAO();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return result;
	}
	
	// TODO
	/* public static WhateverDAO getWhateverDao() */
}
