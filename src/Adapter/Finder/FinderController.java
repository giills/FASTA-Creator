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
        String[] splited = location.split("[\\s | \\n]+");
        Map<String, String> bounds = new HashMap<>();
        for (int i = 0; i < splited.length; i = i+2){
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher matcher = pattern.matcher(splited[i]);
            if (matcher.find()) {
                Pattern patternTwo = Pattern.compile("[0-9]*");
                Matcher matcherTwo = patternTwo.matcher(splited[i+1]);
                if (matcherTwo.find()) {
                    bounds.put(matcher.group(), matcherTwo.group());
                }
            }
        }
        String cleanSpecies = "";
        Pattern patternThree = Pattern.compile("[a-zA-Z]*");
        Matcher matcherThree = patternThree.matcher(species);
        if (matcherThree.find()) {
            cleanSpecies = matcherThree.group();
        }
        FinderInputData data = new FinderInputData(bounds.keySet().size(), bounds, cleanSpecies);
        finderInteractor.execute(data);
    }
}
