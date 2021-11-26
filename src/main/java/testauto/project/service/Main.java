package testauto.project.service;

import java.util.Arrays;
import java.util.List;

import testauto.project.sqlutil.DAOFactory;
import testauto.project.sqlutil.MySQLDAO;

public class Main {

	public static void main(String[] args) {
		MySQLDAO dao = DAOFactory.getMySQLDAO();
		
		if(dao == null) {
			System.err.println("Database connection failed. Exiting...");
			return;
		}
		
		for(int i = 0; i < args.length; ++i) {
			switch(args[i]) {
			case "-menu":
				ResultFormatter.format(dao.getMenu(), 
						"%s: %s per one dish, usually served in %s minutes", "=== Menu ===");
				break;
			case "-orders":
				if(i+1 < args.length && 
						args[i+1].matches(
								"[0-9]{4}-[0-9]{2}-[0-9]{2}@([0-9]{2}:){2}[0-9]{2}"
								)) {
					String time = args[i+1].replace('@', ' ');
					ResultFormatter.format(dao.getOrdersAfter(time), 
							"%s %s ordered %s at %s", "=== Selected orders ===");
					i+=1;
					break;
				}
				ResultFormatter.format(dao.getOrdersAfter(null), 
						"%s %s ordered %s at %s", "=== Selected orders ===");
				break;
			default:
				System.err.format("Warning: unrecognized option \"%s\". Skipped.\n",
						args[i]);	
			}
		}
		
		dao.close();
	}

}

