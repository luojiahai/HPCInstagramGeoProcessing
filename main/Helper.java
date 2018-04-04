package main;

import mpi.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class Helper {

    private String[] args;

    private File file;
    private Scanner scanner;

    private Grid grid;

    public Helper(String args[]) {
        this.args = args;
        grid = new Grid();
    }

    public void run() {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        initialize(args[3], rank, size);
        int counter = 0;
        while (scanner.hasNextLine()) {
            if (counter == 0) {
                String str = scanner.nextLine();
                if (!str.startsWith("{")) {
                    continue;
                }
                JSONObject obj = new JSONObject(str);
                if (obj.getJSONObject("doc").has("coordinates")) {
                    Double x = obj.getJSONObject("doc").getJSONObject("coordinates").getJSONArray("coordinates").getDouble(1);
                    Double y = obj.getJSONObject("doc").getJSONObject("coordinates").getJSONArray("coordinates").getDouble(0);
                    grid.compute(x, y);
                }
            } else {
                scanner.nextLine();
            }

            counter++;
            if (counter == size) {
                counter = 0;
            }
        }
        MPI.Finalize();
        grid.printResult();
    }

    private void initialize(String fileName, int rank, int size) {
        try {
            file = new File(fileName);
            scanner = new Scanner(file);
            scanner.nextLine();
            int i = rank;
            while (i < (size - 1)) {
                scanner.nextLine();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}