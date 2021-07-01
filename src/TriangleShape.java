import java.awt.*;
import java.util.Optional;

/** Represents an isosceles right triangle. */
public class TriangleShape extends Polygon implements ModifiableShape {
    /** Color of a shape */
    private Color color;
    /** Height of a triangle */
    private int height;
    /**
     * Basic constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param height height
     * @param color Optional with color of a shape, default color is black if empty.
     */
    public TriangleShape(int x, int y, int height, Optional<Color> color) {
        int[] xpoints = {x, x, x+height};
        int[] ypoints = {y, y+height,y+height};
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.npoints = 3;

        this.height = height;

        if(color.isPresent())
            this.color = color.get();
        else
            this.color = new Color(0,0,0);
    }
    /** {@inheritDoc} */
    @Override
    public void scale(float scalingFactor) {
        int factor = (int)scalingFactor;

        int x = this.xpoints[0];
        int y = this.ypoints[0];

        int[] newXPoints = {x, x, x + height + factor};
        int[] newYPoints = {y, y + height + factor, y + height + factor};

        this.height += factor;

        this.xpoints = newXPoints;
        this.ypoints = newYPoints;
    }
    /** {@inheritDoc} */
    @Override
    public void move(float x, float y) {
        this.translate((int)x,(int)y);
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
