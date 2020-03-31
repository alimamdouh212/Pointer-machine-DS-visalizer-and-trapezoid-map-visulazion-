package com.company;


import java.util.ArrayList;
import java.util.Stack;

public class Point {
    private ArrayList<Point> childern=new ArrayList<>();
    private Point parent;
    boolean isroot=false;
    static Point root=null;
    boolean statue;
    static Matrix_stack stack=new Matrix_stack();
    static private   final Double arrow=300.0;
    static int numperofsteps;
    private int x1,y1,x2,y2;
    private int curren_step=0;
    boolean ignore=false;
    private Point(int numperofsteps)
    {

        statue=false;
        Point.numperofsteps=numperofsteps;
        isroot=true;
        root=this;
        x1=1000;
        y1=10;
    }
    private boolean updatstate() {
        if (((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 5)&&(curren_step!=numperofsteps-1))
            return true;
        x1 = x2;
        y1 = y2;
        statue = false;
        return statue;
    }
    Node.Pointcoordinate getcoordinate()
    {
        if(!updatstate())
            return new Node.Pointcoordinate(x1,y1);
        double cof=(curren_step*1.0)/numperofsteps;
        int xr=(int)((1-cof)*x1+(cof)*x2);
        int yr=(int)((1-cof)*y1+(cof)*y2);
        curren_step++;

        return new Node.Pointcoordinate(xr,yr);

    }

    private Point(Point parent,boolean ignore)
    {

        this.ignore=ignore;
        statue=true;
        this.parent=parent;
        x1=x2=parent.x1;
        y1=y2=parent.y1;
    }
    Point(){}
    static Point getroot(int numperofsteps)
    {
        if (root==null)
             root=new Point(numperofsteps);
         return root;
    }
    protected Point addchild(boolean ignore)
    {
        Point c=new Point(this,ignore);
        childern.add(c);
        return c;

    }
    Point getchild(int in)
    {
        if(in+1>childern.size())
            return null;
        return childern.get(in);
    }
    boolean getvisibilty()
    {
        return !ignore;
    }
    void make_child(Point p)
    {
        childern.add(p);
        p.parent=this;
        p.x1=p.x2=x1;
        p.y1=p.y2=y1;

    }
    static void dfs()
    {
        if(root!=null)
        dfs(root,2,0);

    }
    void set_child(int in,Point p )
    {
        childern.set(in,p);
        p.parent=this;
        p.x1=p.x2=x1;
        p.y1=p.y2=y1;
    }
    private static void dfs(Point node,int n,int in)
    {
      if(node.ignore)
            return;
        Double angel=Math.PI;
        angel=angel/n;
        angel*=in;
        angel+=(Math.PI/(2*n));
        Matrix m1=Matrix.translate(arrow);
        Matrix m3=Matrix.shrink(n);
        Matrix m2=Matrix.rotate(angel);
        stack.push(m1);
        stack.push(m2);
        stack.push(m3);



        Vectorpolar v=stack.top().mul(new Vectorpolar(Math.PI/2,0.0));

         node.x2=(int)(Math.cos(v.get_angel())*v.get_radis());
         node.y2= (int)((Math.sin(v.get_angel())*v.get_radis()));
         node.x2+=1000;
         node.y2+=10;
         node.statue=true;
         node.curren_step=0;
        for (int i=0;i<node.childern.size();i++)
            dfs(node.childern.get(i),node.childern.size()+1,node.childern.size()-1-i);



        stack.pop();
        stack.pop();
        stack.pop();


    }

}
