package com.company.Pointlocatiom;

import com.company.Data;

import java.awt.*;

public class yquere extends queryobject {
    Straight_line mylinel;


    yquere(Straight_line line)
    {
       /* super(new Data<String>("s"+line.id) {
            @Override
            public String tostring() {
                return my_data;
            }
        });*/

        mylinel=line;
        text="s"+line.id;

    }


    @Override
    int left_or_right(Point point) {
        int r=mylinel.aboveorbelow(point);
        r=-r;
        return r;
    }
}
