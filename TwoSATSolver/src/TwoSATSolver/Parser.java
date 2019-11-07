package TwoSATSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public Parser(String s) {
        parse(s);
    }

    private Graph parse(String fileName) {

        Graph g = null;

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
                        // empty, Do nothing for comment line(in CNF file)
                    } else if (c == 'p') {
                        //TODO: command line(in CNF) for parameters of boolean equations
                        // - parameters for # of clauses(2)  &  # of varibles(3)
                        String[] commandLine = line.split(" ");
//                        System.out.println((commandLine[2]);
                        g = new Graph(2*Integer.valueOf(commandLine[2]));
//                        System.out.println("commandLine = " + commandLine[2] + "#############");
                    } else {
                        line = line.trim().replaceAll(" +", " ");
                        System.out.println(line);
                        String[] clausesInStr = line.split(" ");
//                        System.out.println(clausesInStr);

                        int[] theEdge = new int[2];
//                        System.out.println(clausesInStr);

                        for (int i = 0; i < clausesInStr.length - 1; i++) {
                            // add into the literalArrayList
                            // never add in the zero
//                            System.out.println(clausesInStr[i]);
                            int temp0neLit = Integer.parseInt(clausesInStr[i]);
                            theEdge[i] = temp0neLit;
                            System.out.println("theEdge[i] = " + theEdge[i]);
                        }

                        // add the two edges
//                        System.out.println("######");
//                        System.out.println("theEdge.toString() = " + theEdge.toString());

                        //now the values are in integer format
                        g.addEdge((-1)*theEdge[0],theEdge[1]);
                        g.addEdge((-1)*theEdge[1],theEdge[0]);

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
        return g;
    }

    public static void main(String[] args) {
        Parser theParser = new Parser("/Users/anasiangangster/Downloads/50002/2d-demo/unsat2.cnf");
//        System.out.println(theParser);
    }
}