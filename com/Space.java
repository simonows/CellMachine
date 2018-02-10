package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class Space extends JPanel implements Runnable{
	private Cell cells[][];
	private boolean status;
	private int dim_width, dim_height;
	private boolean temp[][];
	
	public Space(){
		super();
		dim_width = Style.dim_width;
		dim_height = Style.dim_height;
		cells = new Cell[dim_width][dim_height];
		temp = new boolean[dim_width][dim_height];
		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		GridBagConstraints c =  new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH; 
		c.fill   = GridBagConstraints.NONE;  
		c.gridheight = 1;
		c.gridwidth  = 1; 
		c.gridx = GridBagConstraints.RELATIVE; 
		c.gridy = 0; 
		c.insets = new Insets(0, 0, Style.part_space, Style.part_space);
		
		for(int i=0; i<dim_width; i++){
			for(int j=0; j<dim_height; j++){
				cells[i][j] = new Cell();
				gbl.setConstraints(cells[i][j], c);
				add(cells[i][j]);
			}
			c.gridy++; 
		}
		status = false;
		
		
		
		setPreferredSize(new Dimension(Style.space_height, Style.space_width));
		setBackground(Style.space_background);
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	public boolean getStatus(){
		return status;
	}
	
	public void pause(){
		status = false;
	}
	
	public void continuE(){
		status = true;
		synchronized (this){
        	this.notify();
        }
	}
	
	private int count(int x, int y){
		int count=0;
		int ax,ay,bx,by;
		int tx,ty;
		ax = x-1;
		bx = x+1;
		ay = y-1;
		by = y+1;
		for(; ax<=bx; ax++){
			for(int j = ay; j<=by; j++){
				tx =ax;
				ty =j;
				if(tx<0) tx = dim_width-1;
				if(tx==dim_width) tx = 0;
				if(ty<0) ty = dim_height-1;
				if(ty==dim_height) ty = 0;
				if (cells[tx][ty].getStatus() && !(x==ax && y==j)){ 
					count++;
				}
			}
		}
		return count;
	}
	
	@Override
	public void run() {
		int c;
		while(true){
			if(status){
				for(int i=0; i<dim_width; i++){
					for(int j=0; j<dim_height; j++){
						c = count(i,j);
						temp[i][j] = cells[i][j].getStatus(); 
						if(temp[i][j]){
							if(c<2 || c>3) {
								temp[i][j] = false;
							} 
						}
						else if(c==3) {
							temp[i][j] = true;
						}
					}
				}
				for(int i=0; i<dim_width; i++){
					for(int j=0; j<dim_height; j++){
						cells[i][j].setStatus(temp[i][j]);
					}
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			else{
				synchronized (this) {
		            try {
		                this.wait();
		            } catch (InterruptedException ex) {}
	            }
			}
		}
	}
	
}
