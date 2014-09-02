/**
 * RSS class is one of an elements in RSS.
 * @author Juthamas Utamaphathai 5510546964
 */
package model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Rss {
	private Channel channel;
	
	public Rss() {
		
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
