package src.structure_and_algorithm;

public class OrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    private Node first; 
    private int n;

    private class Node {
        Key key;
        Node next;
    }

    public void insert(Key v) {
        Node newNode = new Node();
        newNode.key = v;

        // 场景 A: 链表为空或新元素比头节点大（插入头部）
        if (first == null || v.compareTo(first.key) > 0) {
            newNode.next = first;
            first = newNode;
        } else {
            // 场景 B: 寻找中间或末尾的插入位置
            Node curr = first;
            while (curr.next != null && v.compareTo(curr.next.key) < 0) {
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
        }
        n++;
    }

    public Key delMax() {
        if (first == null) return null;
        Key val = first.key;
        first = first.next; // 直接弹出首元素
        n--;
        return val;
    }
}