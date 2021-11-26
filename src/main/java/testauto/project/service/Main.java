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
		
		ResultFormatter.formatMenu(dao.getMenu());
		
		dao.close();
	}

}

