import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Set<V> unsettledNodes;
    private Map<V, Double> distances;
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (V target : graph.adjacencyList(node)) {
                double newDistance = getShortestDistance(node) + getDistance(node, target);
                if (newDistance < getShortestDistance(target)) {
                    distances.put(target, newDistance);
                    edgeTo.put(target, node);
                    unsettledNodes.add(target);
                }
            }
        }
    }

    private double getDistance(V node, V target) {
        for (Vertex<V> vertex : graph.getEdges(node)) {
            if (vertex.getData().equals(target)) {
                Double weight = vertex.getAdjacentVertices().get(vertex);
                if (weight != null) {
                    return weight;
                }
            }
        }
        return 0.0;
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum))
                minimum = vertex;
        }
        return minimum;
    }

    public double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}