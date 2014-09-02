/**
 * RSS Controller class accept URL and perform XML to java unmarshalling by using JAXB.
 * @author Juthamas Utamaphathai 5510546964
 */
package controller;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.Rss;


public class RSSController {
	private JAXBContext ctx;
	private Unmarshaller unmarshaller;
	private Rss rss;
	
	/**
	 * Constructor.
	 * Declaring JAXBContext and unmarshaller.
	 * @throws JAXBException
	 */
	public RSSController() throws JAXBException {
		ctx = JAXBContext.newInstance( Rss.class );
		unmarshaller = ctx.createUnmarshaller( );
	}
	
	public Rss getRSS(){
		return rss;
	}
	
	/**
	 * Creating Java objects from XML that read data from URL.
	 * @param url
	 * @throws MalformedURLException
	 * @throws JAXBException
	 */
	public void setRSS(String url) throws MalformedURLException, JAXBException{
		Object obj = unmarshaller.unmarshal( new URL(url) );
		rss = (Rss) obj;
		System.out.println(rss.getChannel().getTitle());
	}

}
