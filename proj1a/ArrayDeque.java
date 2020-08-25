public class ArrayDeque<T> {
    private int size;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity, int start) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, start, size);
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size + 1, 1);
        } else {
            resize(items.length, 1);
        }
        size += 1;
        items[0] = item;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size + 1, 0);
        }
        items[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = items[0];
        size--;
        resize(size, 0);
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = items[size - 1];
        size--;
        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }
}
