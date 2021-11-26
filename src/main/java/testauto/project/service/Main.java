package testauto.project.service;

import testauto.project.sqlutil.DAOFactory;
import testauto.project.sqlutil.MySQLDAO;

public class Main {

	public static void main(String[] args) {
		MySQLDAO dao = DAOFactory.getMySQLDAO();
		System.out.println(dao);
		dao.close();
	}

}

