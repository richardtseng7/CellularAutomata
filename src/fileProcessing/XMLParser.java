package fileProcessing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import cells.Cell;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to parse XML files.
 * 
 * @author Richard Tseng
 */

public class XMLParser {

	public enum simulationType{
		GAMEOFLIFE, SEGREGATION, FIRE, WATOR
	}
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"simulationType",
			"cellType",
			"numberOfColumns",
			"numberOfRows"
	});
	
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private final DocumentBuilder DOCUMENT_BUILDER;
    
    //A hashmap of the parameters read in from the XML file
    private Map<String, String> param = new HashMap<String, String>();
    
    //2D array of integers representing the cells in the grid
    private Integer[][] grid;
    
    /**
     * Create a parser for XML files of given type.
     */
    public XMLParser (File xmlFile) {
        DOCUMENT_BUILDER = getDocumentBuilder();
        parseXMLFile(xmlFile);
    }

    /**
     * Get the data contained in this XML file and store it
     */
    public void parseXMLFile(File xmlFile) {
    		Element root = getRootElement(xmlFile);
        if (!isValidFile(root)) {
            throw new XMLException("XML file does not represent a supported simulation.");
        }
		try {
			Document document = DOCUMENT_BUILDER.parse(xmlFile);
			document.getDocumentElement().normalize();
	        readParameters(document);
	        makeGrid(document);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void readParameters(Document document) {
		NodeList parameters = getChildren(document, "parameter");
    		for (int i = 0; i < parameters.getLength(); i++) {
    			Node parameter = parameters.item(i);
    			if (parameter.getNodeType() == Node.ELEMENT_NODE) {
        			param.put( parameter.getNodeName(), parameter.getTextContent());
    			}
    		}
    }
    
    private void makeGrid(Document document) {
    		int numRows = Integer.parseInt(param.get("numberOfRows"));
		int numColumns = Integer.parseInt(param.get("numberOfColumns"));
		grid = new Integer[numRows][numColumns];
		NodeList g = getChildren(document, "grid");
		for (int i = 0; i < numRows*2; i++) {
			Node row = g.item(i);
			if (row.getNodeType() == Node.ELEMENT_NODE) {
				String cells = row.getTextContent();
				for (int j = 0; j < numColumns; j++) {
					grid[i/2][j] = cells.charAt(j) - '0';
				}	
			}	
		}
    }
    
    private NodeList getChildren(Document document, String tagName) {
    		NodeList nodeList = document.getElementsByTagName(tagName);
    		return nodeList.item(0).getChildNodes();
    }  

    // Get root element of an XML file
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }

    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root) { 
    		for (simulationType s : simulationType.values()) {
    			if (getAttribute(root, "type").equals(s.name())) {
    				return true;
    			}
    		}
        return false;
    }

    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
     
    /*
     * Returns HashMap of parameters
     */
    public Map<String, String> getParameters(){
    		return param;
    }
    
    /*
     * Returns grid of cells
     */
    public Integer[][] getGrid(){
    		return grid;
    }
}