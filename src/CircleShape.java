import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Optional;

/** Represents a circle. */
public class CircleShape extends Ellipse2D.Float implements ModifiableShape {
    /** Color of a shape */
    private Color color;

    /**
     * Basic constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param r radius
     * @param color Optional with color of a shape, default color is black if empty.
     */
    public CircleShape(float x, float y, float r, Optional<Color> color) {
        super(x, y, r, r);
        if(color.isPresent())
            this.color = color.get();
        else
            this.color = new Color(0,0,0);
    }

    /** {@inheritDoc} */
    @Override
    public void scale(float scalingFactor) {
        this.width += scalingFactor;
        this.height += scalingFactor;
    }
    /** {@inheritDoc} */
    @Override
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
    /** {@inheritDoc} */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    /** {@inheritDoc} */
    @Override
    public Color getColor() {
        return this.color;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isInside(float x, float y) {
        return this.contains(x,y);
    }
}
