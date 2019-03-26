package chapter10;

public class LinkedList {
    Node head, end;
    int length;

    private class Node {
        int val;
        Node prev, next;

        Node(int n) {
            this.val = n;
            this.next = null;
            this.prev = null;
        }
    }

    LinkedList() {
        length = 0;
    }

    protected void setHead(int n) {
        Node new_node = new Node(n);
        new_node.next = head;
        new_node.prev = null;
        if (head != null)
            head.prev = new_node;
        head = new_node;
        if (end == null)
            end = head;
        length++;
    }

    protected void setEnd(int n) {
        Node new_node = new Node(n);
        new_node.prev = end;
        new_node.next = null;
        if (end != null)
            end.next = new_node;
        end = new_node;
        if (head == null)
            head = end;
        length++;
    }

    protected int getByIndex(int index) {
        if (index < 0 || index >= this.length)
            throw new IllegalStateException();
        int count = 0;
        Node cur = head;
        while (count != index) {
            cur = cur.next;
            count++;
        }
        return cur.val;
    }

    protected void addAtIndex(int index, int val) {
        if (index < 0 || (index >= this.length && this.length != 0))
            throw new IllegalStateException();
        Node new_node = new Node(val);
        Node cur = head;
        int count = 0;
        if (index == 0) {
            setHead(val);
            return;
        }
        if (index == this.length - 1) {
            setEnd(val);
            return;
        }
        while (count != index - 1) {
            cur = cur.next;
            count++;
        }
        Node after = cur.next;
        cur.next = new_node;
        new_node.prev = cur;
        new_node.next = after;
        after.prev = new_node;
        this.length++;
    }

    protected void deleteAtIndex(int index) {
        if (index < 0 || (index >= this.length && this.length != 0))
            throw new IllegalStateException();
        Node cur = head;
        int count = 0;
        if (index == 0 && length > 0) {
            head = head.next;
            if (head != null)
                head.prev = null;
        } else if (index == length - 1) {
            end = end.prev;
            if (end != null)
                end.next = null;
        } else {
            Node pre = null;
            while (count != index) {
                pre = cur;
                cur = cur.next;
                count++;
            }
            pre.next = cur.next;
            cur.next.prev = pre;
        }
        this.length--;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addAtIndex(0, 0);
        list.addAtIndex(0, 1);
        list.addAtIndex(0, 2);
        list.addAtIndex(0, 3);
        LinkedList.Node cur = list.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

        System.out.println(" ");
        list.deleteAtIndex(1);
        cur = list.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

        System.out.println(" ");
        System.out.println("get by index 2: " + list.getByIndex(2));

        System.out.println(" ");
        while (list.length > 0) {
            list.deleteAtIndex(list.length - 1);
            cur = list.head;
            System.out.println(" ");
            while (cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
        }
    }
}