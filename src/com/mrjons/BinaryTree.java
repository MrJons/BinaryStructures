package com.mrjons;

public class BinaryTree {

    /**
     * Topmost Node
     */
    protected Node head = null;

    /**
     * Traverse Tree until value matches Node value or Node is null.
     * Once one of those cases are met return Node (null if not found).
     *
     * @param value
     * @return Node
     */
    public Node search(int value) throws Exception {
        Node currentNode = this.head;

        while (currentNode != null && currentNode.value != value) {
            currentNode = value > currentNode.value ? currentNode.right : currentNode.left;
        }

        if (currentNode == null) {
            throw new Exception("Value not in tree", null);
        }

        return currentNode;
    };

    /**
     * Traverse tree based on passed value until node in direction of travel is null.
     * Create new Node with this value in null position.
     *
     * @param value
     * @return Node
     */
    public Node insert(int value) {
        Node currentNode = this.head;

        Node parentNode = null;
        while (currentNode != null && currentNode.value != value) {
            parentNode = currentNode;
            currentNode = value > currentNode.value ? currentNode.right : currentNode.left;
        }

        if (currentNode == null) {
            currentNode = new Node(value, parentNode);

            if (parentNode != null) {
                if (currentNode.value > parentNode.value) {
                    parentNode.right = currentNode;
                } else {
                    parentNode.left = currentNode;
                }
            } else {
                this.head = currentNode;
            }
        }

        return currentNode;
    };

    /**
     * Search for given value, if found remove from tree & reconnect now separated branches.
     *
     * @param value
     */
    public void delete(int value) throws Exception {

        Node toDelete = this.search(value);

        // if no children, just delete.
        if (toDelete.left == null && toDelete.right == null) {
            this.reassignParentsChild(toDelete, null);
            this.removeNodeReferences(toDelete);
        }

        // if left child with no right child. promote left child
        if (toDelete.left != null && toDelete.right == null) {
            this.reassignParentsChild(toDelete, toDelete.left);
            toDelete.left.parent = toDelete.parent;
            this.removeNodeReferences(toDelete);
        }

        // if right child with left child, promote right childs left most child
        if (toDelete.left == null && toDelete.right != null && toDelete.right.left != null) {
            Node leftmostNode = toDelete.right.left;
            while (leftmostNode.left != null) {
                leftmostNode = leftmostNode.left;
            }

            // Ensure we arent severing any children of the leftmost node
            if (leftmostNode.right != null) {
                leftmostNode.right.parent = leftmostNode.parent;
                this.reassignParentsChild(leftmostNode, leftmostNode.right);
            } else {
                this.reassignParentsChild(leftmostNode, null);
            }

            this.reassignParentsChild(toDelete, leftmostNode);
            toDelete.right.parent = leftmostNode;
            leftmostNode.right = toDelete.right; // assign right tree to promoted node..
            this.removeNodeReferences(toDelete);
        }

        // if right child with no left child promote right child.
        if (toDelete.left == null && toDelete.right != null) {
            this.reassignParentsChild(toDelete, toDelete.right);
            toDelete.right.parent = toDelete.parent;
            this.removeNodeReferences(toDelete);
        }
    }

    /**
     * Process from left-most node, to center node, to right node.
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
    protected Node traverseLeft(Node currentNode) {
        if (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    /**
     * If right Node reference is not null, update current node to be left node.
     */
    protected Node traverseRight(Node currentNode) {
        if (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }

    /**
     * Null all Node references in passed in node.
     *
     * @param toDelete
     */
    protected void removeNodeReferences(Node toDelete) {
        toDelete.parent = null;
        toDelete.left   = null;
        toDelete.right  = null;
    }

    private void reassignParentsChild(Node toDelete, Node newChild) {
        if (toDelete.value > toDelete.parent.value) {
            toDelete.parent.right = newChild;
        } else {
            toDelete.parent.left = newChild;
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

