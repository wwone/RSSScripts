import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

/*
 * Updated 11/30/2018
 * 
 * Extract titles and content from various RSS 
 * feeds. The XML feed contents are grabbed from  
 * the internet. This code "walks the tree", extracting
 * title and content of each item. Depending on the "style"
 * this information is extracted and made into simple HTML.
 * 
 * Why? 
 * 
 * Glad you asked. Using this system, a bulk download 
 * can be made of a RSS feed, and read (emphasis) offline!
 * 
 * Usage: java thisobject.class CI (or FE) <extracted XML >hopefullygoodd.html
 * 
 * Now Using DOM, rather than DOM4J 
 * 
 * Change to read stdin (not a given filename) 
 * 
 */

public class ReadXMLDOM
{
    /*
     * globals 
     */
	// two flavors at this time
	public static final int FLAVOR_CHANNEL_ITEM = 0; // heirarchy channel/item
	public static final int FLAVOR_FEED_ENTRY = 1; // heirarchy feed/entry

	public int g_flavor = 0; // not really a default
     
    public final static void main(String[] args)
    {

	// read STDIN!

        try
        {
		ReadXMLDOM this_is_it = new ReadXMLDOM();  
		this_is_it.init(args);
		this_is_it.execute();
		this_is_it.finish();
	}
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    } // end main

	public void init(String[] args) throws Exception
	{
		if (args.length < 1)
		{
			throw new Exception("Param 1 MUST be Flavor [CI, FE]");
		}
// debug System.out.println("ARG: " + args[0]);
		if (args[0].equalsIgnoreCase("CI"))
		{
			g_flavor = FLAVOR_CHANNEL_ITEM;
		} // end channel/item flavor
		else
		{
			// not CI, try other(s)
			if (args[0].equalsIgnoreCase("FE"))
			{
				g_flavor = FLAVOR_FEED_ENTRY;
			} // end feed/entry flavor
			else
			{
				throw new Exception("Param 1 MUST be Flavor [CI, FE]");
			}
		} // else not channel/item
	} // end init
    
    public void execute() throws Exception
    {   
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setCoalescing(true); // we want CDATA to be treated as text
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	// stdin is the InputStream source
	Document doc = dBuilder.parse(System.in);
	/* following combines whitespace-separated content
	 otherwise, each line of text is a separate text Node
	doc.getDocumentElement().normalize();
	*/

	/*
	 * Tree walking here is simple and dumb. Don't, we hope,
	 * need XPath
	 */
	switch(g_flavor)
	{
		case FLAVOR_CHANNEL_ITEM:
		{
			channelItemProcessor(doc);
			break;
		} // end channel/item code
		case FLAVOR_FEED_ENTRY:
		{
			feedEntryProcessor(doc);
			break;
		} // end feed/entry code
	} // end switch on flavor
} // execute

	public void finish() throws Exception
	{
	}

	public void channelItemProcessor(Document doc) throws Exception
	{
		NodeList nList_channel = doc.getElementsByTagName("channel");
		for (int ch = 0; ch < nList_channel.getLength(); ch++) {

			Node nNode = nList_channel.item(ch);
					
			// debug System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				NodeList nList_item = eElement.getElementsByTagName("item");
				for (int it = 0; it < nList_item.getLength(); it++) {

					Node nNodei = nList_item.item(it);
					
					// debug System.out.println("\nCurrent Element :" + nNodei.getNodeName());
					
					if (nNodei.getNodeType() == Node.ELEMENT_NODE) {

						Element eElementi = (Element) nNodei;
						/*
						 * if debugging, list all children of "item"
						 * check for DESIRED nodes that are children of "item"
						 * and process them
						 */
						NodeList nList_test = eElementi.getChildNodes();
						for (int tes = 0; tes < nList_test.getLength(); tes++) {
							Node nNodetes = nList_test.item(tes);
	/*
	debugging
							System.out.println("   Node: " +
								tes + ", Name: " +
								nNodetes.getNodeName());
	*/
							if ("title".equals(nNodetes.getNodeName()))
							{
								System.out.println("<h1>" +
								nNodetes.getTextContent() + "</h1>");
							} // end if title node
							if ("content:encoded".equals(nNodetes.getNodeName()))
							{
								// no CDATA is present, just HTML
								System.out.println(
								nNodetes.getTextContent());
							} // end if title node
						} // each child node of item
					} // end if item is Element
				} // end each item  inside channel
			} // end if channel is Element
		} // end each channel 
	} // end channel/item processor

	public void feedEntryProcessor(Document doc) throws Exception
	{
		NodeList nList_entry = doc.getElementsByTagName("entry");
		for (int en = 0; en < nList_entry.getLength(); en++) {

			Node nNode = nList_entry.item(en);
					
			// debug System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				NodeList nList_test = eElement.getChildNodes();
				for (int tes = 0; tes < nList_test.getLength(); tes++) {
				Node nNodetes = nList_test.item(tes);
				/*
				debugging
					System.out.println("   Node: " +
								tes + ", Name: " +
								nNodetes.getNodeName());
				*/
				if ("title".equals(nNodetes.getNodeName()))
				{
					System.out.println("<h1>" +
					nNodetes.getTextContent() + "</h1>");
				} // end if title node
				if ("content".equals(nNodetes.getNodeName()))
				{
					/*
					 * HERE HERE, this is actually just HTML, so we must walk the tree and write it
					 */
					 // appears to be pure HTML
					//System.out.println(
					//	nNodetes.getTextContent());
					if (nNodetes.getNodeType() == Node.ELEMENT_NODE) {
						showTree((Element) nNodetes,0);
					}

				} // end if title node
				} // each child node of entry
			} // end if item is Element
		} // end each entry under root
	} // end feed/entry processor

	/*
	 * debugging tool, displays tree down from given Element
	 */
	public void showTree(Element el, int level) throws Exception
	{
		// debugging System.out.println("\nCurrent Element(" + level + "):" + el.getNodeName());
		if (level > 0)
		{
			System.out.print("<" + el.getNodeName()); // start tag
			NamedNodeMap atts = el.getAttributes();
			if (atts == null)
			{
				System.out.println(">"); // close tag
			}
			else
			{
				// attribute list not null
				if (atts.getLength() == 0)
				{
					// no attributes
					System.out.println(">"); // close tag
				}
				else
				{
					// some attributes, dump them
					printAttributes(el, atts);
					System.out.println(">"); // close tag
				}
			} // end attribute list not null

		} // end if not first in depth
		NodeList nList_test = el.getChildNodes();
		// list all children
		for (int tes = 0; tes < nList_test.getLength(); tes++) {
			Node nNodetes = nList_test.item(tes);
			if (nNodetes.getNodeType() == Node.TEXT_NODE) {
				Text tt = (Text)nNodetes;
				System.out.print(tt.getWholeText()); // no recursion
			}
			if (nNodetes.getNodeType() == Node.ELEMENT_NODE) {
				showTree((Element) nNodetes,level+1); // RECURSE
			}
		}
		if (level > 0)
		{
			System.out.println("</" + el.getNodeName() + ">"); // end tag
		}
	} // end showTree

	public void printAttributes(Element el, NamedNodeMap atts ) throws Exception
	{
		int leng = atts.getLength();
	// debug	System.out.print("Attribute Length: " + leng);
		for (int inner = 0 ; inner < leng ; inner++)
		{
			Node something = atts.item(inner);
			if (something instanceof Attr)
			{
				Attr a = (Attr)something;
				System.out.println(" " +
					a.getName() +
					"=\"" + a.getValue() + "\" ");
			}
		}

	} // end print attributes

} // ReadXMLDOM
