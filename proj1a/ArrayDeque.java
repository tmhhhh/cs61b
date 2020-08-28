/**
 * Created by JunhaoW on 04/03/2019
 */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    private int initSize = 8;

    public ArrayDeque() {
        items = (T[]) new Object[initSize];
        size = 0;
        int mid = items.length / 2;
        nextFirst = mid - 1;
        nextLast = mid;
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
        items[nextFirst] = item;
        /* since in java, -5 % 6 == -5, we need to add items.length */
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
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
        String str = "";
        while (cnt < size) {
            T ch = items[start % items.length];
            str += cnt == size - 1 ? ch : (ch + " ");
            cnt++;
            start++;
        }

        System.out.println(str);
    }

    public T removeFirst() {
        if (isEmpty()) {
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
        if (isEmpty()) {
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
        while(index > 0) {
            start++;
            index--;
        }

        return items[start % items.length];
    }

    public T getItem(int index) {
        return items[index];
    }
    public int getLength() {
        return items.length;
    }
}
