package game;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Tarini Duvvuri (tarinid)

/**
 * The main class to run the WhackAShape game.
 */
@SuppressWarnings("unused")
public class ProjectRunner {
    public static void main(String[] args) {
        // Check if command-line arguments are provided
        if (args.length > 0) {
            // Call the constructor with input descriptions
            WhackAShape game = new WhackAShape(args);
        }
        else {
            // Call the default constructor
            WhackAShape game = new WhackAShape();
        }
    }
}
