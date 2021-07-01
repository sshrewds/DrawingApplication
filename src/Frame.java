import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** Class that represents a window frame. */
public class Frame extends JFrame {
    /** Drawing surface */
    DrawingSurface surface;

    /** Creates a window frame. */
    public static void main(String[] args) {
        new Frame();
    }

    /**
     * Basic constructor, provides a frame with a basic setup.
     * @throws HeadlessException
     */
    public Frame() throws HeadlessException {
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Drawing shapes | LABORATORIA 4");


        this.setJMenuBar(new MenuPanel());
        surface = new DrawingSurface();
        this.add(surface);
        this.setVisible(true);
    }

    /**
     * Class that represents a menu bar.
     */
    private class MenuPanel extends JMenuBar implements ActionListener {
        JMenu addMenu;
        JMenu infoMenu;

        JMenuItem addRectangle;
        JMenuItem addTriangle;
        JMenuItem addCircle;

        JMenuItem infoAbout;
        JMenuItem infoManual;

        /**
         * Basic constructor, provides a menu bar with items.
         */
        public MenuPanel() {
            //Add Menu
            addMenu = new JMenu("Add");

            addRectangle = new JMenuItem("Rectangle");
            addTriangle = new JMenuItem("Triangle");
            addCircle = new JMenuItem("Circle");

            addRectangle.addActionListener(this);
            addTriangle.addActionListener(this);
            addCircle.addActionListener(this);

            addMenu.add(addRectangle);
            addMenu.add(addTriangle);
            addMenu.add(addCircle);

            //Info Menu
            infoMenu = new JMenu("Info");

            infoManual = new JMenuItem("Manual");
            infoAbout = new JMenuItem("About");

            infoManual.addActionListener(this);
            infoAbout.addActionListener(this);

            infoMenu.add(infoManual);
            infoMenu.add(infoAbout);

            this.add(addMenu);
            this.add(infoMenu);
        }

        /**
         * Defines behaviour after choosing menu bar items.
         * @param e
         * action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == addRectangle){
                surface.setCurrentDrawingShape(ShapeEnum.RECTANGLE);
            } else if(e.getSource() == addTriangle){
                surface.setCurrentDrawingShape(ShapeEnum.TRIANGLE);
            } else if(e.getSource() == addCircle){
                surface.setCurrentDrawingShape(ShapeEnum.CIRCLE);
            } else if(e.getSource() == infoManual){
                String manualString = "In order to add new shape, choose shape from Add menu at the top,\n"+
                        "then click left mouse button to draw it on screen.\n\n"+
                        "You can move shape by dragging it with left mouse button,\n"+
                        "and you can move group of shapes if they line up with your cursor.\n\n"+
                        "Resize shape by pointing at it and using wheel in your mouse.\n"+
                        "Change color by clicking at shape with right mouse button.";
                JOptionPane.showMessageDialog(Frame.this, manualString, "Manual", JOptionPane.INFORMATION_MESSAGE);
            } else if(e.getSource() == infoAbout){
                String aboutString = "Drawing Shapes | LABORATORIA 4\n\n"+
                        "Application is designed for personal use.\n"+
                        "Created by Michał Tomczyk";
                JOptionPane.showMessageDialog(Frame.this, aboutString, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
