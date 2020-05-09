package com.mrjons;

public class BinaryTree {

    /**
     * Topmost Node
     */
    protected Node head = null;

    /**
     * Set during traversal to mark current Node
     */
    protected Node currentNode;

    /**
     * Traverse Tree until value matches Node value or Node is null.
     * Once one of those cases are met return Node (null if not found).
     *
     * @param value
     * @return Node
     */
    public Node search(int value) {
        this.currentNode = this.head;

        while (this.currentNode != null && this.currentNode.value != value) {
            this.currentNode = value > this.currentNode.value ? this.currentNode.right : this.currentNode.left;
        }

        return this.currentNode;
    };

    /**
     * Traverse tree based on passed value until node in direction of travel is null.
     * Create new Node with this value in null position.
     *
     * @param value
     * @return Node
     */
    public Node insert(int value) {
        this.currentNode = this.head;

        Node parentNode = null;
        while (this.currentNode != null && this.currentNode.value != value) {
            parentNode = this.currentNode;
            this.currentNode = value > this.currentNode.value ? this.currentNode.right : this.currentNode.left;
        }

        if (this.currentNode == null) {
            this.currentNode = new Node(value, parentNode);

            if (parentNode != null) {
                if (this.currentNode.value > parentNode.value) {
                    parentNode.right = this.currentNode;
                } else {
                    parentNode.left = this.currentNode;
                }
            } else {
                this.head = this.currentNode;
            }
        }

        return this.currentNode;
    };

}

