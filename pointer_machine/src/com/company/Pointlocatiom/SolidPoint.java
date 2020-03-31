package com.company.Pointlocatiom;

import com.company.Draw_object;

import javax.swing.*;
import java.awt.*;

public class SolidPoint extends Draw_object {
    static int x=0,y=0;
    static final int r=5;
    Point p;
    static int npoints=0;
    int id;
    String text;
    SolidPoint(Point p,boolean first)
    {
        this.p=p;
        d=true;

        if(first)
        {
            npoints++;

            text="p";
        }
        else {
            text="q";
        }
        text+=npoints;
    }

    @Override
    public void draw(Graphics g, JPanel panel)
    {
        JLabel label=new JLabel(text);
        label.setBounds(p.x+x,p.y+10+y,5*r,5*r);
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
        g.fillOval(p.x+x-r,p.y+y-r,2*r,2*r);


    }
}
