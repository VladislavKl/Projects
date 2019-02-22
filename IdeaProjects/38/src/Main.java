import java.io.*;

class Tree {

    static class Node {

        private Node(long x) {
            key = x;
            leftHeight = 0l;
            rightHeight = 0l;
            left = null;
            right = null;
            previous = null;
        }

        private long key;
        private Node left;
        private long leftHeight;
        private Node right;
        private long rightHeight;
        private Node previous;
    }

    public Tree() {
        root = null;
        answerNode = null;
    }

    public long getAnswer() {
        return answerNode.key;
    }

    public void add(long x) {
        if (root == null) {
            root = new Node(x);
            return;
        }

        Node newNode = new Node(x);
        Node previous = null;

        for (Node current = root; current != null; ) {
            previous = current;
            if (current.key < newNode.key) {
                ++current.rightHeight;
                current = current.right;
            } else {
                ++current.leftHeight;
                current = current.left;
            }
        }

        if (newNode.key < previous.key) {
            previous.left = newNode;
        } else {
            previous.right = newNode;
        }

        newNode.previous = previous;
    }

    private boolean compare(Node node1, Node node2) {

        if (node1 == null) {
            return false;
        }

        if (node2 == null) {
            if (node1.left != null || node1.right != null) {
                return false;
            }
            answerNode = node1;
            return true;
        }

        if (Math.abs(node1.leftHeight - node2.leftHeight
                + node1.rightHeight - node2.rightHeight) != 1) {
            return false;
        }

        if (node1.leftHeight - 1 == node2.leftHeight) {
            if (!compareHelper(node1.right, node2.right)) {
                return false;
            }
            return compare(node1.left, node2.left);
        }

        if (node1.rightHeight - 1 == node2.rightHeight) {
            if (!compareHelper(node1.left, node2.left)) {
                return false;
            }
            return compare(node1.right, node2.right);
        }

        if (node1.left == null) {
            if (compareHelper(node1.right, node2)) {
                answerNode = node1;
                return true;
            } else {
                return false;
            }
        }

        if (node1.right == null) {
            if (compareHelper(node1.left, node2)) {
                answerNode = node1;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean compareHelper(Node node1, Node node2) {

        if (node1 == null && node2 == null) {
            return true;
        }

        if ((node1 == null && node2 != null)
                || (node1 != null && node2 == null))
            return false;

        if ((node1.leftHeight != node2.leftHeight)
                || (node1.rightHeight != node2.rightHeight))
            return false;

        return (compareHelper(node1.left, node2.left)
                && compareHelper(node1.right, node2.right));
    }

    public boolean solution(Tree tree) {
        if (compare(root, tree.root)) {
            boolean oneSun = true;
            Node tempAnsNode = answerNode;
            for (Node current = answerNode.previous; current != null; ) {
                Node temporary = current;
                if (current.left != null && current.right != null)
                    oneSun = false;
                if (current.left != null) {
                    for (temporary = temporary.left; temporary.right != null; ) {
                        temporary = temporary.right;
                    }
                }
                if (temporary.key == answerNode.key
                        && current.key > answerNode.key) {
                    answerNode = current;
                }
                current = current.previous;
            }
            if (oneSun && tempAnsNode.previous.key > tempAnsNode.key){
                answerNode = tempAnsNode.previous;
            }
            return true;
        }

        return false;
    }

    private Node root;
    private Node answerNode;

}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    public void run() {

        Tree tree1 = new Tree();
        Tree tree2 = new Tree();
        long qOfNodes = 0l;

        try {
            FileInputStream fstream = new FileInputStream("tst.in");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.contains("*"))
                    break;
                tree1.add(Long.parseLong(strLine));
                ++qOfNodes;
            }

            while ((strLine = br.readLine()) != null) {
                tree2.add(Long.parseLong(strLine));
                --qOfNodes;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fout;
        try {
            fout = new FileWriter("tst.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            if (Math.abs(qOfNodes) >= 2) {
                bufferedWriter.write("NO");
                bufferedWriter.close();
                return;
            }
            if (qOfNodes > 0) {
                if (tree1.solution(tree2)) {
                    bufferedWriter.write("YES\n" + Long.toString(tree1.getAnswer()));
                } else {
                    bufferedWriter.write("NO");
                }
            } else {
                if (tree2.solution(tree1)) {
                    bufferedWriter.write("YES\n" + Long.toString(tree2.getAnswer()));
                } else {
                    bufferedWriter.write("NO");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}