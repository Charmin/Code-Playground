package interviews.microsoft.design.filesystem;

public class File extends Entry {
    private String type;

    public File(String name, String type) {
        super(name);
        this.type = type;
    }
}
