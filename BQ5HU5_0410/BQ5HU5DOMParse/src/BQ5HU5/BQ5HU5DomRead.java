package BQ5HU5;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class BQ5HU5DomRead {

    public static void main(String[] args) {
        try {
            File xmlFajl = new File("BQ5HU5XML.xml");

            DocumentBuilderFactory gyar = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = gyar.newDocumentBuilder();
            Document dokumentum = builder.parse(xmlFajl);
            dokumentum.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + dokumentum.getDocumentElement().getNodeName());
            System.out.println("--------------------------------");

            kiirCsomopont(dokumentum.getDocumentElement(), 0);

        } catch (Exception e) {
            System.out.println("Hiba történt az XML feldolgozása közben.");
            e.printStackTrace();
        }
    }

    private static void kiirCsomopont(Node csomopont, int behuzas) {
        String prefix = " ".repeat(behuzas);

        if (csomopont.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(prefix + "<" + csomopont.getNodeName() + ">");

            NamedNodeMap attributumok = csomopont.getAttributes();
            if (attributumok != null) {
                for (int i = 0; i < attributumok.getLength(); i++) {
                    Node attr = attributumok.item(i);
                    System.out.println(prefix + "  @" + attr.getNodeName() + " = " + attr.getNodeValue());
                }
            }
        }

        NodeList gyerekek = csomopont.getChildNodes();

        for (int i = 0; i < gyerekek.getLength(); i++) {
            Node gyerek = gyerekek.item(i);

            if (gyerek.getNodeType() == Node.ELEMENT_NODE) {
                kiirCsomopont(gyerek, behuzas + 4);
            } else if (gyerek.getNodeType() == Node.TEXT_NODE) {
                String szoveg = gyerek.getTextContent().trim();
                if (!szoveg.isEmpty()) {
                    System.out.println(prefix + "  érték: " + szoveg);
                }
            }
        }
    }
}
