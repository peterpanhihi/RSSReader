/**
 * Starting an application.
 * @author Juthamas Utamaphathai 5510546964
 */
package UI;

import javax.xml.bind.JAXBException;

public class Main {
	public static void main(String[] args) {
		try {
			RSSReaderUI ui = new RSSReaderUI();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
