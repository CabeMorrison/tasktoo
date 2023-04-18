import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Scanner;

public class XmlReader {
  public static void main(String[] args) throws Exception {
    // Create a new Scanner to read user input
    Scanner scanner = new Scanner(System.in);

    // Ask the user for the names of the fields they want to print
    System.out.println("Enter the names of the fields you want to print, separated by commas:");
    String[] fieldNames = scanner.nextLine().split(",");

    // Create a new DocumentBuilderFactory
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // Use the factory to create a new DocumentBuilder
    DocumentBuilder builder = factory.newDocumentBuilder();

    // Load the input XML document, parse it and return a new Document object
    Document doc = builder.parse("data.xml");

    // Get the root element of the document
    Element root = doc.getDocumentElement();

    // Get all child nodes of the root element
    NodeList nodes = root.getChildNodes();

    // Loop through the child nodes
    for (int i = 0; i < nodes.getLength(); i++) {
      // Get the current node
      Element node = (Element) nodes.item(i);

      // Loop through the field names provided by the user
      for (String fieldName : fieldNames) {
        // Print the value of the current field if it matches the user's selection
        if (node.getElementsByTagName(fieldName.trim()).getLength() > 0) {
          System.out.println(fieldName.trim() + ": " + node.getElementsByTagName(fieldName.trim()).item(0).getTextContent());
        }
      }
    }
  }
}

