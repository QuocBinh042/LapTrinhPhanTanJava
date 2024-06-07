package demo;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class URLDemo {

	public static void main(String[] args) throws IOException {

		URL url = new URL("https://neo4j.com/docs/cypher-manual/current/indexes");

		try (Scanner sc = new Scanner(url.openStream());) {

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
		}

	}
}
