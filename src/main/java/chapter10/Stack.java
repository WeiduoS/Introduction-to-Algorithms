package chapter10;

import java.util.Arrays;

public class Stack<T> {
    private static final int DEFAULT_CAPACITY = 12;
    protected T[] array;
    protected int size;

    @SuppressWarnings("unchecked")
    public Stack() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T value) {
        if (size >= array.length - 1) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = value;
    }

    public T remove() {
        T res = this.peek();
        array[size--] = null;
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return array[size - 1];
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 10; i >= 0; i--) {
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.remove());
        }
    }

}