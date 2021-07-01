import java.awt.*;

/** Holds common methods for shapes. */
public interface ModifiableShape {
    /**
     * Scales shape by a given factor.
     * @param scalingFactor scaling factor
     */
    void scale(float scalingFactor);

    /**
     * Moves shape by given offsets.
     * @param x x offset
     * @param y y offset
     */
    void move(float x, float y);

    /**
     * Changes color of a shape.
     * @param color color
     */
    void setColor(Color color);

    /**
     * Returns current color of a shape.
     * @return current color of a shape.
     */
    Color getColor();

    /**
     * Checks if a given point is inside a shape.
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean value
     */
    boolean isInside(float x, float y);
}
