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

    /**
     * Search for given value, if found remove from tree & reconnect now separated branches.
     */
    public void delete(int value) {};

    /**
     * Process from left-most node, to center node, to current node.
     *
     * Gets tree values in ascending order.
     *
     * @param node
     */
    public void inOrderTraverse(Node node) {
        if (node.left != null) {
            this.inOrderTraverse(node.left);
        }

        this.processNode(node);

        if (node.right != null) {
            this.inOrderTraverse(node.right);
        }

        return;
    }

    /**
     * Reset current Node position to Head.
     */
    protected void resetCurrentNodeToHead() {
        this.currentNode = this.head;
    }

    /**
     * Simply print out Node value.
     *
     * @param node
     */
    protected void processNode(Node node) {
        System.out.printf("proccessed %d %n", node.value);
    }

    /**
     * If left Node reference is not null, update current node to be left node.
     */
    protected void traverseLeft() {
        if (this.currentNode.left != null) {
            this.currentNode = this.currentNode.left;
        }
    }

    /**
     * If right Node reference is not null, update current node to be left node.
     */
    protected void traverseRight() {
        if (this.currentNode.right != null) {
            this.currentNode = this.currentNode.right;
        }
    }

    /**
     * Getter for Node at top of tree.
     *
     * @return Node
     */
    protected Node getHead() {
        return this.head;
    }
}

