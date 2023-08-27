class Node {
    int key;
    Node left, right, parent;

    Node(int key) {
        this.key = key;
        left = right = parent = null;
    }
}

public class SplayTree {

    Node root;

    // splaying, whenever we add or find a node, rotate it all the way to the root
    private void fixTree(Node node) {
        while (node.parent != null) {

        }
    }

    private void rotateLeft(Node node) {
        Node leftChild = node.left;

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node.parent.left == node) {
            node.parent.left = leftChild;
        } else {
            // otherwise the given node is the right child of the grandparent
            node.parent.right = leftChild;
        }

        // update the parents parent to our left child
        node.parent = leftChild;

        // if our child node has a right pointer, set the parents new left node to that
        if (leftChild.right != null) {
            node.left = leftChild.right;
            // update leftchild's pointer
            leftChild.right.parent = node;
        }

        // update parent to be the right child of our left node
        leftChild.right = node;
    }

    private void rotateRight(Node node) {
        Node rightChild = node.right;

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node.parent.left == node) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        // update parent to this
        node.parent = rightChild;

        if (rightChild.left != null) {
            node.right = rightChild.left;
            // update the childs pointer
            rightChild.left.parent = node;
        }

        rightChild.left = node;
    }
}
