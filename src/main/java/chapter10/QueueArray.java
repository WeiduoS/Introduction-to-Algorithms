package chapter10;

public class QueueArray<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 12;
    private int size, head, end;

    @SuppressWarnings("unchecked")
    public QueueArray() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    private void resize(int n) {
        T[] temp = (T[]) new Object[n];
        for (int i = 0; i < size; i++) {
            temp[i] = array[(head + i) % array.length];
        }
        array = temp;
        head = 0;
        end = size;
    }

    protected void add(T value) {
        if (array.length == size)
            this.resize(array.length * 2);
        array[end++] = value;
        if (end == array.length)
            end = 0;
        size++;
    }

    protected T remove() {
        if (size == 0)
            throw new java.util.NoSuchElementException();
        T value = array[head];
        array[head++] = null;
        if (head == array.length)
            head = 0;
        if (--size > 0 && size == array.length / 4)
            this.resize(array.length / 2);
        return value;
    }

    protected boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        QueueArray<Integer> queue = new QueueArray<>();
        for (int i = 10; i >= 0; i--) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}