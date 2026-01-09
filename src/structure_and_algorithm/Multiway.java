package src.structure_and_algorithm;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Multiway {

    // This class should not be instantiated.
    private Multiway() { }

    // merge together the sorted input streams and write the sorted result to standard output
    public static void merge(In[] streams) {
        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
        for (int i = 0; i < n; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());

        // Extract and print min and read next from its stream.
        while (!pq.isEmpty()) {
            StdOut.print(pq.minKey() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        }
        StdOut.println();
    }


    /**
     *  Reads sorted text files specified as command-line arguments;
     *  merges them together into a sorted output; and writes
     *  the results to standard output.
     *  Note: this client does not check that the input files are sorted.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = args.length;
        In[] streams = new In[n];
        for (int i = 0; i < n; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}

//javac -cp ".;lib/algs4.jar" src/structure_and_algorithm/IndexMinPQ.java src/structure_and_algorithm/Multiway.java
//java -cp ".;lib/algs4.jar" src.structure_and_algorithm.Multiway src/structure_and_algorithm/m1.txt src/structure_and_algorithm/m2.txt src/structure_and_algorithm/m3.txt
