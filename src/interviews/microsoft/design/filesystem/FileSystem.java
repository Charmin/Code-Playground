package interviews.microsoft.design.filesystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileSystem {
    private Directory root;
    private List<Entry> entries;

    public FileSystem() {
        root = new Directory("");
        entries = new LinkedList<>();
    }

    public void mkdir(String pathName) {
        String[] pn = pathName.split("/");
        Directory parent = root;
        for (int i = 1; i < pn.length; i++) {
            Directory d = new Directory(pn[i]);
            parent.addChild(d);
            parent = d;
        }
    }

    public List<String> ls() {
        List<String> results = new ArrayList<>();
        if (entries.isEmpty()) {
            results.add("/");
            return results;
        }
        for (Entry en : entries) {
            String[] namesAtLevel = en.getName().split("/");
            if (namesAtLevel.length > 1) {
                results.add(namesAtLevel[1]);
            }
        }
        return results;
    }

    public void addContentToFile(String sourceDir, String fileName, String content) {

    }



    //ls
    //mkdir
    //addContentToFile
    //readContentFromFile
}

