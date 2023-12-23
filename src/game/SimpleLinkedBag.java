package game;

import bag.Node;
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
 * A class that implements the SimpleBagInterface using a singly linked list.
 *
 * @param <T>
 *            The type of elements stored in the bag.
 * 
 * @author tarinid (906554765)
 * @version 09.12.2023
 */
public class SimpleLinkedBag<T> implements SimpleBagInterface<T> {
    private Node<T> firstNode; // Reference to the first node
    private int numberOfEntries;

    /**
     * Constructs a new SimpleLinkedBag with an empty linked list.
     */
    public SimpleLinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
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
     * Adds a new entry to this bag if the entry is not null.
     *
     * @return True if the entry was successfully added, false otherwise.
     */
    @Override
    public boolean add(T anEntry) {
        if (anEntry != null) {
            Node<T> newNode = new Node<>(anEntry);
            newNode.setNext(firstNode); // Prepend the new node to the linked
                                        // list
            firstNode = newNode; // Update the firstNode reference
            numberOfEntries++;
            return true;
        }
        else {
            return false;
        }
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
            Node<T> currentNode = firstNode;

            // Traverse the linked list to the selected index
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }

            return currentNode.getData();
        }
    }


    /**
     * Removes a specified entry from the bag if it exists.
     *
     * @return True if the entry was successfully removed, false if not found.
     */
    @Override
    public boolean remove(T anEntry) {
        Node<T> entryNode = getReferenceTo(anEntry);

        if (entryNode == null) {
            return false; // Entry not found
        }
        else {
            // Copy the data from the first node to the entry node
            entryNode.setData(firstNode.getData());

            // Update the first node to skip the removed node
            firstNode = firstNode.getNext();

            numberOfEntries--;
            return true;
        }
    }


    /**
     * Private helper method to get a reference to the node containing a
     * specified entry.
     *
     * @param anEntry
     *            The entry to search for.
     * @return A reference to the node containing the entry, or null if not
     *         found.
     */
    private Node<T> getReferenceTo(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;

        // Traverse the linked list while searching for the entry
        while (!found && currentNode != null) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            }
            else {
                currentNode = currentNode.getNext();
            }
        }

        return currentNode;
    }

}
