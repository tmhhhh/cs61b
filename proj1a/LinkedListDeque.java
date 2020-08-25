public class LinkedListDeque<T> {

    private class Node {
        public T item;
        public Node next;
        public Node front;

        public Node(T i, Node n, Node f) {
            item = i;
            next = n;
            front = f;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        head.front = tail;
        head.next = tail;
        tail.next = head;
        tail.front = head;
        size = 0;
    }


    public void addFirst(T item) {
        Node first = new Node(item, head.next, head);
        head.next.front = first;
        head.next = first;
        size += 1;
    }

    public void addLast(T item) {
        Node last = new Node(item, tail, tail.front);
        tail.front.next = last;
        tail.front = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node tmp = head.next;
        while (tmp != tail) {
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = head.next;
        head.next = first.next;
        first.next.front = head;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = tail.front;
        tail.front = last.front;
        last.front.next = tail;
        size--;
        return last.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        Node tmp = head.next;
        while (i < index) {
            i++;
            tmp = tmp.next;
        }

        return tmp.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(index, 0, head.next);
    }

    private T getRecursiveHelp(int index, int i, Node tmp) {
        if (i == index) {
            return tmp.item;
        }
        return getRecursiveHelp(index, i + 1, tmp.next);
    }
    
}
