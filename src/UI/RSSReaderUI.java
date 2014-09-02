/**
 * RSS Reader GUI displays a list of items and details of a selected item in an RSS feed.
 * @author Juthamas Utamaphethai 5510546964
 */
package UI;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;
import model.Item;
import controller.RSSController;

public class RSSReaderUI extends JFrame{
	private JLabel urlText;
	private JTextField urlTextField;
	private JButton enterBtn;
	/** Label for description of current Channel */
	private JTextArea channelDes;
	private JPanel panelInfo;
	private JScrollPane listScrollPane;
	private JScrollPane detailScrollPane;
	private JList list;
	private JEditorPane datailPane;
	private List<Item> items;
	/** index of selected item*/
	private int index;
	private RSSController controller;
	
	/**
	 * Constructor declares variables.
	 * @throws JAXBException
	 */
	public RSSReaderUI() throws JAXBException {
		super("RSS Reader");
		
		controller = new RSSController();
		this.setBounds(300,200,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		panelInfo = new JPanel();
		panelInfo.setBounds(6,74,788,508);
		panelInfo.setBorder(BorderFactory.createTitledBorder("RSS feed"));
		panelInfo.setLayout(new GridLayout(1, 2));
		this.getContentPane().add(panelInfo);
		
		listScrollPane = new JScrollPane();
		panelInfo.add(listScrollPane);
	
		detailScrollPane = new JScrollPane();
		datailPane = new JEditorPane();
		datailPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		datailPane.setEditable(false);
		datailPane.setOpaque(false);
		datailPane.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent h) {
				if (HyperlinkEvent.EventType.ACTIVATED.equals(h.getEventType())) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(h.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
			}
			
		});
		detailScrollPane.setViewportView(datailPane);
		panelInfo.add(detailScrollPane);	
		
		inputURL();

		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * A field to enter RSS feed's URL and include description of the current Channel.
	 */
	public void inputURL(){
		urlText = new JLabel("URL : ");
		urlText.setBounds(16,10,99,14);
		urlText.setFont(new Font("", Font.BOLD, 16));
		this.getContentPane().add(urlText);
		
		urlTextField = new JTextField();
		urlTextField.setBounds(60,8,651,22);
		this.getContentPane().add(urlTextField);
		
		urlTextField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
					sendURL();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		enterBtn = new JButton("enter");
		enterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sendURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		});
		enterBtn.setBounds(707,7,87,25);
		this.getContentPane().add(enterBtn);
		
		channelDes = new JTextArea();
		JScrollPane scroll = new JScrollPane(channelDes);
		scroll.setBounds(9, 35, 782, 36);
		channelDes.setEditable(false);
		this.getContentPane().add(scroll);
		
	}
	
	/**
	 * Setting URL for RSS.
	 * @throws MalformedURLException
	 * @throws JAXBException
	 */
	public void sendURL() throws MalformedURLException, JAXBException{
		controller.setRSS(urlTextField.getText());
		channelDes.setText(controller.getRSS().getChannel().getTitle()+"\r\n"+controller.getRSS().getChannel().getDescription());
		panelInfo();
		urlTextField.setText("");
	}
	
	/**
	 * A list of items in the RSS field and user can select item to view item's detail.
	 * Including a hyperlink of selected item.
	 */
	public void panelInfo(){
		items = controller.getRSS().getChannel().getItems();
		List<String> titles = new ArrayList<String>();
		
		for(Item t : items){
			titles.add(t.getTitle());
		}
		list = new JList(titles.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listScrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				index = list.getSelectedIndex();
				datailPane.setText("<b><a href='"+ items.get(index).getLink() +"'>"+items.get(index).getTitle()+"</a></b><br><br>" + 
				items.get(index).getDescription()+"<br><br>"+items.get(index).getPubDate());
			}
		});	
	}
}
