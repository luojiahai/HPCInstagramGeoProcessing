package main;

import java.util.ArrayList;
import mpi.*;

public class Grid {

    private ArrayList<Box> grid;

    public Grid() {
        grid = new ArrayList<Box>();
        initialize();
    }

    private void initialize() {
        grid.add(new Box("A1", 144.700000, 144.850000, -37.650000, -37.500000));
        grid.add(new Box("A2", 144.850000, 145.000000, -37.650000, -37.500000));
        grid.add(new Box("A3", 145.000000, 145.150000, -37.650000, -37.500000));
        grid.add(new Box("A4", 145.150000, 145.300000, -37.650000, -37.500000));
        grid.add(new Box("B1", 144.700000, 144.850000, -37.800000, -37.650000));
        grid.add(new Box("B2", 144.850000, 145.000000, -37.800000, -37.650000));
        grid.add(new Box("B3", 145.000000, 145.150000, -37.800000, -37.650000));
        grid.add(new Box("B4", 145.150000, 145.300000, -37.800000, -37.650000));
        grid.add(new Box("C1", 144.700000, 144.850000, -37.950000, -37.800000));
        grid.add(new Box("C2", 144.850000, 145.000000, -37.950000, -37.800000));
        grid.add(new Box("C3", 145.000000, 145.150000, -37.950000, -37.800000));
        grid.add(new Box("C4", 145.150000, 145.300000, -37.950000, -37.800000));
        grid.add(new Box("C5", 145.300000, 145.450000, -37.950000, -37.800000));
        grid.add(new Box("D3", 145.000000, 145.150000, -38.100000, -37.950000));
        grid.add(new Box("D4", 145.150000, 145.300000, -38.100000, -37.950000));
        grid.add(new Box("D5", 145.300000, 145.450000, -38.100000, -37.950000));
    }

    public Boolean compute(Double x, Double y) {
        for (Box box: grid) {
            if ((x >= box.getXmin()) && (x <= box.getXmax()) &&
                (y >= box.getYmin()) && (y <= box.getYmax())) {
                box.increment();
                return true;
            }
        }
        return false;
    }

    public void printResult() {
        for (Box box: grid) {
            System.out.println(box.getId() + ", " + box.getNumPosts());
        }
    }

}