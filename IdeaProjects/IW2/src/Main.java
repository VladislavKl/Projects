import java.util.*;
import java.util.stream.Collectors;

class Aggregator {
    private String name;
    public List<Node> nodes;

    public Aggregator(String tName, List<Node> tNodes) {
        name = tName;
        nodes = new ArrayList<>(tNodes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}

class Node {
    private String name;
    private List<Node> nodes;
    private List<Detail> details;

    public Node(String tName, List<Node> tNodes, List<Detail> tDetails) {
        name = tName;
        nodes = new ArrayList<>(tNodes);
        details = new ArrayList<>(tDetails);
    }

    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}

class Detail {
    private String name;
    private int weight;
    private int length, height, width;

    public Detail(String tName, int tWeight, int tLength, int tHeight, int tWidth) {
        name = tName;
        weight = tWeight;
        length = tLength;
        height = tHeight;
        width = tWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Aggregator> aggregators = new ArrayList<>();

        Detail detail1 = new Detail("detail1", 1, 1, 1, 1);
        Detail detail2 = new Detail("detail2", 2, 2, 2, 2);
        Detail detail3 = new Detail("detail3", 3, 3, 3, 3);
        Detail detail4 = new Detail("detail4", 4, 4, 4, 4);
        Detail detail5 = new Detail("detail5", 5, 5, 5, 5);
        Detail detail6 = new Detail("detail6", 6, 6, 6, 6);
        Detail detail7 = new Detail("detail7", 7, 7, 7, 7);

        Node node1;
        Node node2;
        Node node3;
        Node node4;

        List<Node> list1 = new ArrayList<>();
        List<Detail> list2 = new ArrayList<>();
        list2.add(detail1);
        list2.add(detail7);
        list2.add(detail4);
        list2.add(detail6);
        node1 = new Node("Node1", list1, list2);

        list1.clear();
        list2.clear();
        list2.add(detail3);
        node2 = new Node("Node2", list1, list2);

        list1.clear();
        list2.clear();
        list2.add(detail1);
        list2.add(detail4);
        list2.add(detail5);
        node3 = new Node("Node3", list1, list2);

        list1.clear();
        list2.clear();
        list2.add(detail5);
        list2.add(detail6);
        list2.add(detail7);
        node4 = new Node("Node4", list1, list2);


        list1.clear();
        list1.add(node2);
        list1.add(node4);
        aggregators.add(new Aggregator("Aggregator1", list1));

        list1.clear();
        list1.add(node1);
        list1.add(node2);
        list1.add(node3);
        aggregators.add(new Aggregator("Aggregator2", list1));

        list1.clear();
        list1.add(node3);
        aggregators.add(new Aggregator("Aggregator3", list1));

        Map<String, Long> result = aggregators.stream()
                .map(aggregate -> aggregate.nodes)
                .flatMap(nodes -> nodes.stream().map(Node::getDetails)
                        .flatMap(List::stream))
                .collect(Collectors.groupingBy(Detail::getName, Collectors.counting()));
        result.forEach((name, value) -> System.out.println(name + " " + Long.toString(value)));
    }
}
