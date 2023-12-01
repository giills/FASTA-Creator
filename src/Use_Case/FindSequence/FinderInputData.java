package Use_Case.FindSequence;

import java.util.*;

public class FinderInputData {
    private int genes;
    private Map<String, String> bounds;
    private String species;

    public FinderInputData(int genes, Map<String, String> bounds, String species){
        this.genes = genes;
        this.bounds = bounds;
        this.species = species; 
    }
    
    public String getSpecies() {
        return species;
    }
    public Map<String, String> getBounds() {
        return bounds;
    }
    public int getGenes() {
        return genes;
    }
    
}
