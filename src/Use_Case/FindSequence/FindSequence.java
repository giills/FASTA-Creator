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
        ArrayList<String> keys = new ArrayList<>(inputData.getBounds().keySet());
        for (int i = 0; i < inputData.getGenes(); i++){
            ArrayList<String> toSearch = new ArrayList<>();
            toSearch.add(keys.get(i));
            toSearch.add(inputData.getBounds().get(keys.get(i)));
            ArrayList<String> result = this.dataAccess.findGene(toSearch, inputData.getSpecies());
            data.addGene(result.get(0), result.get(1));
        }
        presenter.prepareView(data);
    }
}
