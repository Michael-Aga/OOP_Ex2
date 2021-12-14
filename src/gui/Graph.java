package gui;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import classes.Node_Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Graph extends JFrame implements ActionListener {
    private final int width;
    private final int height;
    DirectedWeightedGraphAlgorithms DWG_Algo;
    JButton isConnected = new JButton();
    JButton center = new JButton();
    JButton tsp = new JButton();
    JButton shortestPath = new JButton();
    JButton loadNewGraph = new JButton();

    JButton addNode = new JButton();
    JButton removeNode = new JButton();
    JButton connect = new JButton();
    JButton getNode = new JButton();
    JButton getEdge = new JButton();
    JButton removeEdge = new JButton();

    JLabel isConnectedBool = new JLabel();
    JLabel centerBool = new JLabel();

    JPanel buttonsPanel = new JPanel();
    JPanel graphPanel = new JPanel();

    public Graph(DirectedWeightedGraphAlgorithms alg) {
        DWG_Algo = alg;

        isConnected.setText("Check if the graph is connected");
        isConnected.setBounds(0, 0, 250, 50);
        isConnected.addActionListener(this);

        center.setText("Check the center of the graph");
        center.setBounds(250, 0, 250, 50);
        center.addActionListener(this);

        tsp.setText("Check the tsp");
        tsp.setBounds(500, 0, 125, 50);
        tsp.addActionListener(this);

        shortestPath.setText("Check the shortest path");
        shortestPath.setBounds(625, 0, 200, 50);
        shortestPath.addActionListener(this);

        loadNewGraph.setText("Load a new graph");
        loadNewGraph.setBounds(825, 0, 150, 50);
        loadNewGraph.addActionListener(this);

        addNode.setText("Add a node");
        addNode.setBounds(975, 0, 100, 50);
        addNode.addActionListener(this);

        removeNode.setText("Remove a node");
        removeNode.setBounds(1075, 0, 150, 50);
        removeNode.addActionListener(this);

        connect.setText("Connect 2 nodes");
        connect.setBounds(1225, 0, 150, 50);
        connect.addActionListener(this);

        getNode.setText("Get a node");
        getNode.setBounds(1375, 0, 100, 50);
        getNode.addActionListener(this);

        getEdge.setText("Get edge");
        getEdge.setBounds(1475, 0, 100, 50);
        getEdge.addActionListener(this);

        removeEdge.setText("Remove edge");
        removeEdge.setBounds(1575, 0, 125, 50);
        removeEdge.addActionListener(this);


        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.setBounds(0, 0, 1700, 100);
        buttonsPanel.setLayout(null);


        graphPanel.setBackground(Color.BLACK);
        graphPanel.setBounds(0, 100, 1700, 800);
        graphPanel.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Directed Weight Graph");
        this.setLayout(null);
        width = 1716;
        height = 939;
        this.setSize(width, height);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

        buttonsPanel.add(isConnected);
        buttonsPanel.add(center);
        buttonsPanel.add(tsp);
        buttonsPanel.add(shortestPath);
        buttonsPanel.add(loadNewGraph);
        buttonsPanel.add(addNode);
        buttonsPanel.add(removeNode);
        buttonsPanel.add(connect);
        buttonsPanel.add(getNode);
        buttonsPanel.add(getEdge);
        buttonsPanel.add(removeEdge);

        buttonsPanel.add(isConnectedBool);
        buttonsPanel.add(centerBool);

        this.add(buttonsPanel);
        this.add(graphPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == isConnected) {
            boolean itIs = DWG_Algo.isConnected();
            isConnectedBool.setLayout(null);
            isConnectedBool.setBounds(0, 50, 250, 50);
            isConnectedBool.setBackground(Color.white);
            isConnectedBool.setHorizontalAlignment(JLabel.CENTER);
            isConnectedBool.setVerticalAlignment(JLabel.CENTER);
            if (itIs) {
                isConnectedBool.setText("The graph is connected !");
            } else {
                isConnectedBool.setText("The graph is not connected !");
            }
            isConnectedBool.setForeground(Color.white);
            isConnectedBool.setVisible(true);
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                isConnectedBool.setVisible(false);
            });

        } else if (e.getSource() == center) {
            int whatIsIt = DWG_Algo.center().getKey();
            centerBool.setLayout(null);
            centerBool.setBounds(250, 50, 250, 50);
            centerBool.setBackground(Color.white);
            centerBool.setHorizontalAlignment(JLabel.CENTER);
            centerBool.setVerticalAlignment(JLabel.CENTER);
            if (whatIsIt >= 0) {
                centerBool.setText("The center node is : " + DWG_Algo.center().getKey());
            } else {
                centerBool.setText("The graph is not connected !");
            }
            centerBool.setForeground(Color.white);
            centerBool.setVisible(true);
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                centerBool.setVisible(false);
            });

        } else if (e.getSource() == tsp) {
            String answerSRC = JOptionPane.showInputDialog("Please enter the nodes you want to test for example: (1,2,3,4)");
            String[] nodes = answerSRC.split(",");
            List<NodeData> listOfNodes = new ArrayList<>();

            for (String node : nodes) {
                listOfNodes.add(DWG_Algo.getGraph().getNode(Integer.parseInt(node)));
            }

            DWG_Algo.tsp(listOfNodes);

        } else if (e.getSource() == shortestPath) {
            String answerSRC = JOptionPane.showInputDialog("Please enter the source node id");
            String answerDEST = JOptionPane.showInputDialog("Please enter the destination node id");
            List<NodeData> showPath = new ArrayList<>();
            showPath = DWG_Algo.shortestPath(Integer.parseInt(answerSRC), Integer.parseInt(answerDEST));

        } else if (e.getSource() == loadNewGraph) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./data"));
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                this.dispose();
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                DWG_Algo.load(file);
                new Graph(DWG_Algo);
                this.repaint();

            }

        } else if (e.getSource() == addNode) {
            String answerID = JOptionPane.showInputDialog("Please enter the id of the node");
            String answerPOS = JOptionPane.showInputDialog("And a position (x,y,z)");
            DWG_Algo.getGraph().addNode(new Node_Data(Integer.parseInt(answerID), answerPOS));

        } else if (e.getSource() == removeNode) {
            this.dispose();
            String deleteNode = JOptionPane.showInputDialog("Please enter the id of the node");
            DWG_Algo.getGraph().removeNode(Integer.parseInt(deleteNode));
            new Graph(DWG_Algo);
            this.repaint();


        } else if (e.getSource() == connect) {
            String srcNode = JOptionPane.showInputDialog("Please enter the id of the source node");
            String destNode = JOptionPane.showInputDialog("Please enter the id of the destination node");
            String weightNode = JOptionPane.showInputDialog("Please enter the weight of the edge");

            DWG_Algo.getGraph().connect(Integer.parseInt(srcNode), Integer.parseInt(destNode), Double.parseDouble(weightNode));

        } else if (e.getSource() == getNode) {
            String nodeID = JOptionPane.showInputDialog("Please enter the id of the node");
            if (nodeID == null) JOptionPane.showMessageDialog(null, "Sorry there is no such node !");

            DWG_Algo.getGraph().getNode(Integer.parseInt(nodeID));

        } else if (e.getSource() == getEdge) {
            String srcID = JOptionPane.showInputDialog("Please enter the id of the source node");
            if (srcID == null) JOptionPane.showMessageDialog(null, "Sorry there is no such node !");
            String destID = JOptionPane.showInputDialog("Please enter the id of the destination node");
            if (destID == null) JOptionPane.showMessageDialog(null, "Sorry there is no such node !");

            DWG_Algo.getGraph().getEdge(Integer.parseInt(srcID), Integer.parseInt(destID));

        } else if (e.getSource() == removeEdge) {
            String srcID = JOptionPane.showInputDialog("Please enter the id of the source node");
            if (srcID == null) JOptionPane.showMessageDialog(null, "Sorry there is no such node !");
            String destID = JOptionPane.showInputDialog("Please enter the id of the destination node");
            if (destID == null) JOptionPane.showMessageDialog(null, "Sorry there is no such node !");

            DWG_Algo.getGraph().removeEdge(Integer.parseInt(srcID), Integer.parseInt(destID));
        }
    }

    @Override
    public void paint(Graphics g) {
        buttonsPanel.updateUI();

        Graphics2D g2D = (Graphics2D) g;
        Iterator<NodeData> runningOnNodes = DWG_Algo.getGraph().nodeIter();
        g2D.setColor(Color.white);

        double NewMinX = 100;
        double NewMinY = 200;
        double NewMaxX = this.width - 100;
        double NewMaxY = this.height - 100;
        double OldMinX = Double.MAX_VALUE;
        double OldMinY = Double.MAX_VALUE;
        double OldMaxX = 0;
        double OldMaxY = 0;

        while(runningOnNodes.hasNext()){
            NodeData n = runningOnNodes.next();
            if (n.getLocation().x() < OldMinX)
                OldMinX = n.getLocation().x();
            if (n.getLocation().y() < OldMinY)
                OldMinY = n.getLocation().y();
            if (n.getLocation().x() > OldMaxX)
                OldMaxX = n.getLocation().x();
            if (n.getLocation().y() > OldMaxY)
                OldMaxY = n.getLocation().y();
        }

        runningOnNodes = DWG_Algo.getGraph().nodeIter();

        while (runningOnNodes.hasNext()) {
            NodeData n = runningOnNodes.next();
            double OldRangeX = (OldMaxX - OldMinX);
            double NewRangeX = (NewMaxX - NewMinX);
            double NewValueX = (((n.getLocation().x() - OldMinX) * NewRangeX) / OldRangeX) + NewMinX;

            double OldRangeY = (OldMaxY - OldMinY);
            double NewRangeY = (NewMaxY - NewMinY);
            double NewValueY = (((n.getLocation().y() - OldMinY) * NewRangeY) / OldRangeY) + NewMinY;

            g2D.drawOval((int) NewValueX, (int) NewValueY, 30, 30);
            g2D.drawString(n.getKey() + "", (int) NewValueX, (int) NewValueY);
        }

        Iterator<EdgeData> runningOnEdges = DWG_Algo.getGraph().edgeIter();

        while (runningOnEdges.hasNext()) {
            EdgeData e = runningOnEdges.next();
            NodeData sourceNode = DWG_Algo.getGraph().getNode(e.getSrc());
            NodeData destinationNode = DWG_Algo.getGraph().getNode(e.getDest());

            double OldRangeX = OldMaxX - OldMinX;
            double NewRangeX = (NewMaxX - NewMinX);
            double NewSourceValueX = (((sourceNode.getLocation().x() - OldMinX) * NewRangeX) / OldRangeX) + NewMinX;

            double OldRangeY = (OldMaxY - OldMinY);
            double NewRangeY = (NewMaxY - NewMinY);
            double NewSourceValueY = (((sourceNode.getLocation().y() - OldMinY) * NewRangeY) / OldRangeY) + NewMinY;

            double NewDestinationValueX = (((destinationNode.getLocation().x() - OldMinX) * NewRangeX) / OldRangeX) + NewMinX;
            double NewDestinationValueY = (((destinationNode.getLocation().y() - OldMinY) * NewRangeY) / OldRangeY) + NewMinY;

            g2D.drawLine((int) (NewSourceValueX + 15), (int) (NewSourceValueY + 15), (int) (NewDestinationValueX + 15), (int) (NewDestinationValueY + 15));
        }
    }
}