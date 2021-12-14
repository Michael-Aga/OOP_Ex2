package classes;

import api.EdgeData;
import api.NodeData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Load {
    public static Values load(String json_file) {
        File input = new File(json_file);

        try { //Getting all the information
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayNodes = fileObject.get("Nodes").getAsJsonArray();
            JsonArray jsonArrayEdges = fileObject.get("Edges").getAsJsonArray();

            List<NodeData> nodes = new ArrayList<>();
            List<EdgeData> edges = new ArrayList<>();

            for (JsonElement jNode : jsonArrayNodes) {
                JsonObject nodeJsonObject = jNode.getAsJsonObject();

                String pos = null;
                if (nodeJsonObject.has("pos")) {
                    pos = nodeJsonObject.get("pos").getAsString();
                }

                int id = 0;
                if (nodeJsonObject.has("id")) {
                    id = nodeJsonObject.get("id").getAsInt();
                }

                Node_Data node = new Node_Data(id, pos);
                nodes.add(node);
            }

            //Getting all the edges
            for (JsonElement jEdge : jsonArrayEdges) {
                JsonObject edgeJsonObject = jEdge.getAsJsonObject();

                int src = 0;
                if (edgeJsonObject.has("src")) {
                    src = edgeJsonObject.get("src").getAsInt();
                }

                int dest = 0;
                if (edgeJsonObject.has("dest")) {
                    dest = edgeJsonObject.get("dest").getAsInt();
                }

                double w = 0.0;
                if (edgeJsonObject.has("w")) {
                    w = edgeJsonObject.get("w").getAsDouble();
                }

                Edge_Data edge = new Edge_Data(src, dest, w);
                edges.add(edge);
            }

            HashMap<Integer, NodeData> Node_Data = convertNodesList(nodes);
            HashMap<Integer, HashMap<Integer, EdgeData>> toEdges = convertToEdge(edges);
            HashMap<Integer, HashMap<Integer, EdgeData>> fromEdges = convertFromEdge(edges);

            return new Values(Node_Data, toEdges, fromEdges);

        } catch (FileNotFoundException e) {
            System.err.println("Error file not found ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing input file!");
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<Integer, NodeData> convertNodesList(List<NodeData> nodeList) {
        HashMap<Integer, NodeData> nodes = new HashMap<>();
        for (NodeData node : nodeList) {
            nodes.put(node.getKey(), node);
        }
        return nodes;
    }

    public static HashMap<Integer, HashMap<Integer, EdgeData>> convertToEdge(List<EdgeData> edgeList) {
        HashMap<Integer, HashMap<Integer, EdgeData>> toMap = new HashMap<>();
        HashMap<Integer, EdgeData> edgeHash = new HashMap<>();
        int num_of_src = 0;

        for (EdgeData edge : edgeList) {

            if (toMap.containsKey(edge.getSrc())) {
                edgeHash = toMap.get(edge.getSrc());
                num_of_src = edge.getSrc();
            }
            if (num_of_src != edge.getSrc()) {
                edgeHash = new HashMap<>();
                num_of_src = edge.getSrc();
            }

            edgeHash.put(edge.getDest(), edge);
            toMap.put(edge.getSrc(), edgeHash);

        }
        return toMap;
    }

    public static HashMap<Integer, HashMap<Integer, EdgeData>> convertFromEdge(List<EdgeData> edgeList) {
        HashMap<Integer, HashMap<Integer, EdgeData>> fromMap = new HashMap<>();
        HashMap<Integer, EdgeData> edgeHash = new HashMap<>();
        int num_of_dest = 0;


        for (EdgeData edge : edgeList) {

            if (fromMap.containsKey(edge.getDest())) {
                edgeHash = fromMap.get(edge.getDest());
                num_of_dest = edge.getDest();
            }
            if (num_of_dest != edge.getDest()) {
                edgeHash = new HashMap<>();
                num_of_dest = edge.getDest();
            }

            edgeHash.put(edge.getSrc(), edge);
            fromMap.put(edge.getDest(), edgeHash);

        }
        return fromMap;
    }
}