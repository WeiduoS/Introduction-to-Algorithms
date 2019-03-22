package chapter10;

public class QueueLinkedList<T> {
    private Node head, end;
    private int size;

    private class Node {
        private T val;
        private Node next;

        Node(T val) {
            this.val = val;
        }
    }

    public QueueLinkedList() {
        size = 0;
    }

    protected void add(T value) {
        if (size == 0) {
            end = new Node(value);
            head = end;
            size++;
        } else {
            end.next = new Node(value);
            end = end.next;
            size++;
        }
    }

    protected T remove() {
        if (size == 0)
            throw new java.util.NoSuchElementException();
        T value = head.val;
        head = head.next;
        size--;
        return value;
    }

    protected boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        QueueLinkedList<Integer> queue = new QueueLinkedList<>();
        for (int i = 10; i >= 0; i--) {
            queue.add(i);
            if (i % 2 == 0)
                queue.remove();
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}