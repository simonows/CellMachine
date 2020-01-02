package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Style{
    static int width = 1000;
    static int height = 680;
    static int panel_width = 100;

    static int content_width = width - panel_width;
    static int content_height = height;
    static int panel_height = height;

    static int cell_scale = 12;

    static int dim_width = 54;
    static int dim_height = 97;

    static int part_space = 1;
    static int space_width = dim_width * (cell_scale + part_space);
    static int space_height = dim_height * (cell_scale + part_space);

    static Color cell_ncolor = Color.BLACK;
    static Color cell_color = Color.GREEN;
    static Color pointed_color = Color.GRAY;
    static Color space_background = Color.decode("#808080");
    static Color info_background = Color.decode("#8401a5");
    static Color menu_background = Color.decode("#f3e9f7");
    static Color menubar_background = Color.decode("#e3cced");
    static Color text_color = Color.decode("#ffffff");
    static Font text_style1 = new Font("Verdana", Font.PLAIN, 12);

    public static void setJLabelStyle(JLabel element){
        element.setFont(text_style1);
        element.setForeground(info_background);
    }

    public static JButton newButton(String name){
        JButton button = new JButton(name);
        button.setBackground(Style.info_background);
        button.setForeground(Color.white);
        return button;
    }
}

