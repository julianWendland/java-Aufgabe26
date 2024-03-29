/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe_26;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Peter Heusch
 */
public class AnalogPanel extends javax.swing.JPanel {

    private final Window myparent;
    private Calendar lastTime;

    /**
     * Creates new form ClockPanel
     */
    public AnalogPanel(Window parent) {
        myparent = parent;
        lastTime = Calendar.getInstance();
        initComponents();

        Timer t = new Timer(100, (ActionEvent e) -> {
            repaint();
        });
        t.start();

    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Graphics2D g4d;
        Graphics2D g3d;
        Graphics2D g2d = (Graphics2D) gc;
        Dimension dim = getSize();
        int i;
        dim.height = Math.min(dim.height, dim.width);
        dim.width = Math.min(dim.height, dim.width);
        Rectangle screenRect = this.getBounds();
        Point center = new Point(screenRect.x + screenRect.width / 2, screenRect.y + screenRect.height / 2);
        Rectangle clockRect = new Rectangle(center.x - dim.width / 2 , center.y - dim.height / 2 , dim.width  , dim.height);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(center.x - 5 / 2, center.y - 5 / 2, 5, 5);
        int j = 0;
        for (i = 0; i < 12; ++i) {

            while (j != 6 * i) {
                g4d = (Graphics2D) g2d.create();
                g4d.translate(center.x, center.y);
                g4d.rotate(Math.PI / 30 * (double) j);
                g4d.fillRect(-5, clockRect.height / 2 - clockRect.height / 20 + clockRect.height / 80, 3, clockRect.height / 80);
                g4d.dispose();
                j++;
            }

            g3d = (Graphics2D) g2d.create();
            g3d.translate(center.x, center.y);
            g3d.rotate(Math.PI / 6 * (double) i);
            g3d.fillRect(-5, clockRect.height / 2 - clockRect.height / 20, 8, clockRect.height / 40);
            g3d.dispose();
        }
        
        Calendar cal = GregorianCalendar.getInstance();
        Graphics2D g5d = (Graphics2D) g2d.create();
        g5d.translate(center.x,center.y);
        g5d.setColor(Color.BLACK);
        AffineTransform origin = g5d.getTransform();
        double secAngle = Math.PI/30*(double)cal.get(Calendar.SECOND);
        double minAngle = Math.PI/30*(double)cal.get(Calendar.MINUTE);
        double hourAngle = Math.PI/6*(double)cal.get(Calendar.HOUR);
        g5d.rotate(secAngle);
        g5d.setStroke(new BasicStroke(2f));
        g5d.drawLine(0, 0, 0, -clockRect.height*10/25);
        g5d.setTransform(origin);
        g5d.rotate(minAngle + secAngle/60);
        g5d.setStroke(new BasicStroke(4f));
        g5d.drawLine(0, 0, 0, -clockRect.height*9/25);
        g5d.setTransform(origin);
        g5d.rotate(hourAngle + minAngle/60);
        g5d.setStroke(new BasicStroke(6f));
        g5d.drawLine(0, 0, 0, -clockRect.height*8/25);
        g5d.setTransform(origin);
        
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
