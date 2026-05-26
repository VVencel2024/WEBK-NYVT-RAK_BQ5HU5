package BQ5HU5;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;

public class JSONWriteBQ5HU5 {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("orarendBQ5HU5.json"));
            JSONObject gyoker = (JSONObject) jsonObject.get("BQ5HU5_orarend");
            JSONArray orak = (JSONArray) gyoker.get("ora");

            StringBuilder kimenet = new StringBuilder();
            for (Object obj : orak) {
                JSONObject ora = (JSONObject) obj;
                JSONObject idopont = (JSONObject) ora.get("idopont");
                kimenet.append("--------------------------------
");
                kimenet.append("id: ").append(ora.get("@id")).append("
");
                kimenet.append("típus: ").append(ora.get("@tipus")).append("
");
                kimenet.append("kurzus: ").append(ora.get("kurzus")).append("
");
                kimenet.append("nap: ").append(idopont.get("nap")).append("
");
                kimenet.append("időpont: ").append(idopont.get("tol")).append(" - ").append(idopont.get("ig")).append("
");
                kimenet.append("helyszín: ").append(ora.get("helyszin")).append("
");
                kimenet.append("oktató: ").append(ora.get("oktato")).append("
");
                kimenet.append("szak: ").append(ora.get("szak")).append("
");
            }
            System.out.println(kimenet);

            FileWriter writer = new FileWriter("orarendBQ5HU51.json");
            writer.write(jsonObject.toJSONString());
            writer.close();
            System.out.println("Fájlba írás kész: orarendBQ5HU51.json");
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }
}
