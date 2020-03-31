package com.company.Pointlocatiom;

import com.company.Data;
import com.company.Draw_object;
import com.company.Node;
import com.company.task;

import java.awt.*;
import java.util.LinkedList;

public class Point_location_root extends Node {
    int x=1500,y=1000;
    int lenght=500,width=500;

    public Point_location_root()
    {
        super(new Data<String>("D") {
            @Override
           public String tostring() {
                return my_data;
            }
        });
        Face.draw_objectListarray=draw_objectsquee;
        Drawable_face.xb=x;
        Drawable_face.yb=y;
        SolidPoint.x=x;
        SolidPoint.y=y;
        Face firsrface=new Face();
        firsrface.open(0,0,0+lenght);
        firsrface.close(0+width,0,0+lenght);
        make_new_child(0,new Face_node(firsrface));


    }
    static void addpoint(Point p,boolean first)
    {
        try {
            draw_objectsquee.put(new task(10) {
                @Override
                public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    list.add(new SolidPoint(p,first));
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    Face findpoint(Point p)
    {
        Point_location_child node=(Point_location_child) childern.get(0);

        while (!(node instanceof Face_node))
        {  node.visit();
           Query_node query_node=((Query_node)node);
           node=query_node.getrightnode(p);

        }
        node.visit();
        ((Face_node) node).myface.visit();
        return ((Face_node) node).myface;
    }
    public void new_segemnt(Point p1,Point p2)
    {
        Face f1=findpoint(p1);
        addpoint(p1,true);
        Face f2=findpoint(p2);
        addpoint(p2,false);

        Face.travel(f1,f2,p1,p2);
    }
}
