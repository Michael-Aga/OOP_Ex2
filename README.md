
# Weighted directed graphs

<img src="https://user-images.githubusercontent.com/62290677/146029438-b362acb8-3da2-4181-893c-aa9820e1ca57.png" alt="drawing" width="650"/>

Create a weighted graph and tests shown below:




## Creators

 - [Goel Didi](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Michael Agarkov](https://github.com/matiassingers/awesome-readme)




#  Classes: 
## Node_data:
- This class implements the interface of a NodeData. It holds a int key for uniqe ID and his location.
- GeoLocation location for x,y,z coordinates for each node.
- double weight for cost (Initialized as 0 for all nodes).
- int tag for implementing algorithms.
- String info for holding information about the node as a string (If there was an entrance).

## Edge_Data
- This class implements the interface of an EdgeData. It holds two IDs, one for the source's ID and one for the destination's. 
- double weight for the cost of going through from one side to the other side.

## Directed_Weighted_Graph
- This class holds 3 important HashMaps, one for nodes and one fromEdges and one for toEdges, also this class has 2 constructors:
###### One is for Loads the values to create graph.
###### One is an empty constructor.

#### the HashMaps works as follows:
######
###### Node_Data contains the ID of that Node and its location.
#####
###### toEdges and fromEdges works the same, The key it contains will determine the src and his value contents another HashMap that contains the target and the edge that connects them.
####

- #### connect: what it does is connect the 2 vertices that the function receives, and its contains the weight of the connection between them
######   
- #### removeEdge: remove the Edge from both the HashMap to and from edge.
######
- #### removeNode: remove the sec and dest from both the HashMap to and from edge, and remove the node fron his HashMap.
## Directed_Weight_Graph_Algo
- #### This is the graphClass that all the algorithms would operate in.
We create a graph from the previous class, and on that we will run the algorithm.

- #### isConnected: In this function we check that if we do not have a vertex that is not connected to any other vertex on the graph, if the graph is connected we will receives true and if not we will receives false. 
- #### shortestPathDist: this algorithm calculates the shortest way from vertex to his destination Which includes the calculation the weight of the edges on the way.
- #### shortestPath: this algorithm return a list of the shortest way from vertex to his destination by shortest Path to the longest Path. 
- #### Center: We will perform a preliminary check if all the vertices are connected(isConnected), We will then check what the center vertex is, that is: the maximum distance between the same vertex and another vertex in the graph is the minimum among all others.
- #### TSP: The travelling salesman problem, that is an NP-hard problem in combinatorial optimization, important in theoretical computer science and operations research. 
- #### Save and Load: save or load a gson graph file.



# Run time

#### G1,G2,G3 Tests


 ### Center

| G1 |G2     | G3                |
| :-------- | :------- | :------------------------- |
| `58.0015 ms` | `93.9998 ms` | `402.0016 ms` |

### isConnected

| G1 |G2     | G3                |
| :-------- | :------- | :------------------------- |
| True: `30.9984 ms` | True: `31.9981 ms` | True: `34.0002 ms`  |

### init

| G1 |G2     | G3                |
| :-------- | :------- | :------------------------- |
| `28.9986 ms` | `31.0006 ms` |`33.9988 ms` |

### tsp

| G1 |G2     | G3                |
| :-------- | :------- | :------------------------- |
| `45.0005` | `42.9983` | `74.9971` |









# UML

![MyProjectImage2](https://user-images.githubusercontent.com/88629415/146026703-29773cba-9899-4e87-86b9-aa1d53ed83b5.png)


## Run Locally

Clone the project

```bash
  git clone https://github.com/GoelDidi/OOP_Ex2.git
```

Go to the project directory

```bash
  cd OOP_Ex2
```

Run the program

```bash
  java -jar Ex2.jar G1.json
```


## GUI

![image](https://user-images.githubusercontent.com/88629415/146028980-294b2c7b-ef2b-4cd8-8391-7d5d30877b05.png)
![image](https://user-images.githubusercontent.com/88629415/146029169-e08d9950-10d5-49e2-a91b-4b31010ec20e.png)
![image](https://user-images.githubusercontent.com/88629415/146029363-f3c3bfda-be75-4acb-b212-8068716eee5f.png)
![image](https://user-images.githubusercontent.com/88629415/146029409-f66c26e5-3b29-4b0a-94de-38ee2c0a5d26.png)
