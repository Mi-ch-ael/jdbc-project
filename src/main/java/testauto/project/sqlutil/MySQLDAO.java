package testauto.project.sqlutil;

import java.util.ArrayList;
import java.util.List;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public List<List<String>> getOrderSummary() {
		ResultSet result = null;
		Statement query = null;
		List<List<String>> lines = new ArrayList<List<String>>();
		try {
			query = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = query.executeQuery(
						"select firstName, lastName, issuedAt, "
						+ "sum(menu.price)*(1-client.discount) as total "
						+ "from client "
						+ "join clientOrder using(clientId) "
						+ "join dishInOrder using(orderId) "
						+ "join menu using(dishId) "
						+ "group by clientOrder.orderId;"
					);
			while(result.next()) {
				List<String> line = new ArrayList<String>();
				lines.add(line);
				line.add(result.getString("firstName"));
				line.add(result.getString("lastName"));
				line.add(result.getString("issuedAt"));
				line.add(Double.toString(result.getDouble("total")));
			}
		}
		catch(Exception ex) {
			return null;
		}
		return lines;
	}
	
	public List<List<String>> getOrdersAfter(String timeString) {
		ResultSet result = null;
		PreparedStatement query = null;
		String argumentString = (timeString == null ? "1970-01-01 00:00:00" : timeString);
		List<List<String>> lines = new ArrayList<List<String>>();
		try {
			query = this.connection.prepareStatement(
					"select "
					+ "client.firstName, client.lastName, menu.name, clientOrder.issuedAt "
					+ "from client "
					+ "join clientOrder using(clientId) "
					+ "join dishInOrder USING(orderId) "
					+ "join menu using(dishId) "
					+ "where timediff(clientOrder.issuedAt,?) > 0;",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
				);
			query.setString(1, argumentString);
			result = query.executeQuery();
			
			while(result.next()) {
				List<String> line = new ArrayList<String>();
				lines.add(line);
				line.add(result.getString("client.firstName"));
				line.add(result.getString("client.lastName"));
				line.add(result.getString("menu.name"));
				line.add(result.getString("clientOrder.issuedAt"));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return lines;
	}
}
