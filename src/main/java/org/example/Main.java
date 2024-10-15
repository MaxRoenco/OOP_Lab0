package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AllTypes.AllTypes;
import org.json.JSONArray;
import org.json.JSONObject;


public class Main {
    public static void main(String[] args) throws IOException {
        String dataString = JsonFile.loadFileString("input.json");
        JSONObject jsonData = new JSONObject(dataString);
        JSONArray inputArray =  jsonData.getJSONArray("input");
        List<AllTypes> allTypes = new ArrayList<>();

        for (int i = 0; i < inputArray.length(); i++) {
            JSONObject alien = inputArray.getJSONObject(i);

            try {
                AllTypes individual = new AllTypes();

                if (alien.has("id")) {
                    individual.setId(alien.getInt("id"));
                }
                if (alien.has("isHumanoid")) {
                    individual.setIsHumanoid(alien.getBoolean("isHumanoid"));
                }
                if (alien.has("planet")) {
                    individual.setPlanet(alien.getString("planet"));
                }
                if (alien.has("age")) {
                    individual.setAge(alien.getInt("age"));
                }
                if (alien.has("traits")) {
                    JSONArray traitsArray = alien.getJSONArray("traits");
                    List<String> traitsList = new ArrayList<>();
                    for (int j = 0; j < traitsArray.length(); j++) {
                        traitsList.add(traitsArray.getString(j));
                    }
                    individual.setTraits(traitsList);
                }

                allTypes.add(individual);
            } catch (Exception e) {
                System.out.println("Error processing alien data: " + e.getMessage());
            }
        }

        for (AllTypes individual : allTypes) {
            individual.verification();
            System.out.println(" ID: " + individual.getId() + " Specie: " + individual.getSpecie() + " Universe: " + individual.getUniverse());
            View view = new View(individual.getUniverse() ,individual.getId(), individual.getSpecie(), individual.getIsHumanoid(), individual.getPlanet(), individual.getAge(), individual.getTraits());
            view.toJson();
            switch (individual.getUniverse()) {
                case "Star Wars" -> view.writeJsonToFile("StarWars.json");
                case "Marvel" -> view.writeJsonToFile("Marvel.json");
                case "Hitchhiker" -> view.writeJsonToFile("Hitchhiker.json");
                case "Lord of the rings" -> view.writeJsonToFile("LordOfTheRings.json");
                case "Unknown" -> view.writeJsonToFile("Unknown.json");
            }
        }


        }
    }
