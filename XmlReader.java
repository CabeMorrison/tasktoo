import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReader {
  public static void main(String[] args) throws Exception {
    // Create a new SAXParserFactory
    SAXParserFactory factory = SAXParserFactory.newInstance();

    // Use the factory to create a new SAXParser
    SAXParser parser = factory.newSAXParser();

    // Parse the input XML file using a custom DefaultHandler
    parser.parse(new File("data.xml"), new DefaultHandler() {
      private boolean isField = false;

      // Called when the parser encounters a start tag
      public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("field")) {
          isField = true;
        }
      }

      // Called when the parser encounters an end tag
      public void endElement(String uri, String localName, String qName) {
        if (qName.equals("field")) {
          isField = false;
        }
      }

      // Called when the parser encounters character data
      public void characters(char[] ch, int start, int length) {
        if (isField) {
          String value = new String(ch, start, length);
          System.out.println(value);
        }
      }
    });
  }
}
