package main;

import mpi.*;

public class Box {

    private String id;
    private Double xmin;
    private Double xmax;
    private Double ymin;
    private Double ymax;

    private int numPosts;

    public Box(String id, Double xmin, Double xmax, Double ymin, Double ymax) {
        this.id = id;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.numPosts = 0;
    }

    public String getId() {
        return id;
    }

    public Double getXmin() {
        return xmin;
    }

    public Double getXmax() {
        return xmax;
    }

    public Double getYmin() {
        return ymin;
    }

    public Double getYmax() {
        return ymax;
    }

    public void increment() {
        numPosts++;
    }

    public void increment(long n) {
        numPosts += n;
    }

    public int getNumPosts() {
        return numPosts;
    }

}
