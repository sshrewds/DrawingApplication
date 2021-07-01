import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class that represents a drawing surface.
 */
public class DrawingSurface extends JPanel {

    /** Holds all drawn rectangles. */
    ArrayList<RectangleShape> rectangles;
    /** Holds all drawn triangles. */
    ArrayList<TriangleShape> triangles;
    /** Holds all drawn circles. */
    ArrayList<CircleShape> circles;

    /** Defines if application is currently in a drawing mode. */
    private boolean drawing = false;
    /**
     * Holds information about shape that is currently being drawn.
     * @see ShapeEnum
     */
    private ShapeEnum currentDrawingShape;
    /** Basic constructor */
    public DrawingSurface() {
        MovingHandler movingHandler = new MovingHandler();
        this.addMouseListener(movingHandler);
        this.addMouseMotionListener(movingHandler);

        this.addMouseListener(new ColorChangingHandler());
        this.addMouseListener(new AddingShapesHandler());
        this.addMouseWheelListener(new ScalingHandler());

        rectangles = new ArrayList<>();
        triangles = new ArrayList<>();
        circles = new ArrayList<>();
    }

    /**
     * Sets a specific shape to be drawn.
     * @param shape
     * a shape to be drawn
     * @see ShapeEnum
     */
    public void setCurrentDrawingShape(ShapeEnum shape) {
        this.drawing = true;
        this.currentDrawingShape = shape;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawShapes(g2d);
    }

    /**
     * Draws shapes on a screen.
     * @param g2d
     */
    private void drawShapes(Graphics2D g2d) {
        for (RectangleShape rectangle : rectangles) {
            g2d.setColor(rectangle.getColor());
            g2d.fill(rectangle);
        }

        for (TriangleShape triangle : triangles) {
            g2d.setColor(triangle.getColor());
            g2d.fill(triangle);
        }

        for (CircleShape circle : circles) {
            g2d.setColor(circle.getColor());
            g2d.fill(circle);
        }
    }

    /** Handles moving shapes into different locations. */
    private class MovingHandler extends MouseAdapter {
        /** x coordinate of mouse when started dragging */
        private int x;
        /** y coordinate of mouse when started dragging */
        private int y;

        @Override
        public void mousePressed(MouseEvent e) {
            this.x = e.getX();
            this.y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if(SwingUtilities.isLeftMouseButton(e)){
                moveShapes(e);
            }
        }
        /** Moves a specific shape or group of shapes.
         * @param e
         */
        private void moveShapes(MouseEvent e) {
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            for (RectangleShape rectangle : rectangles) {
                if(rectangle.isInside(x, y)){
                    rectangle.move(dx,dy);
                    repaint();
                }
            }

            for (TriangleShape triangle : triangles) {
                if(triangle.isInside(x, y)){
                    triangle.move(dx,dy);
                    repaint();
                }
            }
            for (CircleShape circle : circles) {
                if(circle.isInside(x, y)){
                    circle.move(dx,dy);
                    repaint();
                }
            }
            x += dx;
            y += dy;
        }
    }
    /** Handles changing colors of shapes. */
    private class ColorChangingHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(SwingUtilities.isRightMouseButton(e)){
                changeColorOfShapes(e);
            }
        }

        /**
         * Changes color of specific shape or group of shapes.
         * Shows a color picker on a screen.
         * @param e
         */
        private void changeColorOfShapes(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Color newColor;
            boolean isMouseAtAnyShape = false;
            ArrayList<ModifiableShape> shapesToColor = new ArrayList<>();


            for (RectangleShape rectangle : rectangles) {
                if(rectangle.isInside(x, y)){

                    shapesToColor.add(rectangle);
                    isMouseAtAnyShape = true;
                }
            }

            for (TriangleShape triangle : triangles) {
                if(triangle.isInside(x, y)){
                    shapesToColor.add(triangle);
                    isMouseAtAnyShape = true;
                }
            }

            for (CircleShape circle : circles) {
                if(circle.isInside(x, y)){
                    shapesToColor.add(circle);
                    isMouseAtAnyShape = true;
                }
            }

            if(isMouseAtAnyShape){
                newColor = JColorChooser.showDialog(null, "Pick a color", Color.BLACK);
                for (ModifiableShape shape : shapesToColor) {
                    shape.setColor(newColor);
                }
                repaint();
            }
        }
    }
    /** Handles scaling shapes. */
    private class ScalingHandler implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            scaleShapes(e);
        }

        /**
         * Scales specific shape of group of shapes.
         * @param e
         */
        private void scaleShapes(MouseWheelEvent e) {
            int x = e.getX();
            int y = e.getY();
            float scalingFactor = -e.getWheelRotation()*5f;

            if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL){
                for (RectangleShape rectangle : rectangles) {
                    if(rectangle.isInside(x, y)){
                        rectangle.scale(scalingFactor);

                        repaint();
                    }
                }

                for (TriangleShape triangle : triangles) {
                    if(triangle.isInside(x, y)){
                        triangle.scale(scalingFactor);

                        repaint();
                    }
                }

                for (CircleShape circle : circles) {
                    if(circle.isInside(x, y)){
                        circle.scale(scalingFactor);

                        repaint();
                    }
                }
            }
        }
    }

    /** Handles creating new shapes and adding them to the screen. */
    private class AddingShapesHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e) && drawing){
                drawing = false;
                int x = e.getX();
                int y = e.getY();

                if(currentDrawingShape == ShapeEnum.RECTANGLE){
                    rectangles.add(new RectangleShape(x,y, 200, 100, Optional.empty()));
                    repaint();
                }
                else if(currentDrawingShape == ShapeEnum.TRIANGLE){
                    triangles.add(new TriangleShape(x,y, 100, Optional.empty()));
                    repaint();
                }
                else if(currentDrawingShape == ShapeEnum.CIRCLE){
                    circles.add(new CircleShape(x,y, 100, Optional.empty()));
                    repaint();
                }
            }
        }
    }
}
