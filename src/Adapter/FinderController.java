package Adapter;
import java.util.*;
import Use_Case.FinderInputBoundary;
import Use_Case.FinderInputData;

public class FinderController {
    private FinderInputBoundary finderInteractor;

    public FinderController(FinderInputBoundary finderInteractor){
        this.finderInteractor = finderInteractor;
    }

    public void execute(String species, Map<String, String> bounds){
        FinderInputData data = new FinderInputData(bounds.keySet().size(), bounds, species);
        finderInteractor.execute(data);
    }
}
