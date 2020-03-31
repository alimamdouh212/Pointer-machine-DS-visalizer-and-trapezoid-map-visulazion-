package com.company.Pointlocatiom;

import java.awt.Point;

public class Straight_line {
    double m;
    double b;
    static int nosegments=0;
    Integer id=null;
    public Straight_line(int x1,int y1,int x2,int y2,boolean counted)
    {

        if((x1==x2)&&(y1==y2))
        {
            m=0;
            b=y1;
        }
        m=(1.0*(y1-y2))/(x1-x2);
        b=y1-m*x1;
        if(counted)
        {
        nosegments++;
        id=nosegments;}
    }
    public int aboveorbelow(Point p)
    {
        int myy=(int)(p.x*m+b);
        if((double)((myy-p.y)*(myy-p.y))<0.1)
            return 0;
        if(myy>p.y)
            return -1;
        else
            return 1;


    }
    public int gety(int x)
    {
        return (int)(m*x+b);
    }

}
