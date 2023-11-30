package Data_Access;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Use_Case.FindSequence.FindSequenceDataAccessInterface;

public class FinderDataAccessObject implements FindSequenceDataAccessInterface{
    private File blastResults;
    private String error = null;
    public FinderDataAccessObject(){}

    private void updateFile(String filePath){
        try {
            File tempFile = new File(filePath);
            if (!(tempFile.createNewFile())) {
                this.blastResults = tempFile;
            } else {
                this.error = "File for this species does not exist";
            }
        } catch (IOException e) {
            this.error = "An error finding this file occurred";
        }
    }

    private void addError(String newError){
        this.error = newError;
    }

    public ArrayList<String> findRawData(ArrayList<String> bounds) throws Exception{
        try {
            // Initialize reader for the blast results file
            FileInputStream fstream = new FileInputStream(this.blastResults);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String recentScaffold = "";
            // bounds found: -1 after upper bound is found, 0 when neither have been found
            // 1 when upper bound has been found in the same iterations, 2 otherwise
            int boundsFound = 0;
            String rawSequence = "";
            String lowerBound = "\\s";
            String upperBound = "[^0-9]*";
            for (int i = 0; i < bounds.get(0).length(); i++){
                lowerBound += "[" + bounds.get(0).charAt(i) + "]";
            }
            lowerBound += "\\s[^0-9]*";
            for (int i = 0; i < bounds.get(1).length(); i++){
                upperBound += "[" + bounds.get(1).charAt(i) + "]";
            }
            // Go through lines of text file
            while ((strLine = br.readLine()) != null) {
                // If line contains a scaffold, save it as the most recent one
                Pattern pattern = Pattern.compile("[>].*");
                Matcher matcher = pattern.matcher(strLine);
                if (matcher.find()) {
                    recentScaffold = matcher.group();
                }
                // If line contains the number of the first bound (ALONE), save the line to raw sequence
                Pattern patternBounds = Pattern.compile(lowerBound); // regex for bounds
                Matcher matcherBounds = patternBounds.matcher(strLine);
                if(matcherBounds.find()){
                    boundsFound = 2;
                    rawSequence += matcherBounds.group();
                }
                //If the lines contains the number of the second bound (ALONE), save the line and end
                Pattern patternUpperBound = Pattern.compile(upperBound); // regex for bounds
                Matcher matcherUpperBound = patternUpperBound.matcher(strLine);
                if(matcherUpperBound.find()){
                    boundsFound = -1;
                    rawSequence += matcherUpperBound.group();
                }
                // If the first bound has been found and the line is from the subject, save the line to raw sequence
                if (boundsFound > 0){
                    if (boundsFound == 1){
                        Pattern patternSubject = Pattern.compile("Sbjct(.)*");
                        java.util.regex.Matcher matcherSubject = patternSubject.matcher(strLine);
                        while(matcherSubject.find()){
                        rawSequence += strLine;
                        }
                    }
                    else{
                        boundsFound = 1;
                    }
                }
                if (boundsFound == -1){
                    break;
                }
            } 
            ArrayList<String> toReturn = new ArrayList<>();
            toReturn.add(recentScaffold);
            toReturn.add(rawSequence);
            return toReturn;

        }catch (Exception e) {
            addError("Something went wrong finding this sequence");
            return null;
        }
    }


    public ArrayList<String> findGene(ArrayList<String> bounds, String species){
        updateFile("files/blast_check_" + species.toUpperCase());
        if (this.error != null){
            return null;
        }
        try{
            return findRawData(bounds);
        } catch(Exception e)
        {
            ArrayList<String> ret = new ArrayList<>();
            ret.add("AKHHHHHHHHHHHHHH");
            return ret;
        }
    }
    
}
