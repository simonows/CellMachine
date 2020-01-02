package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Cell extends JPanel {
    private boolean status;

    public Cell() {
        super();
        setPreferredSize(new Dimension(Style.cell_scale, Style.cell_scale));
        //setBackground(Style.cell_color);
        setVisible(true);
        status = false;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (arg0.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                    status = true;
                }
                repaint();
                //setBackground(Style.pointed_color);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                status = !status;
                repaint();
            }
        });
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean _status) {
        status = _status;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getStatus()) {
            g.setColor(Style.cell_color);
        }
        else {
            g.setColor(Style.cell_ncolor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

