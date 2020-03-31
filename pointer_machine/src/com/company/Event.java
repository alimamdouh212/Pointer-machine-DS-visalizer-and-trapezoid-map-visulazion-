package com.company;

public  class Event {
    int time=0;
    Runnable task;
    Event(int time,Runnable task)
    {
        this.time=time;
        this.task=task;
    }


}
