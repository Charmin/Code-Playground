package algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 18/11/18.
 */
public class Graph<T> {

    private Map<GraphVertex<T>, List<GraphVertex<T>>> adjList = new HashMap<>();

    public Map<GraphVertex<T>, List<GraphVertex<T>>> getAdjList() {
        return adjList;
    }
}

class GraphVertex<T> {

    T id;
    GraphVertex<T> parent;

    public GraphVertex(T id) {
        this.id = id;
    }

    public void setParent(GraphVertex<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphVertex<?> vertex = (GraphVertex<?>) o;

        return id != null ? id.equals(vertex.id) : vertex.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}