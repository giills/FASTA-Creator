package Data_Access;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Use_Case.FindSequence.FindSequenceDataAccessInterface;
import Use_Case.WriteEntry.WriteEntryDataAccessInterface;

public class FinderDataAccessObject implements FindSequenceDataAccessInterface, WriteEntryDataAccessInterface{
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

    private boolean containsBound(String upperBound, String line){
        String first = "\\s+";
        for (int i = 0; i < upperBound.length(); i++){
                first += "[" + upperBound.charAt(i) + "]";
            }
        String last = first + "";
        first += "\\s+";
        last += "$";
        Pattern patternFirst = Pattern.compile(first); // regex for bounds
                Matcher matcherFirst = patternFirst.matcher(line);
                if(matcherFirst.find()){
                    return true;
                }
        Pattern patternLast = Pattern.compile(last); // regex for bounds
                Matcher matcherLast = patternLast.matcher(line);
                if(matcherLast.find()){
                    return true;
                }        
        return false;
    }

    private ArrayList<String> findRawData(ArrayList<String> bounds){
        try {
            // Initialize reader for the blast results file
            FileInputStream fstream = new FileInputStream(this.blastResults);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String recentScaffold = "";
            String rawSequence = "";
            String lowerBound = bounds.get(1);
            String upperBound = bounds.get(0);

            boolean l = false;
            boolean u = false;
            // Go through lines of text file
            while ((strLine = br.readLine()) != null) {
                // If line contains a scaffold, save it as the most recent one
                Pattern pattern = Pattern.compile("[>].*");
                Matcher matcher = pattern.matcher(strLine);
                if (matcher.find()) {
                    recentScaffold = matcher.group();
                }
                // If lower bound is found
                if (containsBound(lowerBound, strLine)){
                    // If both bounds are on the same line
                    if(containsBound(upperBound, strLine)){
                        rawSequence = strLine;
                        break;
                    }
                    // If upper bound was already found
                    else if (u){
                        rawSequence += strLine;

                        break;
                    }
                    else{
                        rawSequence += strLine;
                        l = true;
                    }
                }
                else if (containsBound(upperBound, strLine)){
                    if (l){
                        rawSequence += strLine;
                        break;
                    }
                    else{
                        rawSequence += strLine;
                        u = true;
                    }
                }
                // If the first bound has been found and the line is from the subject, save the line to raw sequence
                if ((u) | (l)){
                    // If the line hasnt already been added
                    if (!(rawSequence.contains(strLine))){
                        Pattern patternSubject = Pattern.compile("Sbjct(.)*");
                        java.util.regex.Matcher matcherSubject = patternSubject.matcher(strLine);
                        while(matcherSubject.find()){
                        rawSequence += strLine;
                        strLine.replace("Sbjct", "");
                        }
                    }
                }
            }
            ArrayList<String> toReturn = new ArrayList<>();
            toReturn.add(recentScaffold);
            toReturn.add(rawSequence);
            br.close();
            return toReturn;

        }catch (Exception e) {
            addError("Something went wrong finding this sequence");
            return null;
        }
    }

    private ArrayList<String> cleanRawData(ArrayList<String> rawData, String species){
        ArrayList<String> toReturn = new ArrayList<>();
        String name = "";
        String sequence = "";
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
        Matcher matcher = pattern.matcher(rawData.get(0));
        if (matcher.find()) {
            name += matcher.group();
        }
        if (!(name.contains(species.toUpperCase()))){
            name = species.toUpperCase() + "." + name;
        }
        toReturn.add(name);
        if (rawData.get(1).contains("Sbjct")){
            System.out.println(true);
        }
        String temp = rawData.get(1);
        temp = temp.replace("Sbjct", "");
        for (int i = 0; i < temp.length(); i++){
            String check = String.valueOf(temp.charAt(i));
            Pattern patternSeq = Pattern.compile("[A-Z]*[*]*");
            Matcher matcherSeq = patternSeq.matcher(check);
            if (matcherSeq.find()) {
                sequence += matcherSeq.group();
            }
        }
        if (sequence.length() == 0){
            this.error = "somethine went wrong finding the sequence";
        }
        toReturn.add(sequence);
        return toReturn;
    }


    public ArrayList<String> findGene(ArrayList<String> bounds, String species){
        updateFile("files/blast_check_" + species.toUpperCase());
        if (this.error != null){
            return null;
        }
        return cleanRawData(findRawData(bounds), species);   
    }
    
}
