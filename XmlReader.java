import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            boolean isCurrentNodeValid = false;
            String currentElementName = "";
            JSONObject json = new JSONObject();

            public void startElement(String uri, String localName,String qName,
                                     Attributes attributes) {
                if (isFieldNameValid(qName)) {
                    isCurrentNodeValid = true;
                    currentElementName = qName;
                }
            }

            public void endElement(String uri, String localName,
                                   String qName) {
                if (isFieldNameValid(qName)) {
                    isCurrentNodeValid = false;
                    currentElementName = "";
                }
            }

            public void characters(char ch[], int start, int length) {
                if (isCurrentNodeValid) {
                    String fieldValue = new String(ch, start, length);
                    json.put(currentElementName, fieldValue);
                }
            }

            private boolean isFieldNameValid(String fieldName) {
                String trimmedFieldName = fieldName.trim();
                for (String validFieldName : validFieldNames) {
                    if (validFieldName.equals(trimmedFieldName)) {
                        return true;
                    }
                }
                return false;
            }
        };

        // Add valid field names to the list of validated field names
        for (String fieldName : fieldNames) {
            String trimmedFieldName = fieldName.trim();
            validFieldNames.add(trimmedFieldName);
        }

        // If there are no valid field names, exit the program
        if (validFieldNames.isEmpty()) {
            System.out.println("No valid field names provided. Exiting program.");
            System.exit(0);
        }

        // Parse the input XML file using the SAX parser and the custom handler
        saxParser.parse("data.xml", handler);

        // Print the JSONObject to the console in JSON format
        System.out.println(handler.json.toString());
    }
}
