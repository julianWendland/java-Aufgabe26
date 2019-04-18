/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe_26;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Timer;

/**
 *
 * @author 82weju1bif
 */
public class DigitalPanel extends javax.swing.JPanel {

    private final Window myparent;
    private Calendar lastTime;
    private int height;
    private int width;
    private int line;
    private int gap;
    private int digitgap;
    private int blockgap;
    private int pointgap;
    private Color foreground;
    private Color background;
    private Graphics2D g2d;
    private Dimension dim = getSize();

    private int mitteX = dim.width / 4;
    private int mitteY1 = dim.height / 4 + 195;//+5
    private int mitteY2 = dim.height / 4 + 405; //+10
    private int mitteY3 = dim.height / 4 + 615; //+15
    private int linksX = dim.width / 6 - 5;
    private int linksY1 = dim.height / 4 + 200;
    private int linksY2 = dim.height / 4 + 405;
    private int rechtsX = dim.width / 6 + 210;
    private int rechtsY1 = linksY1;
    private int rechtsY2 = linksY2;

    private int offsetHour = 100;
    private int offsetMinute = 720;
    private int offsetSecond = 1380;
    private int offsetEach = 250;

    public final int OFFSET_FIRST_HOUR = offsetHour;
    public final int OFFSET_SECOND_HOUR = offsetHour + offsetEach;
    public final int OFFSET_FIRST_MINUTE = offsetMinute;
    public final int OFFSET_SECOND_MINUTE = offsetMinute + offsetEach;
    public final int OFFSET_FIRST_SECOND = offsetSecond;
    public final int OFFSET_SECOND_SECOND = offsetSecond + offsetEach;

    /**
     * Creates new form ClockPanel
     */
    public DigitalPanel(Window parent) {
        myparent = parent;
        lastTime = Calendar.getInstance();
        initComponents();
        Timer t = new Timer(50, (ActionEvent e) -> {
            repaint();
        });
        t.start();

    }

    @Override
    public void paintComponent(Graphics gc) {

        super.paintComponent(gc);
        Calendar cal = GregorianCalendar.getInstance();
        this.g2d = (Graphics2D) gc;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        g2d.setColor(Color.BLACK);
        drawDots(g2d);
        drawDigit(splitNumber(hour)[0], g2d, OFFSET_FIRST_HOUR);
        drawDigit(splitNumber(hour)[1], g2d, OFFSET_SECOND_HOUR);
        drawDigit(splitNumber(minute)[0], g2d, OFFSET_FIRST_MINUTE);
        drawDigit(splitNumber(minute)[1], g2d, OFFSET_SECOND_MINUTE);
        drawDigit(splitNumber(second)[0], g2d, OFFSET_FIRST_SECOND);
        drawDigit(splitNumber(second)[1], g2d, OFFSET_SECOND_SECOND);

    }

    private int[] splitNumber(int number) {
        int firstNumber = 0;
        int secondNumber = 0;
        if (number > 60) {
            firstNumber = 6;
            secondNumber = number - 60;
        } else if (number >= 50) {
            firstNumber = 5;
            secondNumber = number - 50;
        } else if (number >= 40) {
            firstNumber = 4;
            secondNumber = number - 40;
        } else if (number >= 30) {
            firstNumber = 3;
            secondNumber = number - 30;
        } else if (number >= 20) {
            firstNumber = 2;
            secondNumber = number - 20;
        } else if (number >= 10) {
            firstNumber = 1;
            secondNumber = number - 10;
        } else {
            secondNumber = number;
        }
        return new int[]{firstNumber, secondNumber};
    }

    private void drawOne(int offset) {

        g2d.fill(drawPolyV(rechtsX + offset, linksY2));//rechts oben 
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawFive(int offset) {
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten

    }

    private void drawThree(int offset) {

        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawFour(int offset) {

        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links oben 
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawTwo(int offset) {

        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyV(linksX + offset, linksY2));//links oben
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawNine(int offset) {
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links unten 
        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawSeven(int offset) {

        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawEight(int offset) {
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links unten 
        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyV(linksX + offset, linksY2));//links oben
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private void drawSix(int offset) {

        g2d.fill(drawPolyH(mitteX + offset, mitteY2));//mitte mitte
        g2d.fill(drawPolyV(linksX + offset, linksY2));//links oben
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links unten 
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten

    }

    private void drawZero(int offset) {
        g2d.fill(drawPolyV(linksX + offset, linksY1));//links unten 
        g2d.fill(drawPolyV(linksX + offset, linksY2));//links oben
        g2d.fill(drawPolyH(mitteX + offset, mitteY1));//mitte oben
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY2));//rechts oben 
        g2d.fill(drawPolyH(mitteX + offset, mitteY3));//mitte unten
        g2d.fill(drawPolyV(rechtsX + offset, rechtsY1));//rechts unten
    }

    private Polygon drawPolyV(int startX, int startY) {
        Polygon p = new Polygon();
        p.addPoint(startX + 5, startY + 5);
        p.addPoint(startX + 5, startY + 200);
        p.addPoint(startX, startY + 205);
        p.addPoint(startX - 5, startY + 200);
        p.addPoint(startX - 5, startY + 5);
        p.addPoint(startX, startY);
        return p;

    }

    private Polygon drawPolyH(int startX, int startY) {
        Polygon p = new Polygon();
        p.addPoint(startX + 5, startY + 5);
        p.addPoint(startX + 200, startY + 5);
        p.addPoint(startX + 205, startY);
        p.addPoint(startX + 200, startY - 5);
        p.addPoint(startX + 5, startY - 5);
        p.addPoint(startX, startY);

        return p;

    }

    private void drawDots(Graphics2D g1d) {
        int size = 15;
        int half = size / 2;
        int dotDis = 100;
        Dimension dim = getSize();
        g1d.fillRect(dim.width / 3 - half, dim.height / 3 - half, size, size);
        g1d.fillRect(dim.width / 3 - half, (dim.height / 3 - half) + dotDis, size, size);
        g1d.fillRect((dim.width / 3 * 2 - half), dim.height / 3 - half, size, size);
        g1d.fillRect((dim.width / 3 * 2 - half), (dim.height / 3 - half) + dotDis, size, size);

    }

    private void drawDigit(int digit, Graphics2D g1d, int offset) {
        switch (digit) {
            case 0:
                drawZero(offset);
                break;
            case 1:
                drawOne(offset);
                break;

            case 5:
                drawFive(offset);
                break;
            case 3:
                drawThree(offset);
                break;
            case 4:
                drawFour(offset);
                break;
            case 2:
                drawTwo(offset);
                break;
            case 9:
                drawNine(offset);
                break;
            case 7:
                drawSeven(offset);
                break;

            case 8:
                drawEight(offset);
                break;
            case 6:
                drawSix(offset);
                break;
        }

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
