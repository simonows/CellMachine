package com;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{

public Panel(Space space){
    super();
    setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton btn = new JButton("start");
    btn.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            if(space.getStatus()){
                space.pause();
                btn.setText("continue");
            }else{
                space.continuE();
                btn.setText("stop");
            }
        }
    });

    add(btn);

    setPreferredSize(new Dimension(Style.panel_width, Style.panel_height));
    setBackground(Style.space_background);
    setVisible(true);
}

}
