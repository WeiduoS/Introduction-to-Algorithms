package chapter13;

import java.util.HashMap;
import java.util.Map;

public class RedBlackTreeDelete {
    private final int RED = 0;
    private final int BLACK = 1;

    private class Node {
        Node left, right, parent;
        int key, color;

        Node(int key) {
            this.key = key;
            this.color = BLACK;
        }
    }

    private Node nil = new Node(-1);
    private Node root = nil;

    private void insert(Node node) {
        Node cur = root;
        Node parent = nil;

        while (cur != nil) {
            parent = cur;
            if (cur.key > node.key) {
                cur = cur.left;
            } else if (cur.key < node.key) {
                cur = cur.right;
            }
        }

        node.parent = parent;

        if (parent == this.nil) {
            this.root = node;
        } else if (node.key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        node.left = nil;
        node.right = nil;
        node.color = RED;

        fixup(node);
    }

    private void fixup(Node node) {
        while (node.parent.color == RED) {
            Node uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.right) {
                    node = node.parent;
                    leftRotate(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rightRotate(node.parent.parent);

            } else if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left;

                if (uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } else if (node == node.parent.left) {
                    node = node.parent;
                    rightRotate(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                leftRotate(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        x.right = T2;

        if (T2 != nil) {
            T2.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == nil) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        y.left = T2;

        if (T2 != nil) {
            T2.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == nil) {
            this.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        x.right = y;
        y.parent = x;
    }

    public void printTree(Node node) {
        if (node == nil)
            return;
        printTree(node.left);
        System.out.println(((node.color == RED) ? "Color RED " : "Color Black ") + "Key " + node.key + " Parent "
                + node.parent.key);
        printTree(node.right);
    }

    private void delete(Node z) {
        Node y = z;
        Node x;
        int y_original_color = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = findSuccessor(z.right);
            y_original_color = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (y_original_color == BLACK) {
            delete_fixup(x);
        }
    }

    private void delete_fixup(Node x) {
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == RED) {
                    w.color = BLACK;// case 1
                    x.parent.color = RED;// case 1
                    leftRotate(x.parent);// case 1
                    w = x.parent.right;// case 1
                }
                if (w.left.color == BLACK && w.right.color == BLACK) { // case 2
                    w.color = RED;// case 2
                    x = x.parent;// case 2
                    continue;
                } else if (w.right.color == BLACK) {
                    w.left.color = BLACK; // case 3
                    w.color = RED;// case 3
                    rightRotate(w);// case 3
                    w = x.parent.right;// case 3
                }
                if (w.right.color == RED) {
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == RED) {
                    w.color = BLACK;// case 1
                    x.parent.color = RED;// case 1
                    rightRotate(x.parent);// case 1
                    w = x.parent.left;// case 1
                }
                if (w.left.color == BLACK && w.right.color == BLACK) { // case 2
                    w.color = RED;// case 2
                    x = x.parent;// case 2
                    continue;
                } else if (w.left.color == BLACK) {
                    w.right.color = BLACK; // case 3
                    w.color = RED;// case 3
                    leftRotate(w);// case 3
                    w = x.parent.left; // case 3
                }
                if (w.left.color == RED) {
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    private void transplant(Node target, Node with) {
        if (target.parent == nil) {
            this.root = with;
        } else if (target == target.parent.left) {
            target.parent.left = with;
        } else {
            target.parent.right = with;
        }
        with.parent = target.parent;
    }

    private Node findSuccessor(Node node) {
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        RedBlackTreeDelete tree = new RedBlackTreeDelete();
        Map<Integer, RedBlackTreeDelete.Node> map = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            map.put(i, tree.new Node(i));
        }
        tree.insert(map.get(1));
        tree.insert(map.get(2));
        tree.insert(map.get(3));
        tree.insert(map.get(4));
        tree.insert(map.get(5));
        tree.insert(map.get(6));
        tree.insert(map.get(7));
        tree.insert(map.get(8));
        System.out.println(tree.root.key);
        tree.printTree(tree.root);
        tree.delete(map.get(1));
        System.out.println("delete node key 1 ---------");
        tree.printTree(tree.root);
        tree.delete(map.get(2));
        System.out.println("delete node key 2 ---------");
        tree.printTree(tree.root);
        tree.delete(map.get(3));
        System.out.println("delete node key 3 ---------");
        tree.printTree(tree.root);
    }
}