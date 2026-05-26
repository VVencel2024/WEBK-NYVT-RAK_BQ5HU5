package BQ5HU5;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JSONReadBQ5HU5 {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("orarendBQ5HU5.json"));
            JSONObject gyoker = (JSONObject) jsonObject.get("BQ5HU5_orarend");
            JSONArray orak = (JSONArray) gyoker.get("ora");

            for (Object obj : orak) {
                JSONObject ora = (JSONObject) obj;
                JSONObject idopont = (JSONObject) ora.get("idopont");

                System.out.println("--------------------------------");
                System.out.println("id: " + ora.get("@id"));
                System.out.println("típus: " + ora.get("@tipus"));
                System.out.println("kurzus: " + ora.get("kurzus"));
                System.out.println("nap: " + idopont.get("nap"));
                System.out.println("tól: " + idopont.get("tol"));
                System.out.println("ig: " + idopont.get("ig"));
                System.out.println("helyszín: " + ora.get("helyszin"));
                System.out.println("oktató: " + ora.get("oktato"));
                System.out.println("szak: " + ora.get("szak"));
            }
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }
}
