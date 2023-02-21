public class LinkedListStack {
    private Node top;

    private class Node {
        char value;
        Node next;

        Node(char value) {
            this.value = value;
            next = null;
        } // Node
    } // Node

    public void push(char value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    } // push

    public char pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        } // if
        char poppedValue = top.value;
        top = top.next;
        return poppedValue;
    } // pop

    public char peek() {
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
        Node current = top;
        while (current != null) {
            count++;
            current = current.next;
        } // while
        return count;
    } // size
} // LinkedListStack
