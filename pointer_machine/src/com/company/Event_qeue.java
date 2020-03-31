package com.company;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import javax.swing.Timer;



public class Event_qeue implements ActionListener {



    ArrayDeque<Event> arrayDeque=new ArrayDeque<>();
    Event_qeue()
    {
        arrayDeque=new ArrayDeque<>();


    }
    void push(Event e)
    {
        arrayDeque.addLast(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        start();
    }
    void start()
    {
        if(arrayDeque.isEmpty())
            return;
        Event top=arrayDeque.getFirst();
        arrayDeque.removeFirst();
        top.task.run();
        Timer t=new Timer(top.time,this);
        t.setRepeats(false);
        t.start();
    }
}
