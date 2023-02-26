public class LinkedListStack<T> {
    private Node<T> top;

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            next = null;
        } // Node
    } // Node<T>

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
    } // push

    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        } // if
        T poppedValue = top.value;
        top = top.next;
        return poppedValue;
    } // pop

    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        } // if
        return top.value;
    } // peek

    public boolean isEmpty() {
        return top == null;
    } // isEmpty

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
