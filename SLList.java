public class SLList {

    private static class IntNode {
    
        public int item;
        public IntNode next;
    
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    
    }   
    /*The first item (if it exists) is at sentinel.next*/ 
    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
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
    public static void main(String[] args) {
        SLList L = new SLList();
        // L.addFirst(10);
        // L.addFirst(5);
        L.addLast(20);
        System.out.println(L.getFirst());
    }
}
