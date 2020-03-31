package com.company;

import com.company.Pointlocatiom.Point_location_root;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.Scanner;

public class Main {

    static int l=2;
    static int n=3;
    static void tes(Node u,int l)
    {
        if(l==0)
        return;

        for(int i=0;i<n;i++)
        {

            /*Scanner cin=new Scanner(System.in);
            int f=cin.nextInt();*/
            Node y=u.addchild(new test(i),false);

            tes(y,l-1);
        }


    }
     static void insert(Node n,int i)
    {
        n.visit();
        int temp=(int)n.getData().my_data;
        if(temp>i)
        {
            int temp2=(int)((Integer)n.getchild(0).getData().my_data);
            if(temp2==-1)
            {
                n.getchild(0).getData().my_data=i;
                n.getchild(0).rememper();
                n.getchild(0).addchild(new test(-1),true);
                n.getchild(0).addchild(new test(-1),true);




            }
            else {
                insert(n.getchild(0), i);

            }

        }
        else
        {
            int temp2=(int)((Integer)n.getchild(1).getData().my_data);


            if(temp2==-1)
            {
                n.getchild(1).getData().my_data=i;
                n.getchild(1).rememper();
                n.getchild(1).addchild(new test(-1),true);
                n.getchild(1).addchild(new test(-1),true);



            }
            else insert(n.getchild(1),i);

        }


    }
    public static void main(String[] args) {


      //  JFrame f=new JFrame("");
       // Point.dfs();
        //Panel_me panelme = new Panel_me(Point.temp);

	// write your code here

       /* Node n=Node.make_root(new test(11));
        n.addchild(new test(-1),true);
        n.addchild(new test(-1),true);
        insert(n,5);
       insert(n,2);

        insert(n,3);
        insert(n,14);
        insert(n,15);
        insert(n,15);
        insert(n,3);
        insert(n,7);
        insert(n,6);
        //tes(n,3);
        Node.start();*/
        /*Face f1=new Face();
        f1.open(0,100,200);
        f1.close(70,100,200,new Point(70,150));
        Face f2=new Face();
        Face f3=new Face();

        f2.open(70,100,150);
        f2.leftup=f2.righup=true;
        f2.leftup=f3.righup=false;

        f3.open(70,150,200);
        f2.close(150,100,150);

        f3.close(150,150,200);
        Face f4=new Face();
        f4.open(150,100,200);
        f4.close(200,100,200);
        Face.start();
        f1.right.add(f3);
        f1.right.add(f2);

        f2.left.add(f1);
        f3.left.add(f1);
        f2.right.add(f4);
        f3.right.add(f4);
        f4.left.add(f3);
        f4.left.add(f2);
        Face.travel(f1,f4,new Point(35,120),new Point(175,120));*/
       Point_location_root root=new Point_location_root();
        root.new_segemnt(new Point(17,160),new Point(430,250));
        root.new_segemnt(new Point(20,140),new Point(400,200));

        root.new_segemnt(new Point(100,400),new Point(300,450));
       root.new_segemnt(new Point(40,100),new Point(90,100));
        root.new_segemnt(new Point(40,100),new Point(100,80));
        root.new_segemnt(new Point(250,420),new Point(400,420));
       root.new_segemnt(new Point(20,300),new Point(200,400));
        root.new_segemnt(new Point(40,300),new Point(400,300));
        root.new_segemnt(new Point(35,100),new Point(45,90));
        root.new_segemnt(new Point(35,100),new Point(45,110));
        root.new_segemnt(new Point(90,400),new Point(120,380));
        root.new_segemnt(new Point(90,400),new Point(120,420));

        Point_location_root.start();
        /*Node n=Node.make_root(new test(0));
        n.addchild(new test(1),false);
        n.addchild(new test(2),false);
        n.addchild(new test(3),false);
        n.getchild(0).data=new test(1);
        n.getchild(0).data=new test(1);
        Node.reconstruct();
        Node.start();*/
    }
}
