package com.company;

import java.util.ArrayList;
import java.util.Vector;

public class Matrix {
    ArrayList<ArrayList<Double>> numbers=new ArrayList<>(3);

    Matrix(Double n11, Double n12, Double n13, Double n21, Double n22, Double n23)
    {
        for(int i=0;i<3;i++)
        {
            numbers.add(new ArrayList<>(3));
        }
        numbers.get(0).add(n11);
        numbers.get(0).add(n12);
        numbers.get(0).add(n13);
        numbers.get(1).add(n21);
        numbers.get(1).add(n22);
        numbers.get(1).add(n23);
        numbers.get(2).add(0.0);
        numbers.get(2).add(0.0);
        numbers.get(2).add(1.0);

    }
    Matrix(ArrayList<ArrayList<Double>> numbers)
    {
        this.numbers=numbers;

    }
    static Matrix rotate(Double n)
    {
        return new Matrix(1.0,0.0,n,0.0,1.0,0.0);


    }
    static Matrix shrink(int n)
    {

        return new Matrix(1.0/(n),0.0,0.0,0.0,1.0,0.0);
    }
    static Matrix translate(Double n)
    {


        return new Matrix(1.0,0.0,0.0,0.0,1.0,n);
    }


    Matrix mul(Matrix m1)
    {
        ArrayList<ArrayList<Double>> valus=new ArrayList<>();
        for(int i=0;i<3;i++)
        {
            valus.add(new ArrayList<>(3));
        }
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                Double v=new Double(0);
                for(int k=0;k<3;k++)
                 v+=(numbers.get(i).get(k)*m1.numbers.get(k).get(j));
                valus.get(i).add(v);


            }



    return new Matrix(valus);
    }
    static Matrix iden()
    {
        return new Matrix(1.0,0.0,0.0,0.0,1.0,0.0);
    }
    Vectorpolar mul(Vectorpolar v)
    {
        ArrayList<Double> valus=new ArrayList<>(3);
        for(int i=0;i<3;i++)
        {
            Double dou=new Double(0);
            for (int j = 0; j < 3; j++)
                dou+=numbers.get(i).get(j)*v.numbers.get(j);
            valus.add(dou);

        }
        return new Vectorpolar(valus);
    }



}
