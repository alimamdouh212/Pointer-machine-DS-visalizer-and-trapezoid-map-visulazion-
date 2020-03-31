package com.company.Pointlocatiom;

import com.company.Data;

public class Face_node extends Point_location_child
{
    Face myface;
    String text;
    Face_node(Face f)
    {
        super(new Data<String>("f"+f.id) {
            @Override
            public String tostring() {
                return my_data;
            }
        });
        myface=f;
        myface.representers.add(this);
        text="f"+f.id;
    }
    void bond(Face f)
    {
        f.representers.add(this);
        myface=f;
    }

    @Override
    Face_node creatdeplicate() {
        return new Face_node(myface);
    }
}
