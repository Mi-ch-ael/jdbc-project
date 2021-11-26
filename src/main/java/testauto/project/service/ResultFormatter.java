package testauto.project.service;

import java.util.List;

public class ResultFormatter {
	
	static void format(List<List<String>> result, String template, String header) {
		if(result == null) {
			System.err.print("Query failed, nothing to display");
			return;
		}
		System.out.println(header);
		if(result.size() == 0) {
			System.out.println("<empty>");
		}
		for(List<String> line: result) {
			System.out.format(template + "\n", line.toArray());
		}
	}
}
