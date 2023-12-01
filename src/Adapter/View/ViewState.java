package Adapter.View;

import java.util.*;

public class ViewState {
    private Map<String, String> genes = new HashMap<>();
    private String error = null;
    private String result = "";
    private String species = "";
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
    private String location = "";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ViewState() {}

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public Map<String, String> getGenes() {
        return genes;
    }
    public void setGenes(Map<String, String> genes) {
        this.genes = genes;
        setResult(genes);
    }
    public void setResult(Map<String, String> result) {
        String toSet = "<html><body>";
        for (String key : result.keySet()){
            toSet += (key + "With length" + result.get(key).length() + "<br>"+ result.get(key) + "<br><br>");
        }
        toSet += "</body></html>";
        this.result = toSet;
    }
    public String getResult(){
        return result;
    }
}
