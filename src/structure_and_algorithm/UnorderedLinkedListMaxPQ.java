package src.structure_and_algorithm;

public class UnorderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private Node first; // 链表头
    private int n;

    private class Node {
        Key key;
        Node next;
    }

    public void insert(Key v) {
        Node oldFirst = first;
        first = new Node();
        first.key = v;
        first.next = oldFirst;
        n++;
    }

    public Key delMax() {
        if (first == null) return null;
        
        // 1. 寻找最大值及其前驱节点
        Node maxNode = first;
        Node maxPrev = null;
        Node curr = first;
        Node prev = null;

        while (curr != null) {
            if (prev != null && curr.key.compareTo(maxNode.key) > 0) {
                maxNode = curr;
                maxPrev = prev;
            }
            prev = curr;
            curr = curr.next;
        }

        // 2. 从链表中删除最大节点
        if (maxPrev == null) first = first.next; // 最大值在头部
        else maxPrev.next = maxNode.next;
        
        n--;
        return maxNode.key;
    }
}
