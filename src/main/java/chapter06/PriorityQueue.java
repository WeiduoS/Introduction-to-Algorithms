package chapter06;

/**
 * An Interface defines the basic operation for a personal learning
 * PriorityQueue ADT for generic tye
 */
public interface PriorityQueue<T> {
    /**
     * Add value to priority queue
     */
    public void add(T value);

    /**
     * check whether the priority queue is empty or not
     */
    public boolean isEmpty();

    /**
     * Returns the root element of the PriorityQueue
     * 
     * @return the element at the top of the priority queue
     * @throws IllegalStateException if priority queue is empty
     */
    public T peek();

    /**
     * Deletes and returns the element at the top of the priority queue
     * 
     * @return the element at the top of the priority queue
     * @throws IllegalStateException if priority queue is empty
     */
    public T remove();
}