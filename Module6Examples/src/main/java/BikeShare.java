import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class BikeShare {
    public static void main(String[] args) {
        String sULR = "https://api.mockaroo.com/api/e9cc2e00?count=20&key=e99c5530";
        try{
            URL netURL = new URL(sULR);

            // create a connection to make a http request
            URLConnection conn = netURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            InputStream inputStream = conn.getInputStream();

            // reading the input stream as XML content
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            // getting all station objects from XML
            NodeList nList = doc.getElementsByTagName("station");

            // for each station found
            for(int i = 0; i < nList.getLength(); i++){
                Element itemElement = (Element)nList.item(i);

                // read all properties from a station
                String name = getTagValue("name", itemElement);
                String id = getTagValue("id", itemElement);
                String lat = getTagValue("lat", itemElement);
                String lon = getTagValue("long", itemElement);
                String num = getTagValue("nbBikes", itemElement);

                System.out.println(name + " " + id + " " + lat + " " + lon + " " + num);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static String getTagValue(String tagName, Element element){
        NodeList tags = element.getElementsByTagName(tagName);
        if(tags.getLength()> 0){
            return tags.item(0).getTextContent();
        }
        return null;
    }
}
