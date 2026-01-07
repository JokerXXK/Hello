package src.structure_and_algorithm;
//O(logN) 的插入和删除最大元素操作
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;      // 元素存储数组
    private int N = 0;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public void insert(Key v) {
        if (N == pq.length - 1) {
            Key[] temp = (Key[]) new Comparable[pq.length * 2];
            for (int i = 1; i <= N; i++) {
                temp[i] = pq[i];
            }
            pq = temp;           
        }
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        if (isEmpty()) return null;

        Key max = pq[1];
        exch(1, N--);       
        sink(1);
        pq[N + 1] = null; // 避免对象游离
        if (N > 0 && N == (pq.length - 1) / 4) {
            Key[] temp = (Key[]) new Comparable[pq.length / 2];
            for (int i = 1; i <= N; i++) {
                temp[i] = pq[i];
            }
            pq = temp;
            
        }
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    
}
