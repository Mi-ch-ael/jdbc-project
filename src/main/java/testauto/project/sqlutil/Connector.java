package testauto.project.sqlutil;

import java.sql.Connection;
import java.sql.DriverManager;

class Connector {
	private static final String DB_URL = "jdbc:mysql://localhost/restaurant";
	private static final String USER = "epam-autotest-training";
    private static final String PASS = "12345678";
	
	private Connector() throws Exception{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		instance = this;
	}
	
	private static Connector instance = null;
	
	public static Connector getInstance() throws Exception {
		if(instance == null) return new Connector();
		return instance;
	}
	
	public Connection connect() throws Exception {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
}

