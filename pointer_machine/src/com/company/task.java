package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class task {

    int time;
   // ArrayList<Draw_object> arrayList;
    /*task(int time, ArrayList<Draw_object> ar)
    {
        this.time=time;
        this.arrayList=ar;
    }*/
   public task(int t)
    {time=t;}
   public abstract LinkedList<Draw_object> getdrabels(LinkedList<Draw_object> list);

}
