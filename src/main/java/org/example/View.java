package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class View {
    private String universe;
    private String specie;
    private int id;
    private Boolean isHumanoid;
    private String planet;
    private Integer age;
    private List<String> traits;

    View(String universe, int id, String specie, Boolean isHumanoid, String planet, Integer age, List<String> traits) {
        this.universe = universe;
        this.id = id;
        this.specie = specie;
        this.isHumanoid = isHumanoid;
        this.planet = planet;
        this.age = age;
        this.traits = traits;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("universe", universe);
        jsonObject.put("specie", specie);
        jsonObject.put("id", id);
        jsonObject.put("isHumanoid", isHumanoid);
        jsonObject.put("planet", planet);
        jsonObject.put("age", age);
        jsonObject.put("traits", traits);

        return jsonObject.toString();
    }

    public void writeJsonToFile(String filename) {
        try {
            JSONArray jsonArray = new JSONArray();

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                StringBuilder jsonString = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                if (jsonString.length() > 0) {
                    JSONObject existingJson = new JSONObject(jsonString.toString());
                    jsonArray = existingJson.optJSONArray("input");
                    if (jsonArray == null) {
                        jsonArray = new JSONArray();
                    }
                }
            } catch (IOException e) {

                System.out.println("File not found, starting with a new array.");
            } catch (JSONException e) {

                System.out.println("Invalid JSON format in file: " + filename);
                System.out.println("Starting with a new array.");
            }


            JSONObject newEntry = new JSONObject();
            newEntry.put("universe", universe);
            newEntry.put("specie", specie);
            newEntry.put("id", id);
            newEntry.put("isHumanoid", isHumanoid);
            newEntry.put("planet", planet);
            newEntry.put("age", age);
            newEntry.put("traits", traits);


            boolean exists = false;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject existingEntry = jsonArray.getJSONObject(i);
                if (existingEntry.getInt("id") == id && existingEntry.getString("specie").equals(specie)) {
                    exists = true;
                    break;
                }
            }


            if (!exists) {
                jsonArray.put(newEntry);

                JSONObject outputJson = new JSONObject();
                outputJson.put("input", jsonArray);


                try (FileWriter file = new FileWriter(filename)) {
                    file.write(outputJson.toString(4));
                    file.flush();
                    System.out.println("JSON appended to file: " + filename);
                }
            } else {
                System.out.println("Entry with id " + id + " and species " + specie + " already exists. Skipping append.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }


