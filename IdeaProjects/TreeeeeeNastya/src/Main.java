import java.io.*;
import java.util.*;

public class Main implements Runnable{

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }
    public void run() {
        Tree tree = new Tree();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка! Файл отсутствует");
            return;
        }
        while (scanner.hasNext()) {
            tree.insert(scanner.nextInt());
        }
        FileWriter fout;
        try {
            fout = new FileWriter("out.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            tree.heightSonFunc(tree.root);
            tree.lucky(tree.root);
            if (tree.centralNode() != null)
                tree.delete(tree.root, tree.centralNode().key);
            tree.preOrder(tree.root, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//====================================================================================================================
class Tree {

    ArrayList<Node> mas = new ArrayList<>();

    static class Node implements Comparable<Node>{
        int key;
        int height;
        int qSon;
        Node left;
        Node right;

        public Node(int i) {
            key = i;
        }

        @Override
        public int compareTo(Node o) {
            return key - o.key;
        }
    }

    public Node root;


    public void insert(int x) {
        root = doInsert(root, x);
    }

    private static Node doInsert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = doInsert(node.left, x);
        } else
            node.right = doInsert(node.right, x);
        return node;
    }
//====================================================================================================================

    public void preOrder(Node t, BufferedWriter fileWriter) throws IOException {
        if (t != null){
            Integer temp = t.key;
            fileWriter.write(temp.toString() + "\n");
            preOrder(t.left, fileWriter);
            preOrder(t.right, fileWriter);
        }
    }
//======================================================================================================================

    public void heightSonFunc(Node h) { //метки высоты и подсчёт потомков
        if (h != null) {
            if (h.left != null && h.right != null)
            {
                heightSonFunc(h.left);
                heightSonFunc(h.right);
                h.height = Math.max(h.left.height, h.right.height) + 1;
                h.qSon = h.left.qSon + h.right.qSon + 1;
            } else if (h.left == null && h.right != null) {
                heightSonFunc(h.right);
                h.height = h.right.height + 1;
                h.qSon = h.right.qSon + 1;
            } else if (h.left != null && h.right == null) {
                heightSonFunc(h.left);
                h.height = h.left.height + 1;
                h.qSon = h.left.qSon + 1;
            } else {  //if (h.left == null && h.right== null)
                h.height = 0;
                h.qSon = 1;
            }
        }
    }
    //====================================================================================================================
    public void lucky(Node l) {
        if (l != null) {
            if (l.left == null && l.right == null) {
            } else if (l.left != null && l.right != null) {
                if (l.left.height == l.right.height && l.left.qSon != l.right.qSon) {
                    mas.add(l);
                }
                lucky(l.left);
                lucky(l.right);
            } else if (l.left != null && l.right == null) {
                lucky(l.left); //l.check = false;
            } else if (l.left == null && l.right != null) {
                lucky(l.right);

            }
        }

    }
    public Node centralNode() {
        Collections.sort(mas);
        if (mas.size() % 2 == 0) {
            return null;
        }
        else
            return  mas.get(mas.size() / 2);
        }
//===================================================================================
//===================================================================================

    public Node findMin(Node v){
        if (v.left != null)
            return findMin(v.left);
        return v;
    }
    public Node delete(Node v, int x){
        if (v == null)
            return null;
        if (x < v.key) {
            v.left = delete(v.left, x);
            return v;
        }
        if (x > v.key) {
            v.right = delete(v.right, x);
            return v;
        }
        if (v.left == null) {
            return v.right;
        }
        else if (v.right == null) {
            return v.left;
        }
        else {
            int min = findMin(v.right).key;
            v.key = min;
            v.right = delete(v.right, min);
            return v;
        }
    }
}
//===================================================================================
//===================================================================================