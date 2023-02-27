
public class LinkedListStack<T> {

    private Node<T> top;

    /**
     * Generic Node class creates generic nodes.
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            next = null;
        } // Node
    } // Node<T>

    /**
     * Push pushes a value onto the stack.
     * @param is the value to be pushed.
     */
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
    } // push

    /**
     * Pop returns the value at the top of the stack and removes it from the stack.
     */
    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        } // if
        T poppedValue = top.value;
        top = top.next;
        return poppedValue;
    } // pop

    /**
     * Peek returns the value at the top of the stack without removing it.
     */
    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        } // if
        return top.value;
    } // peek

    /**
     * IS Empty checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == null;
    } // isEmpty

    /**
     * size returns the size of the stack.
     */
    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        } // while
        return count;
    } // size
} // LinkedListStack
