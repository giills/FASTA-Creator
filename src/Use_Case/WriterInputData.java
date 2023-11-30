package Use_Case;
public class WriterInputData{
    private String content;
    private String name;
    private String scaffold;

    public WriterInputData(String content, String scaffold){
        this.content = content;
        this.name = scaffold + "x";
        this.scaffold = scaffold;
    }
}