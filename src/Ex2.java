import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import classes.Load;
import classes.Values;
import classes.Directed_Weighted_Graph;
import classes.Directed_Weight_Graph_Algo;
import com.google.gson.internal.bind.util.ISO8601Utils;
import gui.Graph;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        Values values = Load.load(json_file);
        DirectedWeightedGraph ans = new Directed_Weighted_Graph(values);

        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new Directed_Weight_Graph_Algo();

        ans.init(getGrapg(json_file));

        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new Graph(alg);
    }

    public static void main(String[] args) {
        runGUI("./data/G1.json");
    }
}