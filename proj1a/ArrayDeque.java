/**
 * Created by JunhaoW on 04/03/2019
 */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    private int initSize = 4;

    public ArrayDeque() {
        items = (T[]) new Object[initSize];
        size = 0;
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        int startPos = newArr.length / 2 - size / 2;
        int oldIndex = nextFirst + 1;
        int newIndex = startPos;
        int cnt = 0;
        while (cnt < size) {
            newArr[newIndex] = items[oldIndex % items.length];
            newIndex++;
            oldIndex++;
            cnt++;
        }

        items = newArr;
        nextFirst = startPos - 1;
        nextLast = nextFirst + size + 1;
    }

    private void resizeDown() {
        resize(size * 2 < initSize ? initSize : size * 2);
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[nextFirst] = item;
        /* since in java, -5 % 6 == -5, we need to add items.length */
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextFirst + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int cnt = 0;
        int start = nextFirst + 1;
        while (cnt < size) {
            System.out.print(items[start % items.length] + " ");
            cnt++;
            start++;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }
        nextFirst = (nextFirst + 1) % items.length;
        T first = items[nextFirst];
        items[nextFirst] = null;
        size--;

        return first;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }
        nextLast = ((nextLast - 1) + items.length) % items.length;
        T last = items[nextLast];
        items[nextLast] = null;
        size--;

        return last;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int start = nextFirst + 1;
        start += index;

        return items[start % items.length];
    }

    public T getItem(int index) {
        return items[index];
    }
    public int getLength() {
        return items.length;
    }
}
