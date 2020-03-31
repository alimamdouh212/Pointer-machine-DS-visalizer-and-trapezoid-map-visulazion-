package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class Node {
    protected static Panel_me fram;
    protected static Event_qeue event_qeue;
    protected static Point point_root;
    protected static LinkedBlockingQueue draw_objectsquee;
    protected static LinkedList<Draw_object> lastdraw_objects;
    protected static final int numperofsteps=1;
    protected static Node root;
    protected Point myPoint;
    protected Circle circle;
    protected boolean isroot=false;
    protected Node parent;
    protected ArrayList<Node> childern=new ArrayList<>();
    protected Data data;
    boolean visibilty=true;
    boolean orphan=false;
    void ignore()
    {
        visibilty=false;
        myPoint.ignore=true;
        reconstructbridge();
    }
    void rememper()
    {
        visibilty=true;
        myPoint.ignore=false;
        reconstructbridge();
    }

   protected Node(Data data)

    {
        this.data=data;

        draw_objectsquee=new LinkedBlockingQueue();
        fram=new Panel_me(draw_objectsquee);
        myPoint=Point.getroot(numperofsteps);
        point_root=myPoint;
        event_qeue=new Event_qeue();

        Pointcoordinate p=myPoint.getcoordinate();
        circle =new Circle(p.x,p.y,data.tostring());
        isroot=true;
        root=this;



    }
    private task changeclor(Color c)
    {

        Circle local=circle;
        return new task(300) {
            @Override
           public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {

                circle.c=c;
                for(Draw_object d:list)
                {
                local.c=c;
                }
                return list;
            }
        };

    }
    protected Node(Data data,Node parent,Point myPoint)
        {


            this.data=data;
            this.parent=parent;

            this.myPoint=myPoint;


            Pointcoordinate p=myPoint.getcoordinate();
            circle =new Circle(p.x,p.y,data.tostring());



        }

    static Node getRoot()
    {

        return root;


    }
    static Node make_root(Data d)
    {
        root=new Node(d);
        reconstruct();
        return getRoot();

    }
    Node addchild(Data d,boolean ishidden)
    {

       Point childpoint= myPoint.addchild(ishidden);
       Node child=new Node(d,this,childpoint);
       child.visibilty=!ishidden;
       childern.add(child);
       reconstructbridge();
       return child;

    }
    private void make_child(Node child)
    {

        childern.add(child);
        child.parent=this;
        myPoint.make_child(child.myPoint);
        reconstructbridge();

    }
    private void setcild(int i,Node childe)
    {
        childern.set(i,childe);
        childe.parent=this;
        myPoint.set_child(i,childe.myPoint);
        reconstructbridge();
    }
    public void make_new_child(int i,Node child)
    {
        if(i==childern.size())
            make_child(child);
        else if(i<childern.size())
            setcild(i,child);
        else {
           throw new IndexOutOfBoundsException();
        }
        child.deorphan();
    }
    Node getchild(int in)
    {
        if(in>childern.size()-1)
            return null;

        return childern.get(in);
    }
    public void visit()
    {
        try {
            draw_objectsquee.put(changeclor(Color.green));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            draw_objectsquee.put(changeclor(Color.yellow));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    Data getData()
    {


        /*try {
            draw_objectsquee.put(new task(300,lastdraw_objects));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updatecolor(this.circle,Color.yellow);
        try {
            draw_objectsquee.put(new task(300,lastdraw_objects));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return data;
    }
   /* void updatecolor(Circle c,Color color)
    {
        ArrayList<Draw_object> ne=new ArrayList<Draw_object>();
        for(int i=0;i<lastdraw_objects.size();i++)
        {

            if(lastdraw_objects.get(i)==c)
            {
                Circle target=(Circle) lastdraw_objects.get(i);
                Circle circle=new Circle(target.x,target.y,target.s);
                circle.c=color;
                this.circle=circle;
                ne.add(circle);
            }
            else
                ne.add(lastdraw_objects.get(i));

        }
        lastdraw_objects=ne;


    }*/
    private static void dfs(Node n,Node.Pointcoordinate pointcoordinate,LinkedList<Draw_object> draw_objects)
    {
        for(int i=0;i<n.childern.size();i++)
        {
            Node child=n.childern.get(i);
            Node.Pointcoordinate p=child.myPoint.getcoordinate();
            child.circle=new Circle(p.x,p.y,child.data.tostring());
            if(child.visibilty==true)
            {draw_objects.add(child.circle);
            draw_objects.add(new Line(p.x,pointcoordinate.x,p.y,pointcoordinate.y));
            dfs(child,p,draw_objects);}

        }



    }
    private static void updat_drawable(LinkedList<Draw_object> draw_objects)
    {
        draw_objects.clear();
        Pointcoordinate p=point_root.getcoordinate();
        Node no=root;
        root.circle=new Circle(p.x,p.y,root.data.tostring());
        draw_objects.add(root.circle);
        dfs(root,p,draw_objects);


    }
    void deorphan()
    {
        orphan=false;
        for(Node child:childern)
        {
           child.orphan=false;
        }

    }
    void reconstructbridge()
    {
        if(!orphan)
            reconstruct();
    }
    static  void reconstruct()  {

        /*event_qeue.push(new Event(0,new Runnable() {
            @Override
            public void run() {

            }
        }));*/

        Point.dfs();

        for(int i=0;i<numperofsteps;i++)
        {
           /* event_qeue.push(new Event(50, new Runnable() {
                @Override
                public void run() {
                    updat_drawable();
                    fram.repaint();
                }
            }));*/
            //lastdraw_objects=new LinkedList<>();

            //updat_drawable(lastdraw_objects);
            LinkedList<Draw_object> temp=new LinkedList<>();

            updat_drawable(temp);
            lastdraw_objects=temp;
            try {


                draw_objectsquee.put(new task(10) {
                    @Override
                    public LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list) {

                        if(!list.isEmpty()) {

                            Iterator<Draw_object> it = list.iterator();
                            do {
                                Draw_object object = it.next();
                                if ((object instanceof Line) || (object instanceof Circle)) {
                                    it.remove();
                                }


                            } while (it.hasNext());
                        }

                        list.addAll(temp);
                        return list;
                    }
                });
            }catch (Exception e)
            {}

        }



    }
    protected Node( Data d,boolean orphane)
    {
        this.data=d;
        myPoint=new Point();
        Pointcoordinate p=myPoint.getcoordinate();
        circle =new Circle(p.x,p.y,data.tostring());
    }
    static JFrame f=new JFrame("");
    static protected void start()
    {


        f.add(fram);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        f.setSize(4500,3500);
        f.setVisible(true);
        fram.start();


    }











    public static class Pointcoordinate{
        int x, y;

        Pointcoordinate(int x,int y)
        {this.x=x;
        this.y=y;}


    }

}
