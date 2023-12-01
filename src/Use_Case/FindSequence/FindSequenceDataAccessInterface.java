package Use_Case.FindSequence;

import java.util.*;

public interface FindSequenceDataAccessInterface {
    // Returns an ArrayList of 2 strings, the first one is the scaffold and the second one is the gene sequence
    public ArrayList<String> findGene(ArrayList<String> bounds, String species);
}
