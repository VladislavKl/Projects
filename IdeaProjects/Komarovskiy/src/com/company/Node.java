package com.company;

public class Node{
    Node(int _info, Node _Left, Node _Right){ info =_info; left =_Left; right =_Right;}
    Node(){this(0,null,null);}
    Node(int _info){this(_info,null,null);}
    Node(Node _left, Node _right){this(0,_left,_right);}

    public void setInfo(int info) {
        this.info = info;
    }

    public int getInfo() {
        return info;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private int info;
    private Node left, right;
}
