package src.structure_and_algorithm;
//O(N)-O(logN)
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // 树的根节点

    private class Node {
        private Key key;           // 键
        private Value val;         // 值
        private Node left, right;  // 指向左右子树的链接
        private int size;          // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * 获取树的节点总数
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * 查找方法：根据键获取值
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key); // 这里的 extends Comparable 保证了 compareTo 的可用性
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     * 插入方法：如果键已存在则更新值，否则创建新节点
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right); // 更新节点计数
        return x;
    }

    /**
     * 获取最小键
     */
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }
    /**
     * 获取最大键
     */
    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * 获取小于等于给定键的最大键
     */
    public Key floor(Key key){
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    /**
     * 获取大于等于给定键的最小键
     */
    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    /**
     * 删除最小键
     */
    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right; // 如果没有左子树，右子树直接顶替上来
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * 删除最大键
     */
    public void deleteMax() {
        if (root != null) root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left; // 如果没有右子树，左子树直接顶替上来
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }    
    /**
     * Hibbard 删除方法：根据键删除节点
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // 找到目标节点，处理三种情况
            if (x.right == null) return x.left; // 只有左子树
            if (x.left  == null) return x.right; // 只有右子树
            
            // 节点有两个子树：用其右子树中的最小节点（后继节点）替换自己
            Node t = x;
            x = min(t.right); 
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    /**
     * 选择排名为 k 的键（0 为最小键）
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else            return x;
    }
    /**
     * 计算键的排名（小于该键的节点数）
     */
    public int rank(Key key) {
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    /**
     * 范围查询：获取所有键的集合，或指定范围内的键的集合(中序遍历：左-根-右)
     */
    public Iterable<Key> keys() {
        return keysInRange(min(), max());
    }
    public Iterable<Key> keysInRange(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keysInRange(root, queue, lo, hi);
        return queue;
    }
    private void keysInRange(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) keysInRange(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.enqueue(x.key);
        if (cmpHi > 0) keysInRange(x.right, queue, lo, hi);
    }
}
