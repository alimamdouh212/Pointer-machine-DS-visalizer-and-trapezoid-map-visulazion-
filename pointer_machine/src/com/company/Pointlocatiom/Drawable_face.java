package com.company.Pointlocatiom;

import com.company.Draw_object;

import javax.swing.*;
import java.awt.*;

public class Drawable_face extends Draw_object {
    protected static  int xb,yb;
    int x1;
    int x2;
    int y1;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;
    Color c=Color.yellow;
    String text;
    public Drawable_face(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,String s)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.x3=x3;
        this.x4=x4;
        this.y3=y3;
        this.y4=y4;

        this.text=s;
    }
    @Override
    public void draw(Graphics g, JPanel panel) {

        g.setColor(c);
        g.fillPolygon(new int[]{x1+xb,x2+xb,x4+xb,x3+xb},new int[]{y1+yb,y2+yb,y4+yb,y3+yb},4);
        int x1t=(x1+xb+x3+xb)/2;
        int x2t=(x2+xb+x4+xb)/2;
        int x=(x1t+x2t)/2;

        int y1t=(y1+yb+y3+yb)/2;
        int y2t=(y2+yb+y4+yb)/2;
        int y=(y1t+y2t)/2;

        x-=10;
        y-=10;
        JLabel label=new JLabel(text);
        label.setBounds(x,y,20,20);
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
        g.setColor(Color.black);
        g.drawLine(x1+xb,y1+yb,x2+xb,y2+yb);
        g.drawLine(x1+xb,y1+yb,x3+xb,y3+yb);
        g.drawLine(x4+xb,y4+yb,x2+xb,y2+yb);
        g.drawLine(x4+xb,y4+yb,x3+xb,y3+yb);


    }
    Drawable_face creatdublicate()
    {
        return new Drawable_face(x1,y1,x2,y2,x3,y3,x4,y4,text);
    }

}
