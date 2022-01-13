package classes;

import api.DirectedWeightedGraph;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is for the function save that saves a file as json
 */
public class Save {
    public static boolean save(String fileName, DirectedWeightedGraph graph) {
        Gson gson = new Gson();
        String json = gson.toJson(graph);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(json);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
