package Map61B;
import java.util.List;
import java.util.ArrayList;
//import org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayMap<K,V> implements Map61B<K,V> {
    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(K key) {
        return keyIndex(key) != -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index != -1) {
            values[index] = value;
        } else {
            keys[size] = key;
            values[size] = value;
            size++;
        }
    }

    public V get(K key) {
        int index = keyIndex(key); 
        if (index != -1) {
            return values[index];
        }
        return null;
    }
    public int size() {
        return size;
    }
    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }

    @Test
    public void testPutAndGet() {
        ArrayMap<Integer, Integer> map = new ArrayMap<>();
        map.put(2, 5);
        int expected = 5;
        assertEquals((Integer)expected, map.get(2));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("horse", 3);
        map.put("fish", 9);
        map.put("house", 10);
        
    }
}
