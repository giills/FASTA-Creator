package Adapter.Writer;
import java.util.*;

import Use_Case.WriteEntry.WriterInputBoundary;

public class WriterController{
    
    private WriterInputBoundary writerInteractor;

    public WriterController(WriterInputBoundary writerInteractor){
        this.writerInteractor = writerInteractor;
    }

    public void execute(String species, Map<String, String> bounds){
        


    }
}