package game;

import cs2.Button;
import java.util.Random;
import cs2.CircleShape;
import cs2.Window;
import cs2.WindowSide;
import cs2.Shape;
import cs2.TextShape;
import cs2.SquareShape;
import student.TestableRandom;
import java.awt.Color;
import bag.SimpleBagInterface;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Tarini Duvvuri (tarinid)

/**
 * A class representing the WhackAShape game.
 */
public class WhackAShape {
    private static final String[] STRINGS = { "red circle", "blue circle",
        "red square", "blue square" };
    private SimpleBagInterface<Shape> bag;
    private Window window;
    private TestableRandom randomGenerator;
    private Button quitButton;

    /**
     * Default constructor for WhackAShape.
     * Initializes the game with a bag of shapes, a window, and a Quit button.
     * Fills the bag with random shapes and displays the first shape.
     */
    public WhackAShape() {
        // Initialize the bag (you can choose SimpleArrayBag or SimpleLinkedBag)
        bag = new SimpleArrayBag<>();

        // Initialize the window
        window = new Window();

        // Create and add a Quit button to the window's East side
        quitButton = new Button("Quit");
        window.addButton(quitButton, WindowSide.EAST);
        quitButton.onClick(this, "clickedQuit");

        // Fill the bag with random shapes from STRINGS array
        int size = randomGenerator.nextInt(9) + 6; // Generates a random number
                                                   // between 6 and 14
                                                   // (inclusive)
        for (int i = 0; i < size; i++) {
            String randomString = STRINGS[randomGenerator.nextInt(
                STRINGS.length)];
            Shape shape = buildShape(randomString);
            bag.add(shape);
        }

        // Display the first shape
        Shape firstShape = bag.pick();
        window.addShape(firstShape);
        quitButton.onClick(this, "clickedQuit");
    }


    /**
     * Constructor for WhackAShape that takes an array of shape descriptions.
     * Initializes the game with a bag of shapes, a window, and a Quit button.
     * Converts input descriptions into shapes and adds them to the bag.
     * Displays the first shape.
     *
     * @param inputs
     *            An array of shape descriptions.
     */
    public WhackAShape(String[] inputs) {
        // Initialize the bag (you can choose SimpleArrayBag or SimpleLinkedBag)
        bag = new SimpleArrayBag<>();

        // Initialize the window
        window = new Window();

        // Create and add a Quit button to the window's East side
        quitButton = new Button("Quit");
        window.addButton(quitButton, WindowSide.EAST);
        quitButton.onClick(this, "clickedQuit");

        // Convert input descriptions into shapes and add them to the bag
        for (String input : inputs) {
            try {
                Shape shape = buildShape(input);
                bag.add(shape);
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        // Display the first shape
        Shape firstShape = bag.pick();
        window.addShape(firstShape);
        quitButton.onClick(this, "clickedQuit");
    }


    /**
     * Builds a shape based on the provided input string.
     * Parses the input to determine shape type, size, location, and color.
     *
     * @param input
     *            The input string describing the shape.
     * @return The constructed shape.
     * @throws IllegalArgumentException
     *             If the input string is invalid.
     */
    public Shape buildShape(String input) throws IllegalArgumentException {
        Random randomGenerator = new Random();
        // Determine the size (random between 100 and 200)
        int size = randomGenerator.nextInt(100) + 200;

        // Determine random x and y coordinates
        int maxX = window.getGraphPanelWidth() - size;
        int maxY = window.getGraphPanelHeight() - size;
        int x = randomGenerator.nextInt(maxX);
        int y = randomGenerator.nextInt(maxY);

        // Determine color based on input
        Color color;
        if (input.contains("red")) {
            color = Color.RED;
        }
        else if (input.contains("blue")) {
            color = Color.BLUE;
        }
        else {
            throw new IllegalArgumentException("Invalid color in input: "
                + input);
        }

        // Determine shape type based on input
        Shape currentShape;
        if (input.contains("circle")) {
            currentShape = new CircleShape(x, y, size, color);
        }
        else if (input.contains("square")) {
            currentShape = new SquareShape(x, y, size, color);
        }
        else {
            throw new IllegalArgumentException("Invalid shape type in input: "
                + input);
        }

        // Tie currentShape to the clickedShape method
        currentShape.onClick(this, "clickedShape");

        return currentShape;
    }


    /**
     * Handles a click event on a shape.
     * Removes the clicked shape from the window and the bag.
     * Displays the next shape or a "You Win!" message if the game is over.
     *
     * @param shape
     *            The shape that was clicked.
     */
    public void clickedShape(Shape shape) {
        // Remove the clicked shape from the window and the bag
        window.removeShape(shape);
        bag.remove(shape);

        // Display the next shape or "You Win!" if the game is over
        Shape nextShape = bag.pick();
        if (nextShape == null) {
            TextShape winMessage = new TextShape(window.getGraphPanelHeight()
                / 2, window.getGraphPanelWidth() / 2, "You Win!");
            window.addShape(winMessage);
        }
        else {
            window.addShape(nextShape);
        }
    }


    /**
     * Handles a click event on the Quit button.
     * Exits the game.
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }


    /**
     * Gets the game window.
     *
     * @return The game window.
     */
    public Window getWindow() {
        return window;
    }


    /**
     * Gets the bag of shapes.
     *
     * @return The bag of shapes.
     */
    public SimpleBagInterface<Shape> getBag() {
        return bag;
    }

}
