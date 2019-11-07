package TwoSATSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

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
                    } else {
                        String[] clausesInStr = line.split(" ");

                        int[] theEdge = new int[2];

                        for (int i = 0; i < clausesInStr.length - 1; i++) {
                            // add into the literalArrayList
                            // never add in the zero
                            int temp0neLit = Integer.parseInt(clausesInStr[i]);
                            theEdge[i] = temp0neLit;
                        }

                        // add the two edges
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
}