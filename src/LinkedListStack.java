public class LinkedListStack<T> {
    private Node<T> top;

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            next = null;
        }
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        T poppedValue = top.value;
        top = top.next;
        return poppedValue;
    }

    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
