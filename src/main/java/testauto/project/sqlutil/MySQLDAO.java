package testauto.project.sqlutil;

import java.util.ArrayList;
import java.util.List;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public List<List<String>> getMenu() {
		ResultSet result = null;
		Statement query = null;
		List<List<String>> strings = new ArrayList<List<String>>();
		try {
			query = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = query.executeQuery("SELECT name, price, serveTimeMinutes "
					+ "FROM menu");
			
			while(result.next()) {
				List<String> line = new ArrayList<String>();
				strings.add(line);
				line.add(result.getString("name"));
				line.add(Double.toString(result.getDouble("price")));
				line.add(Integer.toString(result.getInt("serveTimeMinutes")));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return strings;
	}
}
