package BQ5HU5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomReadBQ5HU5 {

    public static void main(String[] args) {
        try {
            File inputFile = keresXmlFajlt();

            if (inputFile == null) {
                System.out.println("HIBA: Nem találom az XML fájlt.");
                System.out.println("Tedd az XML fájlt a projekt főmappájába vagy az src/BQ5HU5 mappába.");
                System.out.println("Elfogadott fájlnevek:");
                System.out.println("- XMLBQ5HU5.xml");
                System.out.println("- XMLBQ5HU5");
                return;
            }

            System.out.println("Beolvasott XML fájl:");
            System.out.println(inputFile.getAbsolutePath());
            System.out.println();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + document.getDocumentElement().getNodeName());
            System.out.println();

            kiirGondozok(document);
            kiirAllatok(document);
            kiirOrokbefogadok(document);
            kiirVizsgalatok(document);
            kiirJelentkezesek(document);

            File outputFile = new File(inputFile.getParentFile(), "BQ5HU5MENTES.xml");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);

            transformer.transform(source, result);

            System.out.println();
            System.out.println("A dokumentum mentése sikeres:");
            System.out.println(outputFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Hiba történt az XML feldolgozása közben.");
            e.printStackTrace();
        }
    }

    private static File keresXmlFajlt() {
        String[] lehetsegesFajlok = {
                "XMLBQ5HU5.xml",
                "XMLBQ5HU5",
                "src/BQ5HU5/XMLBQ5HU5.xml",
                "src/BQ5HU5/XMLBQ5HU5",
                "../XMLBQ5HU5.xml",
                "../XMLBQ5HU5"
        };

        for (String fajlNev : lehetsegesFajlok) {
            File fajl = new File(fajlNev);

            if (fajl.exists() && fajl.isFile()) {
                return fajl;
            }
        }

        return null;
    }

    private static void kiirGondozok(Document document) {
        NodeList lista = document.getElementsByTagName("gondozo");

        System.out.println("===== GONDOZÓK =====");

        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                System.out.println("Gondozó azonosító: " + elem.getAttribute("id"));
                System.out.println("Név: " + getText(elem, "vezeteknev") + " " + getText(elem, "keresztnev"));
                System.out.println("Email: " + getText(elem, "email"));
                System.out.println("Munkakör: " + getText(elem, "munkakor"));
                System.out.println();
            }
        }
    }

    private static void kiirAllatok(Document document) {
        NodeList lista = document.getElementsByTagName("allat");

        System.out.println("===== ÁLLATOK =====");

        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                System.out.println("Állat azonosító: " + elem.getAttribute("id"));
                System.out.println("Gondozó referencia: " + elem.getAttribute("gondozoRef"));
                System.out.println("Név: " + getText(elem, "nev"));
                System.out.println("Faj: " + getText(elem, "faj"));
                System.out.println("Fajta: " + getText(elem, "fajta"));
                System.out.println("Kor: " + getText(elem, "kor"));
                System.out.println("Szín: " + getText(elem, "szin"));
                System.out.println("Méret: " + getText(elem, "meret"));
                System.out.println("Ismertetőjegy: " + getText(elem, "ismertetoJegy"));
                System.out.println();
            }
        }
    }

    private static void kiirOrokbefogadok(Document document) {
        NodeList lista = document.getElementsByTagName("orokbefogado");

        System.out.println("===== ÖRÖKBEFOGADÓK =====");

        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                System.out.println("Örökbefogadó azonosító: " + elem.getAttribute("id"));
                System.out.println("Név: " + getText(elem, "vezeteknev") + " " + getText(elem, "keresztnev"));
                System.out.println("Email: " + getText(elem, "email"));
                System.out.println("Város: " + getText(elem, "varos"));
                System.out.println("Utca: " + getText(elem, "utca") + " " + getText(elem, "hazszam"));
                System.out.println();
            }
        }
    }

    private static void kiirVizsgalatok(Document document) {
        NodeList lista = document.getElementsByTagName("vizsgalat");

        System.out.println("===== ORVOSI VIZSGÁLATOK =====");

        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                System.out.println("Vizsgálat azonosító: " + elem.getAttribute("id"));
                System.out.println("Állat referencia: " + elem.getAttribute("allatRef"));
                System.out.println("Dátum: " + getText(elem, "datum"));
                System.out.println("Típus: " + getText(elem, "tipus"));
                System.out.println("Diagnózis: " + getText(elem, "diagnozis"));
                System.out.println("Gyógyszer: " + getText(elem, "gyogyszer"));
                System.out.println("Adagolás: " + getText(elem, "adagolas"));
                System.out.println("Időtartam: " + getText(elem, "idotartam"));
                System.out.println();
            }
        }
    }

    private static void kiirJelentkezesek(Document document) {
        NodeList lista = document.getElementsByTagName("jelentkezes");

        System.out.println("===== JELENTKEZÉSEK =====");

        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                System.out.println("Jelentkezés azonosító: " + elem.getAttribute("id"));
                System.out.println("Állat referencia: " + elem.getAttribute("allatRef"));
                System.out.println("Örökbefogadó referencia: " + elem.getAttribute("orokbefogadoRef"));
                System.out.println("Dátum: " + getText(elem, "datum"));
                System.out.println("Motiváció: " + getText(elem, "motivacio"));
                System.out.println("Státusz: " + getText(elem, "statusz"));
                System.out.println("Megjegyzés: " + getText(elem, "megjegyzes"));
                System.out.println();
            }
        }
    }

    private static String getText(Element parent, String tagName) {
        NodeList lista = parent.getElementsByTagName(tagName);

        if (lista.getLength() > 0) {
            return lista.item(0).getTextContent();
        }

        return "";
    }
}