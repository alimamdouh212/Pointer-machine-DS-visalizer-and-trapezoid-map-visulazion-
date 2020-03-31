package com.company.Pointlocatiom;

import com.company.Data;
import com.company.Node;
import com.company.Point;

public class Query_node extends Point_location_child {
    queryobject myqueryobject;
    Query_node(queryobject q) {
        super(new Data<String>(q.text) {
            @Override
            public String tostring() {
                return my_data;
            }
        });
        myqueryobject=q;
    }
    Point_location_child getrightnode(java.awt.Point point)
    {
        if(myqueryobject.left_or_right(point)==1)
            return (Point_location_child)childern.get(1);
        else
            return (Point_location_child)childern.get(0);


    }


    @Override
    Query_node creatdeplicate() {
        return new Query_node(myqueryobject);
    }
}

