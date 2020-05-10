package com.mrjons;

public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        int[] values = {50, 93, 71, 94, 75, 21, 19, 20, 37, 60, 52, 49, 51, 95, 79, 78, 77};
        for (int value : values) {
            bt.insert(value);
        }

        bt.inOrderTraverse(bt.getHead());

        System.out.printf("Now we delete a reference and re-traverse %n");

        try {
            bt.delete(75);
            bt.inOrderTraverse(bt.getHead());

        } catch (Exception e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
