package com.company;

import java.util.ArrayList;

public class Vectorpolar {


        ArrayList<Double> numbers=new ArrayList<>(3);
        Vectorpolar(Double n1,Double n2)
        {
            numbers.add(n1);
            numbers.add(n2);
            numbers.add(1.0);

        }
        Vectorpolar(ArrayList<Double> numbers)
        {

            this.numbers=numbers;
        }
        Double get_angel()
        {
            return numbers.get(0);
        }
        Double get_radis()
        {
            return numbers.get(1);
        }


}
