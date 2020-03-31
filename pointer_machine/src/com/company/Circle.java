package com.company;


import javax.swing.*;
import java.awt.*;

public class Circle extends Draw_object {

    Color c=Color.yellow;
    int x,y;
    String s="";
    Circle(int x,int y,String s)
    {
        this.x=x;
        this.y=y;

        this.s=s;

    }
    private final static int r=10;
    @Override
    public void draw(Graphics g, JPanel panel) {

        g.setColor(c);
        g.fillOval(x-r,y-r,2*r,2*r);
        JLabel label=new JLabel(s);
        label.setBounds(x-r,y-r,2*r,2*r);
        panel.add(label);
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));

    }
}
