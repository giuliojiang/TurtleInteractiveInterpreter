package turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import turtle.util.Rotation;

public class TurtleCluster implements Turtle {

    private List<Turtle> cluster;
    private HashMap<String, Turtle> allTurtles;
    private String clusterName;
    
    public TurtleCluster(HashMap<String, Turtle> allTurtles, String clusterName)
    {
        this.allTurtles = allTurtles;
        this.clusterName = clusterName;
        cluster = new ArrayList<Turtle>();
    }
    
    public void put(String name, Turtle t)
    {
        cluster.add(t);
        allTurtles.put(clusterName + "." + name, t);
    }
    
    @Override
    public void changeBrush(char c) {
        for (Turtle t : cluster)
        {
            t.changeBrush(c);
        }
        
    }

    @Override
    public void liftPen() {
        for (Turtle t : cluster)
        {
            t.liftPen();
        }
        
    }

    @Override
    public void move(int n) {
        for (Turtle t : cluster)
        {
            t.move(n);
        }
        
    }

    @Override
    public void putPen() {
        for (Turtle t : cluster)
        {
            t.putPen();
        }
        
    }

    @Override
    public void rotate(Rotation r, int x) {
        for (Turtle t : cluster)
        {
            t.rotate(r,x);
        }
        
    }

    @Override
    public void writeMark() {
        for (Turtle t : cluster)
        {
            t.writeMark();
        }
        
    }
    
    public String getName()
    {
        return clusterName;
    }

    public String status()
    {
        StringBuilder out = new StringBuilder();
        out.append("Cluster " + clusterName + ": {");
        for (Turtle t : cluster)
        {
            out.append(t.getName() + " ");
        }
        out.append("}");
        return out.toString();
    }
   
    
}
