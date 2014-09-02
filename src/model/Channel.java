/**
 * Channel class is one of elements in RSS.
 * @author Juthamas Utamaphathai 5510546964
 */
package model;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	private String title;
	private String link;
	private String description;
	/** last update */
	private String lastBuildDate;
	/** time-to-live */
	private String ttl;
	@XmlElement(name="item")
	private List<Item> items;
	
	public Channel() {
		items = new ArrayList<Item>(); 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
