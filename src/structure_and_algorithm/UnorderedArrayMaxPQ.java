package src.structure_and_algorithm;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;      // 元素存储数组
    private int n;         // 元素数量

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    // 插入逻辑：和栈的 push 一样
    public void insert(Key v) {
        if (n == pq.length) resize(2 * pq.length);
        pq[n++] = v;
    }

    // 删除最大元素：类似选择排序的内循环
    public Key delMax() {
        if (isEmpty()) return null;
        
        int max = 0;
        // 步骤1：遍历寻找最大值的索引
        for (int i = 1; i < n; i++) {
            if (less(max, i)) max = i;
        }

        // 步骤2：交换最大元素到末尾
        exch(max, n - 1);

        // 步骤3：删除并返回
        Key res = pq[--n];
        pq[n] = null; // 避免对象游离
        
        if (n > 0 && n == pq.length / 4) resize(pq.length / 2);
        return res;
    }

    // 辅助方法
    private boolean less(int i, int j) { return pq[i].compareTo(pq[j]) < 0; }
    private void exch(int i, int j) { Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }
    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i < n; i++) temp[i] = pq[i];
        pq = temp;
    }
}