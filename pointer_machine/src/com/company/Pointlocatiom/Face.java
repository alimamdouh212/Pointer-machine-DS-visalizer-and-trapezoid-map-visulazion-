package com.company.Pointlocatiom;

import com.company.Draw_object;
import com.company.Node;
import com.company.Panel_me;
import com.company.task;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class Face {
    static private final int defaulttime=100;
    static int nfaces=0;
    int x1, x2;
    int y1,y2,y3,y4;
    int id;
    static LinkedList<Draw_object> drawable_faces=new LinkedList<>();
    LinkedList<Face> left=new LinkedList<>();
    LinkedList<Face> right=new LinkedList<>();
    static Panel_me fram;
    Drawable_face drawable_face;
    Straight_line upine;
    Straight_line downline;
    boolean pointleft=false;
    boolean pointright=false;
    boolean righup;
    boolean leftup;
    java.awt.Point medleft;
    java.awt.Point medright;
    LinkedList<Face_node> representers=new LinkedList<>();
    static LinkedBlockingQueue<task> draw_objectListarray=new LinkedBlockingQueue<>();

    static ArrayList<Draw_object> mydrawables=new ArrayList<>();
     public Face()
    {

    }
     void open(int x,int y1,int y2 )
    {
        x1=x;
        this.y1=y1;
        this.y2=y2;
        nfaces++;
        id=nfaces;
    }
    void open(int x,int y1,int y2,Point p)
    {
        open(x,y1,y2);
        pointleft=true;
        medleft=p;

    }
   void close(int x,int y1,int y2)
    {
        delelttemp();
        x2=x;
        y3=y1;
        y4=y2;
        upine=new Straight_line(x1,this.y1,x2,y3,false);
        downline=new Straight_line(x1,this.y2,x2,y4,false);

        drawable_face=new Drawable_face(x1,this.y1,x1,this.y2,x2,y3,x2,y4,""+id);
        add_new();
    }

    void add_new()
    {
        LinkedList<Draw_object> templist=new LinkedList<>();
        try {
            draw_objectListarray.put(new task(defaulttime) {
                @Override
               public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    list.add(drawable_face);
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    void close(int x, int y1, int y2, java.awt.Point p)
    {
        close(x,y1,y2);
        medright=p;
        pointright=true;

    }
    static Face firstfactes;
    static Face firstface(Face f, java.awt.Point p)
    {

        Face face=new Face();
        face.leftup=f.leftup;
        for(Face lefts:f.left)
        {
            face.left.add(lefts);
            if(f==lefts.right.get(0))
            {
                lefts.right.set(0,face);
            }
            else
            {
                lefts.right.set(1,face);
            }
        }


        if(f.pointleft) {
            face.open(f.x1, f.y1, f.y2, f.medleft);

        }
        else
            face.open(f.x1,f.y1,f.y2);
        face.close(p.x,f.upine.gety(p.x),f.downline.gety(p.x),p);
        face.righup=false;
        firstfactes=face;
        return face;

    }
    static Face lastface(Face f, java.awt.Point p)
    {

        Face face=new Face();
        face.righup=f.righup;
        face.open(p.x,f.upine.gety(p.x),f.downline.gety(p.x));
        for(Face rights:f.right)
        {
            face.right.add(rights);
            if(f==rights.left.get(0))
            {
                rights.left.set(0,face);
            }
            else
            {
                rights.left.set(1,face);
            }
        }
        if(f.pointright)
            face.close(f.x2,f.y3,f.y4,p);
        else
            face.close(f.x2,f.y3,f.y4);
        face.leftup=false;
        return face;
    }

   static Face_bus proces_first(Face f, Point p1,Straight_line line)
    {
        f.cololface(Color.green);
        f.delelt();

        Face face=Face.firstface(f,p1);

        Face_bus bus=new Face_bus(f,p1,line);
        face.right.add(bus.lower_face);
        bus.lower_face.left.add(face);
        face.right.add(bus.upper_face);
        bus.upper_face.left.add(face);

        xquery midq=new xquery(p1,true);
        Query_node mid=new Query_node(midq);
        Face_node leftface=new Face_node(face);
        mid.make_new_child(0,leftface);
        mid.make_new_child(1,bus.gettree());
        f.replace(mid);
        return bus;
    }
   static void proceslast(Face f, Point p2,Face_bus bus)
    {
       bus.end(f.upine.gety(p2.x),f.downline.gety(p2.x),p2.x);
        Face face=Face.lastface(f,p2);

        bus.lower_face.right.add(face);
        face.left.add(bus.lower_face);
        bus.upper_face.right.add(face);
        face.left.add(bus.upper_face);
        Face_node rightface=new Face_node(face);
        xquery xq=new xquery(p2,false);
        Query_node mid=new Query_node(xq);
        mid.make_new_child(0,bus.gettree());
        mid.make_new_child(1,rightface);
        f.replace(mid);
        for(Face l :f.right)
        {
            try {
                if (f == l.left.get(0))
                    l.left.set(0, face);
                else
                    l.left.set(1, face);
                face.right.add(l);
            }catch (IndexOutOfBoundsException e){
                int g=0;
            };
        }

    }
    /*static void dfs(Face ff)
    {
        ff.cololface(Color.BLUE);
        ff.cololface(Color.yellow);
        if(f==null)
            return;
        for(Face f:ff.right)
        {
            dfs(f);
        }
    }*/
    public void visit()
    {
        cololface(Color.green);
        cololface(Color.yellow);
    }
    static void travel(Face f1,Face f2,Point p1,Point p2)
    {


        Straight_line line=new Straight_line(p1.x,p1.y,p2.x,p2.y,true);
        boolean loop=true;
        if(f1==f2)
        {
            f1.cololface(Color.green);
            f1.delelt();
            Face left=Face.firstface(f1,p1);
            Face_bus firstbus=new Face_bus(f1,p1,line);
            firstbus.end(f1.upine.gety(p2.x),f1.downline.gety(p2.x),p2.x);
            Face right=Face.lastface(f1,p2);

            left.right.add(firstbus.lower_face);
            firstbus.lower_face.left.add(left);
            left.right.add(firstbus.upper_face);
            firstbus.upper_face.left.add(left);
            right.left.add(firstbus.lower_face);
            firstbus.lower_face.right.add(right);
            right.left.add(firstbus.upper_face);
            firstbus.upper_face.right.add(right);
            xquery p1qe=new xquery(p1,true);
            xquery p2qe=new xquery(p2,false);
            Query_node p1node=new Query_node(p1qe);
            Query_node p2node=new Query_node(p2qe);
            p2node.make_new_child(0,firstbus.gettree());
            p2node.make_new_child(1,new Face_node(right));
            p1node.make_new_child(0,new Face_node(left));
            p1node.make_new_child(1,p2node);
            f1.replace(p1node);


        }else {
            boolean first = true;
            Face currface = f1;
            Face_bus bus = proces_first(f1, p1, line);

            while (true) {


                if (!first) {
                    currface.cololface(Color.green);
                    currface.delelt();
                    currface.replace(bus.gettree());

                }
                first = false;
                if (currface == f2) {


                    proceslast(f2, p2, bus);
                    //dfs(firstfactes);
                    break;
                }
                Face nextface;

                if (currface.pointright) {
                    int above=line.aboveorbelow(currface.medright);

                    if ((above == 1)||((above == 0)&&(p2.y<currface.medright.y))) {
                        nextface = currface.right.getLast();
                        bus.lower_face.right.add(currface.right.getFirst());
                        currface.right.getFirst().left.set(0, bus.lower_face);
                        bus.lower_face.righup = false;
                        bus.lower_face.pointright = true;
                        bus.lower_face.medright = new Point(currface.right.getFirst().x1, currface.right.getFirst().y1);
                        bus.new_lower(currface.x2, currface.y4,currface.y3, nextface);

                    } else  {
                        nextface = currface.right.getFirst();
                        bus.upper_face.right.add(currface.right.getLast());
                        currface.right.getLast().left.set(0, bus.upper_face);
                        bus.upper_face.righup = false;
                        bus.upper_face.pointright = true;
                        bus.upper_face.medright = new Point(nextface.x1, nextface.y1);
                        bus.new_upper(currface.x2, currface.y3,currface.y4, nextface);
                        try {

                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("");
                        }


                    }


                } else {
                    nextface = currface.right.getFirst();
                    if (currface.righup) {
                        bus.lower_face.righup = true;
                        bus.lower_face.pointright = false;
                        bus.new_lower(currface.x2, currface.y4,currface.y3, nextface);
                        bus.lower_face.left.addFirst(nextface.left.get(0));
                        nextface.left.get(0).right.set(0, bus.lower_face);

                    } else {
                        bus.upper_face.righup = false;
                        bus.upper_face.righup = false;
                        bus.new_upper(currface.x2, currface.y3,currface.y4 ,nextface);
                        bus.upper_face.left.addFirst(nextface.left.get(1));
                        nextface.left.get(1).right.set(0, bus.upper_face);
                    }


                }
                currface = nextface;


            }

        }


    }
    void cololface(Color c)
    {

       Drawable_face local=drawable_face;
        try {
            draw_objectListarray.put(new task(defaulttime) {
                @Override
                public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    local.c=c;
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    private void delelt()
    {

        try {
            draw_objectListarray.put(new task(defaulttime) {
                @Override
               public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    list.remove(drawable_face);
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    static void traveltes(Face f1,Face f2,Point p1,Point p2)
    {


        Straight_line line=new Straight_line(p1.x,p1.y,p2.y,p2.y,true);

        Face currface=f1;

        while (true)
        {

            Face nextface;
            currface.cololface(Color.green);
            if(currface==f2)
            {
                currface.delelt();
                break;
            }
            if(currface.pointright)
            {
                if(line.aboveorbelow(currface.medright)==1)
                {
                    nextface=currface.right.getLast();

                }
                else
                {


                    nextface=currface.right.getFirst();


                }



            }else{
                nextface=currface.right.getFirst();
                if(currface.righup)
                {





                }
                else {





                }



            }
            currface.delelt();
            currface=nextface;


        }}
    void replace(Point_location_child child)
    {
        boolean first=true;
        for(Face_node node:representers)
        {
            if(first)
            {
                first=false;
                node.replacr(child);
            }else {
                node.replacr(child.dfs_deplicat());
            }

        }
    }
    Drawable_face temp;
    void tempclose(int x2,int y3,int y4){
        delelttemp();
        add_temp();
        temp=new Drawable_face(x1,y1,x1,y2,x2,y3,x2,y4,""+id);
    }
    private void delelttemp()
    {

        if(temp==null)
            return;
        try {
            draw_objectListarray.put(new task(0) {
                @Override
                public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    list.remove(temp);
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    void add_temp()
    {
        LinkedList<Draw_object> templist=new LinkedList<>();
        try {
            draw_objectListarray.put(new task(defaulttime) {
                @Override
                public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {
                    list.add(temp);
                    return list;
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }











    static class Face_bus
    {
        Face upper_face;

        Face lower_face;

        Straight_line line;
        Face_bus(Face f, Point p,Straight_line l)
        {
            line=l;
            lower_face=new Face();
            lower_face.open(p.x,p.y,f.downline.gety(p.x));
            lower_face.leftup=false;
            upper_face=new Face();
            upper_face.open(p.x,f.upine.gety(p.x),p.y);
            upper_face.leftup=true;


            //upper_face.down.add(lower_face);
            //lower_face.top.add(upper_face);

        }
        void new_upper(int x,int y,int yt,Face nextface)
        {
            lower_face.tempclose(x,line.gety(x),yt);
            upper_face.close(x,y,line.gety(x));

            Face face=new Face();
            face.leftup=false;
            face.open(x,nextface.y1,line.gety(x));
            upper_face.right.addFirst(face);
            face.left.add(upper_face);
            upper_face=face;
            //lower_face.top.add(upper_face);
            //upper_face.down.add(lower_face);
        }
        void new_lower(int x,int y,int yt,Face nextface)
        {

            lower_face.close(x,line.gety(x),y);
            upper_face.tempclose(x,yt,line.gety(x));
            Face face=new Face();
            face.open(x,line.gety(x),nextface.y2);

            lower_face.right.addLast(face);
            face.left.add(lower_face);
            lower_face=face;
            //lower_face.top.add(upper_face);
           // upper_face.down.add(lower_face);
        }
        void end(int y1, int y2,int x)
        {
            lower_face.close(x,line.gety(x),y2);
            upper_face.close(x,y1,line.gety(x));
            lower_face.righup=false;
            upper_face.righup=true;


        }
        Point_location_child gettree()
        {
            Face_node f1=new Face_node(lower_face);
            Face_node f2=new Face_node(upper_face);
            yquere midq=new yquere(line);
            Query_node mid=new Query_node(midq);
            mid.make_new_child(0,f1);
            mid.make_new_child(1,f2);
            return mid;
        }

    }
































}
