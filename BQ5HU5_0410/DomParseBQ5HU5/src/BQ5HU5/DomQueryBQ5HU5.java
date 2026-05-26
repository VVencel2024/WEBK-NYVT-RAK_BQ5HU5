package neptunkod;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomQueryBQ5HU5 {

    public static void main(String[] args) {
        try {
            File xmlFajl = new File("XMLBQ5HU5.xml");

            DocumentBuilderFactory gyar = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = gyar.newDocumentBuilder();
            Document dokumentum = builder.parse(xmlFajl);
            dokumentum.getDocumentElement().normalize();

            osszesVendegKiirasa(dokumentum);
            elfogadottFoglalasokKiirasa(dokumentum);
            dragabbEtelekKiirasa(dokumentum, 2000);

        } catch (Exception e) {
            System.out.println("Hiba történt a lekérdezések futtatása közben.");
            e.printStackTrace();
        }
    }

    private static void osszesVendegKiirasa(Document dokumentum) {
        System.out.println("\n1. lekérdezés: Összes vendég neve és e-mail címe");
        NodeList vendegek = dokumentum.getElementsByTagName("vendeg");

        for (int i = 0; i < vendegek.getLength(); i++) {
            Element vendeg = (Element) vendegek.item(i);
            String nev = vendeg.getElementsByTagName("nev").item(0).getTextContent();
            String email = vendeg.getElementsByTagName("email").item(0).getTextContent();

            System.out.println("- " + nev + " | " + email);
        }
    }

    private static void elfogadottFoglalasokKiirasa(Document dokumentum) {
        System.out.println("\n2. lekérdezés: Elfogadott foglalások");
        NodeList foglalasok = dokumentum.getElementsByTagName("foglalas");

        for (int i = 0; i < foglalasok.getLength(); i++) {
            Element foglalas = (Element) foglalasok.item(i);
            String statusz = foglalas.getElementsByTagName("statusz").item(0).getTextContent();

            if (statusz.equals("elfogadva")) {
                String id = foglalas.getAttribute("id");
                String datum = foglalas.getElementsByTagName("datum").item(0).getTextContent();
                String idopont = foglalas.getElementsByTagName("idopont").item(0).getTextContent();
                String letszam = foglalas.getElementsByTagName("letszam").item(0).getTextContent();

                System.out.println("- " + id + " | " + datum + " " + idopont + " | létszám: " + letszam);
            }
        }
    }

    private static void dragabbEtelekKiirasa(Document dokumentum, int alsoHatar) {
        System.out.println("\n3. lekérdezés: " + alsoHatar + " Ft-nál drágább ételek");
        NodeList etelek = dokumentum.getElementsByTagName("etel");

        for (int i = 0; i < etelek.getLength(); i++) {
            Element etel = (Element) etelek.item(i);
            String nev = etel.getElementsByTagName("nev").item(0).getTextContent();
            int ar = Integer.parseInt(etel.getElementsByTagName("ar").item(0).getTextContent());

            if (ar > alsoHatar) {
                System.out.println("- " + nev + " | " + ar + " Ft");
            }
        }
    }
}
