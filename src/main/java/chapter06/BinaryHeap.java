package chapter06;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private static final int DEFAULT_CAPACITY = 12;
    protected T[] array;
    protected int size;

    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T value) {
        if (size >= array.length - 1) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[++size] = value;
        precolateUp();
    }

    protected void precolateUp() {
        int index = size;
        while (index > 1 && array[index].compareTo(array[index / 2]) < 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    public T remove() {
        T res = this.peek();
        array[1] = array[size];
        array[size--] = null;
        precolateDown();
        return res;
    }

    protected void precolateDown() {
        int index = 1;
        while (index * 2 <= size) {
            int left = index * 2;
            int right = index * 2 + 1;
            int smaller = left;
            if (right <= size && array[right].compareTo(array[left]) < 0) {
                smaller = right;
            }
            if (array[smaller].compareTo(array[index]) < 0) {
                swap(smaller, index);
            }
            index = smaller;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException();
        return array[1];
    }

    public String toString() {
        return Arrays.toString(array);
    }

    protected void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> pq = new BinaryHeap<>();
        for (int i = 10; i >= 0; i--) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }
}
