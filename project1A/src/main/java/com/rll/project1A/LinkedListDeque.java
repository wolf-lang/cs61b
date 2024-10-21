package com.rll.project1A;

public class LinkedListDeque<T> {


    public class Node {

        T item;
        Node next;
        Node pre;

        Node() {
            this.pre = null;
            this.next = null;
            this.item = null;
        }

        Node(T item) {
            this.item = item;
            this.next = null;
        }

        Node(T item, Node pre, Node next) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }

    private Node sentinel;

    LinkedListDeque() {
        sentinel = new Node();
    }
    LinkedListDeque(T item) {
        sentinel = new Node(item);
    }


    public void addFirst(T item) {
        sentinel.pre = new Node(item, null, sentinel);
        sentinel = sentinel.pre;
    }


    public void addLast(T item) {
        sentinel.next = new Node(item, sentinel, sentinel);
        sentinel = sentinel.pre;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public void printDeque() {

    }


    public T removeFirst() {
        return null;
    }

    public T removeLast() {
        return null;
    }

    public T get(int index) {
        return null;
    }

}
