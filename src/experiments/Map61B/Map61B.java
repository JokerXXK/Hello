package src.experiments.Map61B;

import java.util.List;

public interface Map61B<K,V> {
    boolean containsKey(K key);
    V get(K key);
    void put(K key, V value);
    int size();
    List<K> keys();
}
