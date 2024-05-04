package ADTs;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {
    private int size;
    private int pointer;
    private static int CAPACITY = 100000;
    private T[] items;

    public ArrayList() {
        size = 0;
        pointer = 0;
        items = (T[]) new Object[CAPACITY];
    }

    // shift all elements from index one position to the right
    private void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
    }

    // shift all elements from the end one position to the left
    // until index
    private void shiftLeft(int index) {
        for (int i = index + 1; i < size; i++) {
            items[i - 1] = items[i];
        }
    }

    public boolean add(T value) {
        if (size >= items.length) {
            // Optionally, increase the size of the items array if needed
            ensureCapacity();
        }
        items[size++] = value;
        return true;
    }
    
    private void ensureCapacity() {
        int newCapacity = items.length * 2;
        T[] newItems = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reset() {
        pointer = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }

    @Override
    public boolean hasNext() {
        return (pointer < size);
    }

    @Override
    public T next() {
        pointer++;
        return items[pointer - 1];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertAt(int index, T value) {
        if (index > size) {
            return false;
        }
        shiftRight(index);
        items[index] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertBefore(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(searchValue)) {
                return insertAt(i, value);
            }
        }
        return false;
    }

    @Override
    public boolean insertAfter(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(searchValue)) {
                return insertAt(i + 1, value);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index >= size) {
            return false;
        }
        shiftLeft(index);
        size--;
        return true;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return items[currentIndex++];
            }
        };
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T set(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldElement = items[index];
        items[index] = element;
        return oldElement;
    }


public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        for (String item : list) {
            System.out.println(item);
        }
    }
}