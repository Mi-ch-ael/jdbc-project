package testauto.project.service;

import java.util.List;

public class ResultFormatter {
	
	static void formatMenu(List<List<String>> result) {
		if(result == null) {
			System.err.print("Menu fetching failed");
			return;
		}
		System.out.println("=== Menu ===");
		if(result.size() == 0) {
			System.out.println("<empty>");
		}
		for(List<String> line: result) {
			System.out.format("%s: %s per one dish, usually served in %s minutes\n",
					line.toArray());
		}
		
	}
}
