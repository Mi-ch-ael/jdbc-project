package testauto.project.sqlutil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;

public class MySQLDAO implements Closeable {
	private Connection connection;
	
	MySQLDAO() throws Exception {
		connection = Connector.getInstance().connect();
	}

	public void close() {
		try {
			connection.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
