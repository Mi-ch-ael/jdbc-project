package testauto.project.service;

import java.util.List;

import testauto.project.sqlutil.DAOFactory;
import testauto.project.sqlutil.MySQLDAO;

public class Main {

	public static void main(String[] args) {
		MySQLDAO dao = DAOFactory.getMySQLDAO();
		System.out.println(dao);
		
		if(dao == null) {
			System.err.println("Database connection failed. Exiting...");
			return;
		}
		
		ResultFormatter.format(dao.getMenu(), 
				"%s: %s per one dish, usually served in %s minutes", "=== Menu ===");
		
		ResultFormatter.format(dao.getOrdersAfter(null), 
				"%s %s ordered %s at %s", "=== Selected orders ===");
		
		ResultFormatter.format(dao.getOrdersAfter("2021-09-02 20:00:00"), 
				"%s %s ordered %s at %s", "=== Selected orders ===");
		
		dao.close();
	}

}

