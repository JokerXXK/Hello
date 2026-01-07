package src.structure_and_algorithm;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;      // 元素存储数组
    private int n;         // 元素数量

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    // 插入逻辑：和栈的 push 一样
    public void insert(Key v) {
        if (n == pq.length) resize(2 * pq.length);
    
        int i = n - 1;
        // 循环条件：只有当前面的元素比 v 大时，才继续左移
        // 如果 v >= pq[i]，循环会自动终止（跳出）
        while (i >= 0 && v.compareTo(pq[i]) < 0) { 
            pq[i+1] = pq[i]; 
            i--;
        }    
        // 跳出循环后，i+1 就是新元素该呆的位置
        pq[i+1] = v;
        n++;
    }

    // 删除最大元素：类似选择排序的内循环
    public Key delMax() {
        if (isEmpty()) return null;
        
        Key res = pq[--n];
        pq[n] = null; // 避免对象游离
        
        if (n > 0 && n == pq.length / 4) resize(pq.length / 2);
        return res;
    }

    // 辅助方法
    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i < n; i++) temp[i] = pq[i];
        pq = temp;
    }
}
