package BQ5HU5;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;

public class xPathBQ5HU5 {
    public static void main(String[] args) {
        try {
            String neptunkod = "studentBQ5HU5.xml";
            File xmlFile = new File(neptunkod);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();

            // 1) Összes student elem, amely a class gyermeke
            kiir(xpath, document, "/class/student");

            // 2) Az a student elem, amely rendelkezik id attribútummal és értéke '02'
            kiir(xpath, document, "/class/student[@id='02']");

            // 3) Összes student elem, függetlenül attól, hol vannak a dokumentumban
            kiir(xpath, document, "//student");

            // 4) A második student elem, amely a class root element gyermeke
            kiir(xpath, document, "/class/student[2]");

            // 5) Az utolsó student elem, amely a class root element gyermeke
            kiir(xpath, document, "/class/student[last()]");

            // 6) Az utolsó előtti student elem, amely a class root element gyermeke
            kiir(xpath, document, "/class/student[last()-1]");

            // 7) Az első két student elem, amelyek a root element gyermekei
            kiir(xpath, document, "/class/student[position()<=2]");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File("studentBQ5HU51.xml")));
            System.out.println("XML fájlba írás kész: studentNeptunkod1.xml");
        } catch (Exception e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }

    private static void kiir(XPath xpath, Document document, String kifejezes) throws Exception {
        System.out.println("
XPath lekérdezés: " + kifejezes);
        NodeList lista = (NodeList) xpath.evaluate(kifejezes, document, XPathConstants.NODESET);
        for (int i = 0; i < lista.getLength(); i++) {
            Element student = (Element) lista.item(i);
            System.out.println("--------------------------");
            System.out.println("id: " + student.getAttribute("id"));
            System.out.println("vezetéknév: " + student.getElementsByTagName("vezeteknev").item(0).getTextContent());
            System.out.println("keresztnév: " + student.getElementsByTagName("keresztnev").item(0).getTextContent());
            System.out.println("becenév: " + student.getElementsByTagName("becenev").item(0).getTextContent());
            System.out.println("kor: " + student.getElementsByTagName("kor").item(0).getTextContent());
        }
    }
}
