package interviews.microsoft.design.filesystem;

import java.util.LinkedList;
import java.util.List;

public class Directory extends Entry {
    private List<Entry> children;

    public Directory(String name) {
        super(name);
        children = new LinkedList<>();
    }

    public void setChildren(List<Entry> children) {
        this.children = children;
    }

    public List<Entry> getChildren() {
        return children;
    }

    public void addChild(Entry e) {
        children.add(e);
    }
}
