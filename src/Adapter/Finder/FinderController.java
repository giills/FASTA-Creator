package Adapter.Finder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Use_Case.FindSequence.FinderInputBoundary;
import Use_Case.FindSequence.FinderInputData;

public class FinderController {
    private FinderInputBoundary finderInteractor;

    public FinderController(FinderInputBoundary finderInteractor){
        this.finderInteractor = finderInteractor;
    }

    public void execute(String species, String location){
        // go from string to map string string
        ArrayList<Map<String, String>> bounds = new ArrayList<>();
        String[] splited = location.split("\\n+");
        for (int i = 0; i < splited.length; i++){
            bounds.add(new HashMap<String, String>());
            String[] single = location.split("\\s+");
            for (int j = 0; j < single.length; j = j+2){
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(single[j]);
                if (matcher.find()) {
                    Pattern patternTwo = Pattern.compile("[0-9]+");
                    Matcher matcherTwo = patternTwo.matcher(single[j+1]);
                    if (matcherTwo.find()) {
                        
                        bounds.get(i).put(matcher.group(), matcherTwo.group());
                    }
                }
            }   
        }
        String cleanSpecies = "";
        Pattern patternThree = Pattern.compile("[a-zA-Z]*");
        Matcher matcherThree = patternThree.matcher(species);
        if (matcherThree.find()) {
            cleanSpecies = matcherThree.group();
        }
        FinderInputData data = new FinderInputData(bounds.size(), bounds, cleanSpecies);
        finderInteractor.execute(data);
    }
}
