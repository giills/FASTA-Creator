package Use_Case.FindSequence;

import java.util.*;
public class FinderOutputData {
    private Map<String, String> genes;
    private String message;
    
    public FinderOutputData(){
        this.genes = new HashMap<String, String>();
    }

    public void addGene(String scaffold, String sequence){
        int i = 2;
        while (genes.containsKey(scaffold)){
            scaffold = scaffold + "." + i;
            i++;
        }
        this.genes.put(scaffold, sequence);
    }
    public void addMessage(String message){
        this.message = message;
    }

    public Map<String, String> getGenes(){
        return genes;
    }
    public String getMessage(){
        return message;
    }

}
