package game;

import bag.SimpleBagInterface;
// Import TestableRandom class
import student.TestableRandom;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Tarini Duvvuri (tarinid)

/**
 * A class that implements the SimpleBagInterface using an array-based data
 * structure.
 *
 * @param <T>
 *            The type of elements stored in the bag.
 * 
 * @author tarinid (906554765)
 * @version 09.12.2023
 */
public class SimpleArrayBag<T> implements SimpleBagInterface<T> {
    private T[] bag;
    private static final int MAX = 18; // Maximum capacity of the bag
    private int numberOfEntries;

    /**
     * Constructs a new SimpleArrayBag with an initial capacity of MAX.
     */
    @SuppressWarnings("unchecked")
    public SimpleArrayBag() {
        T[] tempBag = (T[])new Object[MAX];
        bag = tempBag;
        numberOfEntries = 0;
    }


    /**
     * Adds a new entry to this bag if there is space available and the entry is
     * not null.
     *
     * @return True if the entry was successfully added, false otherwise.
     */
    @Override
    public boolean add(T validentry) {
        if (numberOfEntries < MAX && validentry != null) {
            bag[numberOfEntries] = validentry;
            numberOfEntries++;
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Gets the current number of entries in this bag.
     *
     * @return The number of entries in the bag.
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }


    /**
     * Checks if this bag is empty (contains no entries).
     *
     * @return True if the bag is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    /**
     * Retrieves a random entry from this bag, or null if the bag is empty.
     *
     * @return A random entry from the bag, or null if the bag is empty.
     */
    @Override
    public T pick() {
        if (isEmpty()) {
            return null;
        }
        else {
            TestableRandom generator = new TestableRandom();
            int index = generator.nextInt(numberOfEntries);
            return bag[index];
        }
    }


    /**
     * Private helper method to get the index of a specified entry in the bag.
     *
     * @param anEntry
     *            The entry to find in the bag.
     * @return The index of the entry in the bag, or -1 if not found.
     */
    private int getIndexOf(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(bag[i])) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Removes a specified entry from the bag, if it exists.
     *
     * @return True if the entry was successfully removed, false if not found.
     */
    @Override
    public boolean remove(T validentry) {
        int entryIndex = getIndexOf(validentry);
        if (entryIndex == -1) {
            return false;
        }
        else {
            // Fill the hole with the last entry and decrement numberOfEntries
            bag[entryIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
            return true;
        }
    }
}
