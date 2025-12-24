package src.experiments.Map61B;

import org.junit.Test;
import static org.junit.Assert.*;
public class MapHelper {
    public static <K,V> V get(Map61B<K, V> map, K key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return null;
        }

    }
    public static <K extends Comparable<K>,V> K maxKey(Map61B<K, V> map) {
        if (map.size() == 0) {
            return null;
        }
        K maxKey = null;
        for (K key : map.keys()) {
            if (maxKey == null ||key.compareTo(maxKey) > 0) {
                maxKey = key;
            }
        }
        return maxKey;
    }

    @Test
    public void testGet() {
        Map61B<String, Integer> map = new ArrayMap<>();
        map.put("horse", 3);
        map.put("fish", 9);
        map.put("house", 10);
        Integer actual = MapHelper.get(map, "fish");
        Integer expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void testMaxKey() {
        Map61B<String, Integer> map = new ArrayMap<>();
        map.put("horse", 3);
        map.put("fish", 9);
        map.put("house", 10);
        String actual = MapHelper.maxKey(map);
        String expected = "house";
        assertEquals(expected, actual);
    }
}
