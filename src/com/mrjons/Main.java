package com.mrjons;

public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        int[] values = {50, 93, 74, 94, 75, 21, 19, 20, 37, 60, 52, 49, 51};
        for (int value : values) {
            bt.insert(value);
        }

        bt.inOrderTraverse(bt.getHead());
    }
}
