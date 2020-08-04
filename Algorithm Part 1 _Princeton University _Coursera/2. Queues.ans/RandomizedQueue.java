import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[3];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == arr.length) resize(2 * arr.length);
        if (n == 0) arr[n++] = item;
        else {
            int rand = StdRandom.uniform(n);
            Item tmp = arr[rand];
            // swap
            arr[rand] = item;
            arr[n++] = tmp;
        }

    }

    // resize
    private void resize(int len) {
        Item[] copy = (Item[]) new Object[len];
        for (int i = 0; i < n; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (n == arr.length / 4) resize(arr.length / 2);
        int rand = StdRandom.uniform(n);
        Item tmp = arr[rand];
        // swap
        arr[rand] = arr[--n];
        arr[n] = null;
        return tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        return arr[rand];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayList(); // Calling the below class and just that;\
    }

    private class ArrayList implements Iterator<Item> {
        // private int[] A = new int[n];
        private int i = n;

        /* public ArrayList() {
            // A = new int[n];
            i = n;
            for (int j = 0; j > n; j++) {
                A[j] = j;
            }

            StdRandom.shuffle(A[]);
        }*/

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[--i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
