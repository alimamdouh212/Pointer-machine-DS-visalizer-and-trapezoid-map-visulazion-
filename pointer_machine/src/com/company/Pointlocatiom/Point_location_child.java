package com.company.Pointlocatiom;

import com.company.Data;
import com.company.Node;
import com.company.Point;

public abstract class Point_location_child extends Node {
    int rank;
    Point_location_child(Data d)
    {


        super(d,true);
    }
    protected void make_new_child(int i,Point_location_child child)
    {
        super.make_new_child(i,child);

        child.rank=i;

    }
    protected void replacr(Point_location_child newnode)
    {
        parent.make_new_child(rank,newnode);
    }
    abstract Point_location_child creatdeplicate();
    Point_location_child dfs_deplicat()
    {
        Point_location_child u=creatdeplicate();
        for(int i=0;i<childern.size();i++)
        {
            u.make_new_child(i,((Point_location_child) childern.get(i)).dfs_deplicat());
        }
        return u;

    }

}
