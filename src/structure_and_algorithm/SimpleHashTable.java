package src.structure_and_algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleHashTable<K, V> {
    private class Node {
        K key;
        V value;
        Node next;

        Node(K k, V v, Node next) {
            this.key = k;
            this.value = v;
            this.next = next;
        }
    }

    private Node[] buckets;
    private int size; // 当前键值对总数
    private int M;    // 桶的数量

    public SimpleHashTable() {
        this(16); // 默认初始容量
    }

    public SimpleHashTable(int capacity) {
        this.M = capacity;
        // 核心修正：强制转换
        this.buckets = (Node[]) new SimpleHashTable.Node[M];
    }

    // 哈希函数：位运算取正后取模
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(key) != null;
    }

    // 1. 查找方法
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int i = hash(key);
        for (Node x = buckets[i]; x != null; x = x.next) {
            if (x.key.equals(key)) return x.value;
        }
        return null;
    }

    // 2. 插入方法（含自动扩容）
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        
        // 如果值为空，通常在 Java 实践中等同于删除
        if (value == null) {
            delete(key);
            return;
        }

        // 动态扩容：当装载因子 > 8 时（平均每个桶挂8个节点）
        if (size >= 8 * M) resize(2 * M);

        int i = hash(key);
        for (Node x = buckets[i]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = value; // 键存在，更新值
                return;
            }
        }
        
        // 键不存在，头插法插入新节点
        buckets[i] = new Node(key, value, buckets[i]);
        size++;
    }

    // 3. 删除方法
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int i = hash(key);
        buckets[i] = delete(buckets[i], key);
        
        // 缩容：当装载因子过小时释放空间
        if (M > 16 && size <= 2 * M) resize(M / 2);
    }

    // 删除链表节点的递归写法
    private Node delete(Node x, K key) {
        if (x == null) return null;
        if (x.key.equals(key)) {
            size--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    // 4. 扩容方法：核心是重新计算所有键的哈希值
    private void resize(int chains) {
        SimpleHashTable<K, V> temp = new SimpleHashTable<>(chains);
        for (int i = 0; i < M; i++) {
            for (Node x = buckets[i]; x != null; x = x.next) {
                temp.put(x.key, x.value);
            }
        }
        this.M = temp.M;
        this.size = temp.size;
        this.buckets = temp.buckets;
    }

    // 5. 获取所有键（用于遍历）
    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (Node x = buckets[i]; x != null; x = x.next) {
                queue.add(x.key);
            }
        }
        return queue;
    }
}
