package main;

import mpi.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class Helper {

    private String[] args;

    private File file;
    private BufferedReader reader;

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
        try {        
            String str;
            int counter = 0;
            while ((str = reader.readLine()) != null) {
                if (counter == 0) {
                    if (!str.startsWith("{")) {
                        continue;
                    }
                    JSONObject obj = new JSONObject(str);
                    if (obj.getJSONObject("doc").has("coordinates")) {
                        if (obj.getJSONObject("doc").getJSONObject("coordinates") != null) {
                            try {
                                Double x = obj.getJSONObject("doc").getJSONObject("coordinates").getJSONArray("coordinates").getDouble(1);
                                Double y = obj.getJSONObject("doc").getJSONObject("coordinates").getJSONArray("coordinates").getDouble(0);
                                grid.compute(x, y);
                            } catch (Exception e) {
                            }
                        }
                    }
                }

                counter++;
                if (counter == size) {
                    counter = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        long data[] = new long[grid.size()];
        if (rank == 0) {
            for (int i = 1; i < size; i++) {
                MPI.COMM_WORLD.Recv(data, 0, grid.size(), MPI.LONG, i, 1);
                grid.cumulate(data);
            }
            grid.printResult();
        } else {
            data = grid.toArray();
            MPI.COMM_WORLD.Send(data, 0, grid.size(), MPI.LONG, 0, 1);
        }
        MPI.Finalize();
    }

    private void initialize(String fileName, int rank, int size) {
        try {
            file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            int i = rank;
            while (i > 0) {
                reader.readLine();
                i--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}