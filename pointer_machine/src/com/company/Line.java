package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Line extends Draw_object {


    int x1,y1,x2,y2;
    Line(int x1,int x2,int y1 ,int y2)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;

    }
    @Override
    public void draw(Graphics g, JPanel j) {
        g.setColor(Color.BLACK);
        g.drawLine(x1+10,y1+10,x2+10,y2+10);
    }


}
