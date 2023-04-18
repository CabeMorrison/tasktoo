import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.Scanner;

public class XmlToJsonConverter {
  public static void main(String[] args) throws Exception {
    // Create a new Scanner to read user input
    Scanner scanner = new Scanner(System.in);

    // Ask the user for the names of the fields they want to print
    System.out.println("Enter the names of the fields you want to print, separated by commas:");
    String[] fieldNames = scanner.nextLine().split(",");

    // Create a new ArrayList to hold the validated field names
    ArrayList<String> validFieldNames = new ArrayList<>();

    // Validate the user's input
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("data.xml");
    Element root = doc.getDocumentElement();

    for (String fieldName : fieldNames) {
      String trimmedFieldName = fieldName.trim();
      NodeList nodes = root.getElementsByTagName(trimmedFieldName);
      if (nodes.getLength() == 0) {
        System.out.println("Error: " + trimmedFieldName + " is not a valid field name");
      } else {
        validFieldNames.add(trimmedFieldName);
      }
    }

    // If there are no valid field names, exit the program
    if (validFieldNames.isEmpty()) {
      System.out.println("No valid field names provided. Exiting program.");
      System.exit(0);
    }

    // Loop through the child nodes of the root element and build the JSONObject
    JSONObject json = new JSONObject();
    NodeList nodes = root.getChildNodes();

    for (int i = 0; i < nodes.getLength(); i++) {
      Element node = (Element) nodes.item(i);

      for (String fieldName : validFieldNames) {
        NodeList fieldNodes = node.getElementsByTagName(fieldName);
        if (fieldNodes.getLength() > 0) {
          String fieldValue = fieldNodes.item(0).getTextContent();
          json.put(fieldName, fieldValue);
        }
      }
    }

    // Print the JSONObject to the console in JSON format
    System.out.println(json.toString());
  }
}


