package com.company;

public class Param{

    Param(int _value, Node _curr, Node _next){value=_value;curr=_curr;next=_next;}

    public int getValue() {
        return value;
    }

    public Node getCurr() {
        return curr;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setCurr(Node curr) {
        this.curr = curr;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private Node curr, next;
    private int value;

}
