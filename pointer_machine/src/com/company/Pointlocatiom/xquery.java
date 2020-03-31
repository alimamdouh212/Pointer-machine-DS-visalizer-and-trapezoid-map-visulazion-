package com.company.Pointlocatiom;

import com.company.Data;

import java.awt.Point;

public class xquery extends queryobject{
    static int npoints=0;
    Point mypoint;
    boolean first;
    int id;
    xquery(Point p, boolean first)
    {
        /*super(new Data<String>(first? "p"+npoints+1:"q"+npoints) {
            @Override
            public String tostring() {
                return my_data;
            }
        });*/
        mypoint=p;
        if(first)
        {
            npoints++;
            text="p"+npoints;
        }
        else {
            text="q"+npoints;
        }
        this.first=first;
        id=npoints;

    }


    @Override
    int left_or_right(Point point) {
        if (point.x>mypoint.x)
            return 1;
        else if(point.x<mypoint.x)
            return -1;
        return 0;

    }
}
