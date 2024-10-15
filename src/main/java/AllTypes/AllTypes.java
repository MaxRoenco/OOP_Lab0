package AllTypes;

import java.util.*;

public class AllTypes {
    private String universe;
    private String specie;
    private int id;
    private Boolean isHumanoid;
    private String planet;
    private Integer age;
    private List<String> traits;

    // getters and setters
    public String getUniverse() {
        return universe;
    }
    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Boolean getIsHumanoid() {
        return isHumanoid;
    }

    public void setIsHumanoid(Boolean isHumanoid) {
        this.isHumanoid = isHumanoid;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void verification() {
        List<String> probabilities = new ArrayList<>();
        if (this.planet != null) {
            switch (this.planet) {
                case "Earth":
                    probabilities.add("Elf");
                    probabilities.add("Dwarf");
                    break;
                case "Asgard":
                    probabilities.add("Asgardian");
                    break;
                case "Betelgeuse":
                    probabilities.add("Betelgeusian");
                    break;
                case "Vogsphere":
                    probabilities.add("Vogons");
                    break;
                case "Kashyyyk":
                    probabilities.add("Wookie");
                    break;
                case "Endor":
                    probabilities.add("Ewok");
                    break;
                default:
                    probabilities.add("Unknown");
                    break;
            }
//            System.out.println(probabilities.toString());
        }

        checkProbabilities(probabilities);

        if (specie == null && isHumanoid != null) {
            if (isHumanoid) {
                probabilities.add("Asgardian");
                probabilities.add("Betelgeusian");
                probabilities.add("Elf");
                probabilities.add("Dwarf");
            } else {
                probabilities.add("Vogons");
                probabilities.add("Ewok");
                probabilities.add("Wookie");
            }
        }

        checkProbabilities(probabilities);

        if (specie == null && age != null) {
            if (age > 5000) {
                probabilities.add("Elf");
            } else if (age > 400) {
                probabilities.add("Elf");
                probabilities.add("Asgardian");
            } else if (age > 200) {
                probabilities.add("Wookie");
                probabilities.add("Elf");
                probabilities.add("Asgardian");
            } else if (age > 100) {
                probabilities.add("Wookie");
                probabilities.add("Elf");
                probabilities.add("Asgardian");
                probabilities.add("Dwarf");
                probabilities.add("Vogons");
            } else if (age > 60) {
                probabilities.add("Wookie");
                probabilities.add("Elf");
                probabilities.add("Asgardian");
                probabilities.add("Dwarf");
                probabilities.add("Vogons");
                probabilities.add("Betelgeusian");
            } else {
                probabilities.add("Wookie");
                probabilities.add("Elf");
                probabilities.add("Asgardian");
                probabilities.add("Dwarf");
                probabilities.add("Vogons");
                probabilities.add("Betelgeusian");
                probabilities.add("Ewok");
            }
            }

            checkProbabilities(probabilities);

            if (specie == null && traits != null && !traits.isEmpty()) {
//                System.out.println(traits.size() + " " + !traits.isEmpty());
                if (traits.size() == 2) {
                    if (traits.contains("HAIRY") && traits.contains("TALL")) {
                        probabilities.add("Wookie");
                    } else if (traits.contains("HAIRY") && traits.contains("SHORT")) {
                        probabilities.add("Ewok");
                    } else if (traits.contains("BLONDE") && traits.contains("TALL")) {
                        probabilities.add("Asgardian");
                    } else if (traits.contains("EXTRA_ARMS") && traits.contains("EXTRA_HEAD")) {
                        probabilities.add("Betelgeusian");
                    } else if (traits.contains("GREEN") && traits.contains("BULKY")) {
                        probabilities.add("Vogons");
                    } else if (traits.contains("BLONDE") && traits.contains("POINTY_EARS")) {
                        probabilities.add("Elf");
                    } else if (traits.contains("SHORT") && traits.contains("BULKY")) {
                        probabilities.add("Dwarf");
                    } else {
                        probabilities.add("Unknown");
                    }
                } else if (traits.size() == 1) {
                    if (traits.contains("HAIRY")) {
                        probabilities.add("Wookie");
                        probabilities.add("Ewok");
                    } else if (traits.contains("TALL")) {
                        probabilities.add("Asgardian");
                        probabilities.add("Wookie");
                    } else if (traits.contains("SHORT")) {
                        probabilities.add("Ewok");
                        probabilities.add("Dwarf");
                    } else if (traits.contains("BLONDE")) {
                        probabilities.add("Asgardian");
                        probabilities.add("Elf");
                    } else if (traits.contains("EXTRA_ARMS") || traits.contains("EXTRA_HEAD")) {
                        probabilities.add("Betelgeusian");
                    } else if (traits.contains("GREEN")) {
                        probabilities.add("Vogons");
                    } else if (traits.contains("BULKY")) {
                        probabilities.add("Vogons");
                        probabilities.add("Dwarf");
                    } else if (traits.contains("POINTY_EARS")) {
                        probabilities.add("Elf");
                    } else {
                        probabilities.add("Unknown");
                    }
                }
            }
            checkProbabilities(probabilities);
        if (probabilities.size() == 1) {
            setSpecie(probabilities.getFirst());
        } else {
            setSpecie("Unknown specie");
        }
        putUniverse();
    }

    private void checkProbabilities(List<String> probabilities) {
//        System.out.println("Before filtering: " + probabilities);
        if (!probabilities.isEmpty()) {
            List<String> mostFrequentElements = getMostFrequentElements(probabilities);
            probabilities.clear();
            probabilities.addAll(mostFrequentElements);
        }
//        System.out.println("After filtering: " + probabilities);
    }

    private void putUniverse(){
            switch (this.specie) {
                case "Wookie", "Ewok" -> this.setUniverse("Star Wars");
                case "Betelgeusian", "Vogons" -> this.setUniverse("Hitchhiker");
                case "Asgardian" -> this.setUniverse("Marvel");
                case "Elf", "Dwarf" -> this.setUniverse("Lord of the rings");
                default -> this.setUniverse("Unknown");
            }
        }

    private List<String> getMostFrequentElements(List<String> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String item : list) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        int maxCount = Collections.max(frequencyMap.values());

        List<String> mostFrequentElements = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostFrequentElements.add(entry.getKey());
            }
        }

        return mostFrequentElements;
    }
}
