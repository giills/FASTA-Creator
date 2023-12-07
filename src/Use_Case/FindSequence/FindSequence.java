package Use_Case.FindSequence;

import java.util.*;

public class FindSequence implements FinderInputBoundary{
    private FinderOutputBoundary presenter;
    private FindSequenceDataAccessInterface dataAccess;

    public FindSequence(FinderOutputBoundary presenter, FindSequenceDataAccessInterface dataAccess){
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(FinderInputData inputData){
        FinderOutputData data = new FinderOutputData();
        ArrayList<Map<String, String>> bounds = inputData.getBounds();
        // Iterate over each gene to find, bounds.get(i) is each iteration
        for (int i = 0; i < inputData.getGenes(); i++){
            ArrayList<String> keys = new ArrayList<>(bounds.get(i).keySet());
            ArrayList<String> result = new ArrayList<>();
            result.add("");
            result.add("");
            int prev = 0;
            boolean reverse = (Integer.valueOf(keys.get(0)) < Integer.valueOf(bounds.get(i).get((keys.get(0)))));
            // Iterate over each part of the gene to find
            for (String key : keys){
                ArrayList<String> toSearch = new ArrayList<>();
                toSearch.add(key);
                toSearch.add(bounds.get(i).get(key));
                ArrayList<String> thisResult = this.dataAccess.findGene(toSearch, inputData.getSpecies());
                result.set(0, thisResult.get(0));
                // If lower bound was higher than previous lower bound, add to end
                // If lower bound was lower that previous lower bound, add to start
                int lowest;
                if (Integer.valueOf(key) > Integer.valueOf(bounds.get(i).get(key))){
                    lowest = Integer.valueOf(bounds.get(i).get(key));
                }
                else{
                    lowest = Integer.valueOf(key);
                }
                if (((lowest > prev) & reverse) | ((lowest > prev) & !(reverse))){
                    result.set(1, (thisResult.get(1) + result.get(1)));
                }
                else{
                    result.set(1, (result.get(1) + thisResult.get(1)));
                }
                prev = lowest;
            }
            data.addGene(result.get(0), result.get(1));    
        }
        presenter.prepareView(data);
    }
}
