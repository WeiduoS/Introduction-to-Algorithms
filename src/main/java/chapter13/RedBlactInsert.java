package chapter13;

// https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
public class RedBlactInsert {
    private final int RED = 0;
    private final int BLACK = 1;

    private class Node {
        int key = -1, color = BLACK;
        Node left = nil, right = nil, parent = nil;

        Node(int key) {
            this.key = key;
        }
    }

    private final Node nil = new Node(-1);
    private Node root = nil;

    private void insert(Node z) {
        Node y = this.nil;
        Node x = this.root;

        while (x != this.nil) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if (y == this.nil) {
            this.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = this.nil;
        z.right = this.nil;
        z.color = RED;

        fixup(z);
    }

    private void fixup(Node node) {
        while (node.parent.color == RED) {
            Node uncle = this.nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle.color == RED) {
                    node.parent.color = BLACK; // case 1
                    uncle.color = BLACK; // case 1
                    node.parent.parent.color = RED; // case 1
                    node = node.parent.parent; // case 1
                    continue;
                } else if (node == node.parent.right) {
                    node = node.parent;// case 2
                    rotateLeft(node);// case 2
                }
                node.parent.color = BLACK; // case 3
                node.parent.parent.color = RED;// case 3
                rotateRight(node.parent.parent);// case 3
            } else {
                uncle = node.parent.parent.left;

                if (uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.left) {
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        x.right = T2;
        if (T2 != nil) {
            T2.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        y.left = T2;

        if (T2 != nil) {
            T2.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == nil) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        x.right = y;
        y.parent = x;
    }

    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(((node.color == RED) ? "Color: Red " : "Color: Black ") + "Key: " + node.key + " Parent: "
                + node.parent.key + "\n");
        printTree(node.right);
    }

    public static void main(String[] args) {
        RedBlactInsert tree = new RedBlactInsert();
        tree.insert(tree.new Node(1));
        tree.insert(tree.new Node(2));
        tree.insert(tree.new Node(3));
        tree.insert(tree.new Node(4));
        tree.insert(tree.new Node(6));
        tree.insert(tree.new Node(7));
        tree.insert(tree.new Node(8));
        tree.insert(tree.new Node(9));
        System.out.println(tree.root.key);
        tree.printTree(tree.root);
    }
}