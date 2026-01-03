package src.structure_and_algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node first;    // 指向最早添加的节点（出队端）
    private Node last;     // 指向最晚添加的节点（入队端）
    private int n;         // 队列中的元素数量

    // 辅助节点类
    private class Node {
        private Item item;
        private Node next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return n; }

    /**
     * 入队：在队尾添加元素 (enqueue)
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last; // 如果之前是空的，新节点既是头也是尾
        else           oldLast.next = last;
        n++;
    }

    /**
     * 出队：在队头移除元素 (dequeue)
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null; // 避免对象游离
        return item;
    }

    /**
     * 实现 Iterable 接口，允许 for-each 遍历
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}