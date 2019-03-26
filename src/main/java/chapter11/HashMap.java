package chapter11;

public class HashMap {
    int size;
    Node[] list;

    private class Node {
        int key, val;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    HashMap(int n) {
        this.size = n;
        list = new Node[n];
    }

    private int hashCode(int key) {
        if (key < 0)
            return (-key % this.size);
        return key % this.size;
    }

    protected void insert(int key, int val) {
        int pos = hashCode(key);
        Node new_node = new Node(key, val);
        Node cur = list[pos];
        Node head = list[pos];
        while (cur != null) {
            if (cur.key == key)
                return;
            cur = cur.next;
        }
        new_node.next = head;
        list[pos] = new_node;
        return;
    }

    protected Integer search(int key) {
        int pos = hashCode(key);
        Node cur = list[pos];
        while (cur != null) {
            if (cur.key == key)
                return cur.val;
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap(10);
        for (int i = 0; i <= 10; i++) {
            map.insert(i, i + 1);
        }
        System.out.println("key = 1, " + "value =  " + map.search(1));
        System.out.println("key = 2, " + "value =  " + map.search(2));
        System.out.println("key = 10, " + "value =  " + map.search(10));
        System.out.println("key = 3, " + "value =  " + map.search(3));
        System.out.println("key = 11, " + "value =  " + map.search(11));
    }

}