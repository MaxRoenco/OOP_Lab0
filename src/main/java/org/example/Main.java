package org.example;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String dataString = JsonFile.loadFileString("input.json");
        JSONObject jsonData = new JSONObject(dataString);
        JSONArray inputArray =  jsonData.getJSONArray("input");
        for(int i = 0; i < inputArray.length(); i++) {
            JSONObject alien = inputArray.getJSONObject(i);
            try {
                System.out.println(alien.getBoolean("isHumanoid"));
            }
            catch(Exception e) {
                System.out.println("DOESN'T EXIST");
            }

        }
    }
}