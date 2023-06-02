import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private Map<V, Vertex<V>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }


    public void addVertex(V v) {
        map.put(v,new Vertex<>(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return;

        map.get(source).addAdjacentVertices(map.get(dest),weight);

        if (undirected)
            map.get(dest).addAdjacentVertices(map.get(source),weight);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : map.values()) {
            count += v.getAdjacentVertices().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).getAdjacentVertices().containsKey(map.get(dest));
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        List<V> vertices = new LinkedList<>();
        for (Vertex<V> vertex : map.get(v).getAdjacentVertices().keySet()) {
            vertices.add(vertex.getData());
        }
        return vertices;
    }

    public Iterable<Vertex<V>> getEdges(V v) {
        if (!hasVertex(v)) return null;
        return map.get(v).getAdjacentVertices().keySet();
    }

    public Iterable<V> getVertices () {
        return map.keySet();
    }

}