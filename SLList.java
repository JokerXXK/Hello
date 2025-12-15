public class SLList<Blorp> implements List61B<Blorp> {

    private class Node {
    
        public Blorp item;
        public Node next;
    
        public Node(Blorp i, Node n) {
            item = i;
            next = n;
        }
    
    }   
    /*The first item (if it exists) is at sentinel.next*/ 
    private Node sentinel;
    private int size;

    public SLList() {
        sentinel = new Node(null, null);
        size = 0;
    }

    public SLList(Blorp x) {
        sentinel = new Node(null, null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

/*     public void addFirst(Blorp x) {
        sentinel.next = new Node(x, sentinel.next);
        size += 1;
    }
 */

/*     public Blorp getFirst() {
        return sentinel.next.item;
    }
 */
    public void addLast(Blorp x) {
        size += 1;

        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node(x, null);
    }

/***
    public int size() {
        IntNode p = first;
        int size = 0;
        while (p != null) {
            size += 1;
            p = p.next;
        }
        return size;
    }
    **returns the size of the list that starts at IntNode p*

    private static int size1(IntNode p) {
        if (p == null) {
            return 0;
        }
        return 1 + size1(p.next);
    }

    public int size2() {
        return size1(first);
    }
**/
    public int size() {
        return size;
    }

    public Blorp get(int i) {
        Node p = sentinel.next;
        while (i > 0) {
            p = p.next;
            i -= 1;
        }
        return p.item;
    }

    public Blorp removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;

        Node p = sentinel;
        while (p.next.next != null) {
            p = p.next;
        }
        Blorp lastItem = p.next.item;
        p.next = null;
        return lastItem;
    }

    public void insert(Blorp x, int position) {
        size += 1;
        Node p = sentinel;
        for (int i = 0; i < position; i++) {
            p = p.next;
        }
        p.next = new Node(x, p.next);
    }
    
    public void addFirst(Blorp x) {
        insert(x, 0);
    }

    public Blorp getFirst() {
        return get(0);
    }

    public Blorp getLast() {
        return get(size - 1);
    }

    @Override
    public void print() {
        System.out.println("SLList contents:");
        Node p = sentinel.next;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
}
