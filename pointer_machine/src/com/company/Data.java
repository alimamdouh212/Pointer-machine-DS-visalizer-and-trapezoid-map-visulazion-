package com.company;

public abstract class  Data<T>  {
    public T my_data;
    public Data(T my_dat)
    {this.my_data=my_dat;}



    public abstract String tostring();

}
