package com.mrjons;

/**
 * Represents a single item in a Binary tree, recording its value and position
 */
public class Node {

    int value;
    Node parent;
    Node left  = null;
    Node right = null;

    public Node (int value, Node parent) {
        this.value  = value;
        this.parent = parent;
    }
}
