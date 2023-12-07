package Use_Case.WriteEntry;

public class WriteEntry implements WriterInputBoundary{
    private WriterOutputBoundary presenter;
    private WriteEntryDataAccessInterface dataAccess;

    public WriteEntry(WriterOutputBoundary presenter, WriteEntryDataAccessInterface dataAccess){
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(String content, String scaffold){
        
    }
}