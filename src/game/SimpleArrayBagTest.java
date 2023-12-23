package game;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Tarini Duvvuri (tarinid)

/**
 * A test class for testing the methods of the SimpleArrayBag class.
 * 
 * @author tarinid (906554765)
 * @version 09.12.2023
 */
public class SimpleArrayBagTest extends student.TestCase {
    /**
     * This setup method initializes the necessary objects and configurations
     * required for testing the SimpleLinkedBag class.
     */
    public void setUp() {
        // You can add any setup code here if needed
    }


    /**
     * Test the add method of the SimpleArrayBag class.
     */
    public void testAddMethod() {
        SimpleArrayBag<Integer> bag = new SimpleArrayBag<>();

        assertTrue(bag.add(42));
        assertTrue(bag.add(7));
        assertTrue(bag.add(15));

        // Test adding items that should fail
        assertFalse(bag.add(null)); // Should fail because null is not allowed
        assertFalse(bag.add(null)); // Should fail because null is not allowed
    }


    /**
     * Test the getCurrentSize method of the SimpleArrayBag class.
     */
    public void testGetCurrentSizeMethod() {
        SimpleArrayBag<String> bag = new SimpleArrayBag<>();
        bag.add("apple");
        bag.add("banana");
        bag.add("cherry");

        int size = bag.getCurrentSize();

        // Verify that the size is as expected
        assertEquals(3, size);
    }


    /**
     * Test the isEmpty method of the SimpleArrayBag class.
     */
    public void testIsEmptyMethod() {
        SimpleArrayBag<String> emptyBag = new SimpleArrayBag<>();
        SimpleArrayBag<String> nonEmptyBag = new SimpleArrayBag<>();
        nonEmptyBag.add("item");

        boolean isEmpty1 = emptyBag.isEmpty();
        boolean isEmpty2 = nonEmptyBag.isEmpty();

        // Verify that isEmpty returns the correct results
        assertTrue(isEmpty1);
        assertFalse(isEmpty2);
    }


    /**
     * Test the pick method of the SimpleArrayBag class.
     */
    public void testPickMethod() {
        // Create an instance of your SimpleArrayBag class
        SimpleArrayBag<Integer> bag = new SimpleArrayBag<>();

        // Test when the bag is empty
        assertNull(bag.pick());

        // Add some items to the bag
        bag.add(10);
        bag.add(20);
        bag.add(30);

        // Use a loop to pick items repeatedly and verify they are within the
        // expected range
        int min = 10; // Minimum expected value
        int max = 30; // Maximum expected value
        int numberOfPicks = 1000; // Number of picks to make

        for (int i = 0; i < numberOfPicks; i++) {
            Integer pickedItem = bag.pick();
            assertNotNull(pickedItem); // Picked item should not be null
            assertTrue(pickedItem >= min && pickedItem <= max); // Picked item
                                                                // should be
                                                                // within the
                                                                // expected
                                                                // range
        }
    }


    /**
     * Test the remove method of the SimpleArrayBag class.
     */
    public void testRemoveMethod() {
        SimpleArrayBag<String> bag = new SimpleArrayBag<>();
        bag.add("apple");
        bag.add("banana");
        bag.add("cherry");

        boolean result1 = bag.remove("banana");
        boolean result2 = bag.remove("grape"); // Not in the bag

        // Verify that items are removed as expected
        assertTrue(result1);
        assertFalse(result2);
    }
}
