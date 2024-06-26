import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class AVL {

    public class Node {
        public int key;
        public int height;
        public Node left, right, parent;

        public Node(int key, Node parent) {
            this.key = key;
            height = 1;
            left = right = null;
            this.parent = parent;
        }
    }

    public class Avl {
        public Node root;

        public Avl() {
            this.root = null;
        }

        public void rotateRight(Node x) {
            if (x == null || x.right == null) {
                return;
            }

            Node y = x.right;

            // set the right pointer of x to the left child of y
            x.right = y.left;
            if (y.left != null) {
                // if the child isn't null, update its parent to x
                y.left.parent = x;
            }

            // set parent of y to parent of x..
            y.parent = x.parent;

            if (x.parent == null) {
                root = y;
                // if the parent was null it was the root.
            } else if (x == x.parent.left) {
                // otherwise, if y wasn't the root, it is either the left or right root.
                // update the parent accordingly
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }

            // set the left child of y as x, since we know that y is > x
            y.left = x;
            x.parent = y;

            updateHeight(x);
            updateHeight(y);
        }

        public void rotateLeft(Node y) {
            if (y == null || y.left == null) {
                return;
            }
            Node x = y.left;

            if (x == null) {
                return; // No rotation is needed if x is null
            }

            // set the left pointer of the y node to the left node's right child.
            y.left = x.right;
            if (x.right != null) {
                // if there was a node, update that node's new parent
                x.right.parent = y;
            }

            // update our left node's parent
            x.parent = y.parent;

            if (y.parent == null) {
                // if the parent was null it was the root.
                root = x;
            } else if (y == y.parent.left) {
                // otherwise, if y wasn't the root, it is either the left or right root.
                // update the parent accordingly
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }

            // set the right pointer of x as y, since we know that y is > x
            x.right = y;
            y.parent = x;

            updateHeight(y);
            updateHeight(x);
        }

        public void fixTree(Node node) {
            while (node != null) {
                updateHeight(node);

                int balance = getBalance(node);

                if (balance > 1) {
                    if (getBalance(node.left) >= 0) {
                        rotateRight(node);
                    } else {
                        rotateLeft(node.left);
                        rotateRight(node);
                    }
                } else if (balance < -1) {
                    if (getBalance(node.right) <= 0) {
                        rotateLeft(node);
                    } else {
                        rotateRight(node.right);
                        rotateLeft(node);
                    }
                }

                node = node.parent;
            }
        }

        int getBalance(Node x) {
            if (x == null) {
                // if the node is null, return 0.
                return 0;
            }

            // else we want to return the height of the left child - right child
            return getHeight(x.left) - getHeight(x.right);
        }

        public void add(int key) {
            // if there is no root, set this at root
            if (root == null) {
                root = new Node(key, null);
                return;
            }

            // iterate through the tree to find a slot for the node key.
            Node parent = null;
            Node node = root;
            while (node != null) {
                if (node.key == key) {
                    return;
                }

                // set the parent as this current node.
                parent = node;
                if (key < node.key) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            // place the node to the left or the right based on the key value compared to
            // the parent
            Node newNode = new Node(key, parent);
            if (key > parent.key) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }

            fixTree(newNode);
        }

        // simpleremove for 1 child or no children.
        public void simpleRemove(Node node) {
            if (node.left != null) {
                if (node.parent == null) {
                    // parent is the root
                    root = node.left;
                }

                // else if the node is the left node of the parent,
                else if (node.parent.left == node) {
                    // set the parent node's left to this nodes left
                    node.parent.left = node.left;
                }

                // otherwise the node is the right of the parent. set the right value of the
                // parent to the left of this node.
                else {
                    node.parent.right = node.left;
                }
            } else if (node.right != null) {
                if (node.parent == null) {
                    // parent is the root
                    root = node.right;
                }

                // else if the node to the left of the parent is the node
                else if (node.parent.left == node) {
                    // remove the left node of the parent
                    node.parent.left = null;
                } else {
                    // otherwise remove the right node of the parent.
                    node.parent.right = null;
                }
            }
        }

        public void remove(int key) {
            Node node = find(key);
            if (node == null) {
                return;
            }

            // if we are in the case where there is 1 child or no children, we can use
            // simpleremove.
            if (node.right == null) {
                simpleRemove(node);
            } else {
                // otherwise, we need to find the next larger node, and set the key to where our
                // removed node is.
                Node nextNode = findMin(node.right);
                simpleRemove(nextNode);
                node.key = nextNode.key;
            }

            // fix tree from the parent after removing
            fixTree(node.parent);
        }

        public Node find(int key) {
            Node currentNode = root;

            while (currentNode != null) {
                if (currentNode.key == key) {
                    return currentNode;
                } else if (key > currentNode.key) {
                    currentNode = currentNode.right;
                } else {
                    currentNode = currentNode.left;
                }
            }

            return null;
        }

        public Node findMin(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private void updateHeight(Node node) {
            if (node == null) {
                return;
            }

            int leftHeight = (node.left != null) ? getHeight(node.left) : 0;
            int rightHeight = (node.right != null) ? getHeight(node.right) : 0;
            node.height = 1 + Math.max(leftHeight, rightHeight);

        }

        public int getHeight(Node node) {
            if (node == null) {
                return 0;
            } else {
                return node.height;
            }
        }

        public Node findNextLarger(Node node) {
            if (node.right != null) {
                return findMin(node.right);
            } else {
                while (node.parent != null && node == node.parent.right) {
                    node = node.parent;
                }
                return node.parent;
            }
        }

        public int getGreatestHeight() {
            return getGreatestHeight(root);
        }

        private int getGreatestHeight(Node node) {
            if (node == null) {
                return 0;
            }

            int leftHeight = getGreatestHeight(node.left);
            int rightHeight = getGreatestHeight(node.right);

            return 1 + Math.max(leftHeight, rightHeight);
        }

    }

    public static String generateRandomString() {
        Random random = new Random();
        int x = random.nextInt(2);
        int y = random.nextInt(Integer.MAX_VALUE);

        return x + " " + y;
    }

    public static void main(String[] args) throws IOException {
        AVL avl = new AVL();
        Avl avlTree = avl.new Avl();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            // String in = reader.readLine();
            String in = generateRandomString();
            String[] numbers = in.split(" ");
            int command = Integer.parseInt(numbers[0]);
            int key = Integer.parseInt(numbers[1]);
            if (command == 0) {
                avlTree.add(key);
            } else {
                if (avlTree.find(key) == null) {
                    // System.out.println(0);
                    out.append(0 + "\n");
                } else {
                    out.append(1 + "\n");
                }
            }
        }
        System.out.println(out.toString());
        System.out.println(avlTree.getGreatestHeight());
    }
}