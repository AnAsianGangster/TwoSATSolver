package TwoSATSolver;

import java.net.Inet4Address;
import java.util.*;

public class TheLastSolver {
    // take in the hashmap and do dfs
    // find SCC

        HashMap<Integer, LinkedList<Integer>> theGraph;
        HashMap<Integer, LinkedList<Integer>> theGraphInv;
//        HashMap<Integer, Boolean> visited;
        LinkedList<Integer> visited = new LinkedList<>();
        LinkedList<Integer> visitedInv = new LinkedList<>();

        LinkedList<Integer> tempSCC;
        LinkedList<LinkedList<Integer>> theSCC = new LinkedList<LinkedList<Integer>>();

        HashMap<Integer, LinkedList<Integer>> topoSCC = new HashMap<Integer, LinkedList<Integer>>();
        HashMap<Integer, Integer> topoSCC2 = new HashMap<>();
        HashMap<Integer, Boolean> answer = new HashMap<>();
        String answerInBinary = "";


    Integer counter = 0;

        Stack<Integer> stack = new Stack<>();

        TheLastSolver(HashMap<Integer, LinkedList<Integer>> theGraph, HashMap<Integer, LinkedList<Integer>> theGraphInv){
            this.theGraph = theGraph;
            this.theGraphInv = theGraphInv;
        }

        // The return result
//        ArrayList<ArrayList<Integer>> theSCCs = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findSCC(HashMap<Integer, LinkedList<Integer>> theGraph, HashMap<Integer, LinkedList<Integer>> theInvGraph){

        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(theGraph.keySet());
        Integer randomKey = keys.get( random.nextInt(keys.size()) );
//        LinkedList value = theGraph.get(randomKey);


        return null;
    }






//    // A function used by DFS
//    void DFSUtil(int v) {
//        // Mark the current node as visited and print it
////        System.out.println(v);
//        visited.add(v);
//        System.out.print(v + " ");
//        if (!theGraph.containsKey(v)) {
//            return;
//        }
//
//        // Recur for all the vertices adjacent to this vertex
//        Iterator<Integer> i = theGraph.get(v).listIterator();
//        while (i.hasNext()) {
//            int n = i.next();
//            if (!visited.contains(n)){
//                DFSUtil(n);
//            }
//        }
//    }
//
//    void DFSUtilInv(int v) {
//        // Mark the current node as visited and print it
////        System.out.println(v);
//        visitedInv.add(v);
//        System.out.print(v + " ");
//        if (!theGraphInv.containsKey(v)) {
//            return;
//        }
//
//        // Recur for all the vertices adjacent to this vertex
//        Iterator<Integer> i = theGraphInv.get(v).listIterator();
//        while (i.hasNext()) {
//            int n = i.next();
//            if (!visitedInv.contains(n)){
//                DFSUtilInv(n);
//            }
//        }
//    }

    /**
     * v is the starting vertex
     */

    // The function to do DFS traversal. It uses recursive DFSUtil()
//    void DFS(int v) {
//        // Mark all the vertices as not visited(set as
//        // false by default in java)
////        visited = new LinkedList<>();
//
//        // Call the recursive helper function to print DFS traversal
//        for (int i:theGraph.keySet()) {
////            System.out.println(i);
//            if (!visited.contains(i)) {
//                DFSUtil(i);
////                System.out.println(visited +"#######");
//            }
//        }
//
//        System.out.println();
//
//        //DFSInv
//        for(int j:theGraphInv.keySet()){
//            if(!visitedInv.contains(j)){
//                DFSUtilInv(j);
//            }
//        }
//    }



    // A recursive function to print DFS starting from v


    void DFSUtil(int v, LinkedList visited) {
        // Mark the current node as visited and print it
//        visited.contains(v);
        visited.add(v);
//        System.out.print(v + " ");
        tempSCC.add(v);
        topoSCC2.put(v,counter);

        if (!theGraphInv.containsKey(v)) {
            return;
        }

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = theGraphInv.get(v).listIterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited.contains(n)) {
//                System.out.println(n+"wasnt visited");
                DFSUtil(n, visited);
            }
        }
    }




    void fillOrder(int v, Stack stack)
    {
//        System.out.println(v);
        // Mark the current node as visited and print it
//        System.out.println(visited+"*****");
//        System.out.println(v);
        visited.add(v);
        if (!theGraph.containsKey(v)) {
            return;
        }

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = theGraph.get(v).listIterator();
        while (i.hasNext()){
            int n = i.next();
            if(!visited.contains(n))
                fillOrder(n,stack);
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
//        System.out.println(v+ "###");
        stack.push(v);
    }






    // The main function that finds and prints all strongly
    // connected components
    public String printSCCs() {
//        Stack stack = new Stack();

        // Mark all the vertices as not visited (For first DFS)
//        boolean visited[] = new boolean[V];
//        for(int i = 0; i < V; i++)
//            visited[i] = false;

        // Fill vertices in stack according to their finishing
        // times
        for (int i:theGraph.keySet()){
//            System.out.println(i);
//            System.out.println(visited);
            if (!visited.contains(i)){
                fillOrder(i, stack);
            }
        }

        // Create a reversed graph
//        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
//        for (int j:theGraphInv.keySet()){
        this.visited = new LinkedList<>();
//        System.out.println("2nd part");
//        System.out.println(stack+"####");

        // Now process all vertices in order defined by Stack
        while (!stack.empty()) {
            // Pop a vertex from stack
//            System.out.println(stack);
            int v = (int)stack.pop();
//            System.out.println(stack);

            // Print Strongly connected component of the popped vertex
            if (!visited.contains(v)) {
                tempSCC = new LinkedList<>();
                DFSUtil(v, visited);
                theSCC.add(tempSCC);
                topoSCC.put(counter,tempSCC);
                counter = counter + 1;
//                System.out.println(counter);
//                System.out.println();
//                System.out.println(theSCC);
                for (Integer findUnsat :
                        tempSCC) {
                    if (tempSCC.contains((-1) * findUnsat)){
                        System.out.println("FORMULA UNSATISFIABLE");
                        return "FORMULA UNSATISFIABLE";
                    }
                }
            }
        }
//        System.out.println(topoSCC);
//        System.out.println(topoSCC2);



        for(Integer key:topoSCC2.keySet()){
            if(key<0){
                continue;
            }
            int u = topoSCC2.get(key);
            if(topoSCC2.get((-1)*key) == null){

            }
            int v = topoSCC2.get((-1)*key);
            if(u < v) {
                answer.put(key,false);
            }else{
                answer.put(key,true);
            }
        }
//        System.out.println(answer);
        for (Integer key :
                answer.keySet()) {
            if (answer.get(key)){
                answerInBinary = answerInBinary + "1 ";
            } else {
                answerInBinary = answerInBinary + "0 ";
            }
        }
        System.out.println(answerInBinary.trim() + ".");
        System.out.println("FORMULA SATISFIABLE");
        return "FORMULA SATISFIABLE";
    }
}
