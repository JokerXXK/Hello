package src.structure_and_algorithm;
public interface DisjointSets {

    void connect(int p, int q);

    boolean isConnected(int p, int q);
    
} 