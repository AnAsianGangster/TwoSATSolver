package TwoSATSolver;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Parser {

    private static HashMap<Integer, LinkedList<Integer>> adjList = new HashMap<Integer,LinkedList<Integer>>();
    private static HashMap<Integer, LinkedList<Integer>> adjListInv = new HashMap<Integer,LinkedList<Integer>>();

    public Parser(String s) {
        parse(s);
    }

    private void parse(String fileName) {

        BufferedReader br = null;
        try {
            // file path
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    // first char to determine the nature of the line
                    char c = line.charAt(0);
                    if (c == 'c') {
                        // comment line(in CNF)
                    } else if (c == 'p') {
                        // command line(in CNF)
                    } else {
                        line = line.trim().replaceAll(" +", " ");
                        String[] clausesInStr = line.split(" ");

                        // set up the edge (*2)
                        int[] theEdge = new int[2];
                        for (int i = 0; i < clausesInStr.length - 1; i++) {
                            int temp0neLit = Integer.parseInt(clausesInStr[i]);
                            theEdge[i] = temp0neLit;
                            adjList.put(theEdge[i], new LinkedList<>());
                            adjListInv.put(theEdge[i], new LinkedList<>());
                        }

                        // add into adjList
                        if(!adjList.containsKey((-1)*theEdge[0])) {
                            LinkedList<Integer> edge = new LinkedList<Integer>();
                            edge.add(theEdge[1]);
                            adjList.put((-1)*theEdge[0],edge);
                        }
                        if (!adjList.containsKey((-1)*theEdge[1])){
                            LinkedList<Integer> edge = new LinkedList<Integer>();
                            edge.add(theEdge[0]);
                            adjList.put((-1)*theEdge[1], edge);
                        }
                        if(adjList.containsKey((-1) * theEdge[0])){
                            // contains that key
                            LinkedList oldValue = adjList.put((-1)*theEdge[0], new LinkedList<>());
                            adjList.put((-1)*theEdge[0], oldValue);
                            if(!oldValue.contains(theEdge[1])){
                                oldValue.add(theEdge[1]);
                                adjList.put((-1)*theEdge[0], oldValue);
                            }
                        }
                        if(adjList.containsKey((-1)*theEdge[1])){
                            LinkedList oldValue = adjList.put((-1)*theEdge[1], new LinkedList<>());
                            adjList.put((-1)*theEdge[1], oldValue);
                            if(!oldValue.contains(theEdge[0])){
                                oldValue.add(theEdge[0]);
                                adjList.put((-1)*theEdge[1], oldValue);
                            }
                        }

                        // add adjListInv
                        if(!adjListInv.containsKey(theEdge[1])) {
                            LinkedList<Integer> edge = new LinkedList<Integer>();
                            edge.add((-1)*theEdge[0]);
                            adjListInv.put(theEdge[1],edge);
                        }
                        if (!adjListInv.containsKey(theEdge[0])){
                            LinkedList<Integer> edge = new LinkedList<Integer>();
                            edge.add((-1)*theEdge[1]);
                            adjListInv.put(theEdge[0], edge);
                        }
                        if(adjListInv.containsKey(theEdge[1])){
                            // contains that key
                            LinkedList oldValue = adjListInv.put(theEdge[1], new LinkedList<>());
                            adjListInv.put(theEdge[1], oldValue);
                            if(!oldValue.contains((-1)*theEdge[0])){
                                oldValue.add((-1)*theEdge[0]);
                                adjListInv.put(theEdge[1], oldValue);
                            }
                        }
                        if(adjListInv.containsKey(theEdge[0])){
                            LinkedList oldValue = adjListInv.put(theEdge[0], new LinkedList<>());
                            adjListInv.put(theEdge[0], oldValue);
                            if(!oldValue.contains((-1)*theEdge[1])){
                                oldValue.add((-1)*theEdge[1]);
                                adjListInv.put(theEdge[0], oldValue);
                            }
                        }

                    }
                } else {
                    //TODO: handle multi-equations in a single .CNF file
                    // - Empty line is the break between each equation
                    System.out.println("Empty line");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static HashMap<Integer, LinkedList<Integer>> getAdjList() {
        return adjList;
    }

    public static HashMap<Integer, LinkedList<Integer>> getAdjListInv() {
        return adjListInv;
    }

    public static void main(String[] args) {
        Parser theParser = new Parser("/Users/anasiangangster/Downloads/50002/2d-demo/unsat2.cnf");
        HashMap<Integer, LinkedList<Integer>> theGraph = getAdjList();
        HashMap<Integer, LinkedList<Integer>> theInvGraph = getAdjListInv();
//        System.out.println(theGraph);
//        System.out.println(theInvGraph);

//        HashMap<Integer, LinkedList<Integer>> theTestGraph = new HashMap<Integer, LinkedList<Integer>>();
//        theTestGraph.put(1,new LinkedList<>(Arrays.asList(4,2)));
//        theTestGraph.put(4,new LinkedList<>(Arrays.asList(-5)));
//        theTestGraph.put(-5,new LinkedList<>(Arrays.asList(6)));
//        theTestGraph.put(6,new LinkedList<>(Arrays.asList(4)));
//        theTestGraph.put(2,new LinkedList<>(Arrays.asList(3)));
//        theTestGraph.put(3,new LinkedList<>(Arrays.asList(1)));
////        theTestGraph.put(1,new LinkedList<>(Arrays.asList(4)));
////        theTestGraph.put(1,new LinkedList<>(Arrays.asList(4)));
////        theTestGraph.put(1,new LinkedList<>(Arrays.asList(4)));
//        HashMap<Integer, LinkedList<Integer>> theTestGraphInv = new HashMap<Integer, LinkedList<Integer>>();
//        theTestGraphInv.put(1,new LinkedList<>(Arrays.asList(3)));
//        theTestGraphInv.put(3,new LinkedList<>(Arrays.asList(2)));
//        theTestGraphInv.put(2,new LinkedList<>(Arrays.asList(1)));
//        theTestGraphInv.put(4,new LinkedList<>(Arrays.asList(1,6)));
//        theTestGraphInv.put(6,new LinkedList<>(Arrays.asList(-5)));
//        theTestGraphInv.put(-5,new LinkedList<>(Arrays.asList(4)));
//        System.out.println(theTestGraph);
//        System.out.println(theTestGraphInv);

        TheLastSolver theSolver = new TheLastSolver(theGraph,theInvGraph);
        theSolver.printSCCs();
//        theSolver.DFS(-1);
    }
}