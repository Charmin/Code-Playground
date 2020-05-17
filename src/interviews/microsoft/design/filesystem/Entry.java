package interviews.microsoft.design.filesystem;

public abstract class Entry {
    private String name;
    private long size;
    private long createdOn;

    public Entry(String name) {
        this.name = name;
        this.createdOn = System.currentTimeMillis();
        this.size = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
}
