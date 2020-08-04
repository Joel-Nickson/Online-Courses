import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

    }

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Must not be null");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty()) last = first;
        else oldfirst.prev = first;

        size++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Must not be null");
        Node prelast = last;
        last = new Node();
        last.item = item;
        last.prev = prelast;
        last.next = null;
        if (isEmpty()) first = last;
        else prelast.next = last;

        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("No more elements");
        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("No more elements");
        Item item = last.item;
        if (size == 1) {
            last = null;
            first = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;

        return item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        // we need to use a build-in iterator package for this case and ListIterator is a pretty neat interface
        // ListIterator class is used to iterate through the linked list , just to print the elements in it. 
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Node eachElement = first;

        @Override
        public boolean hasNext() {
            return (eachElement != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = eachElement.item;
            eachElement = eachElement.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        System.out.println(deque.isEmpty());
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(1);
        System.out.println(deque.removeFirst());
        deque.addFirst(2);
        System.out.println(deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);

        // for (Integer i : deque) {
        //  System.out.println(" " + i);
        // }
    }
    /*
    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) test.addFirst(i);
            else test.addLast(i);
        }
        System.out.println(test.isEmpty());
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) test.removeLast();
            else test.removeFirst();
        }
        System.out.println(test.isEmpty());
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) test.addLast(i);
            else test.addFirst(i);
        }
        System.out.println(test.size());
        for (int i = 0; i < 1000; i++) {
            test.removeLast();
        }
        System.out.println(test.isEmpty());
    }*/

}
