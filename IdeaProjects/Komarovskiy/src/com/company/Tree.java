package com.company;

public class Tree{

    public Tree(){
        root=null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean add(int x){

        Node p=root,pp=null;
        boolean isLeft=true,isAdded=true;
        while(p!=null) {
            if (x < p.getInfo()) {
                pp = p;
                p = p.getLeft();
                isLeft = true;
            } else if (x > p.getInfo()) {
                pp = p;
                p = p.getRight();
                isLeft = false;
            } else {
                isAdded = false;
                break;
            }
            if (pp==null)
                root=p;
            if (isAdded){
                p=new Node(x);
            }
            else if(isLeft)
                pp.setLeft(p);
            else
                pp.setRight(p);
        }
        return isAdded;
    }

    public boolean isLEOneSon(Node temp){
            return temp.getLeft()==null||temp.getRight()==null;
    }


    private Node root;
}
