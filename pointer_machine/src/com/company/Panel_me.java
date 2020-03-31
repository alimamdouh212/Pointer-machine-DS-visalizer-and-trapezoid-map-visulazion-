package com.company;

import com.company.Pointlocatiom.Drawable_face;
import com.company.Pointlocatiom.Face;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class Panel_me extends JPanel implements ActionListener {

   LinkedBlockingQueue<task> draw_objectListarray;
    LinkedList<Draw_object> draw_objects=new LinkedList<>();
    static int la=-1;
   Panel_me(LinkedBlockingQueue ar)
   {
      draw_objectListarray=ar;


   }
  public void paintComponent(Graphics g)
  {
     super.paintComponent(g);

        removeAll();
        if(la!=draw_objects.size())
        {
            la=draw_objects.size();
        }
        ArrayList<Draw_object> dominate=new ArrayList<>();



      for(Iterator<Draw_object> it=draw_objects.iterator(); it.hasNext();) {
          {
              Draw_object object=it.next();
              if(!object.d)
              object.draw(g, this);
              else
                  dominate.add(object);
          }
            for(int i=0;i<dominate.size();i++)
            {
                dominate.get(i).draw(g,this);
                Component[] c=this.getComponents();


            }



     }

  }


    @Override
    public void actionPerformed(ActionEvent e) {
        start();
    }
     void start()
    {
        task task;
        try {
            task=draw_objectListarray.take();
            draw_objects=task.getdrabels(draw_objects);
            repaint();
            Timer t=new Timer(task.time,this);
            t.setRepeats(false);
            t.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
