public class SLList2<LochNess>{

    private class StuffNode {
    
        public LochNess item;
        public StuffNode next;
    
        public StuffNode(LochNess i, StuffNode n) {
            item = i;
            next = n;
        }
    
    }   
    /*The first item (if it exists) is at sentinel.next*/ 
    private StuffNode first;
    private int size;

    public SLList2(LochNess x) {
        first = new StuffNode(x, null);
        size = 1;
    }

    public void addFirst(LochNess x) {
        first = new StuffNode(x, first);
        size += 1;
    }

    public LochNess getFirst() {
        return first.item;
    }

    public void addLast(LochNess x) {
        size += 1;

        StuffNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new StuffNode(x, null);
    }

/***
    public int size() {
        StuffNode p = first;
        int size = 0;
        while (p != null) {
            size += 1;
            p = p.next;
        }
        return size;
    }
    **returns the size of the list that starts at StuffNode p*

    private static int size1(StuffNode p) {
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
    

}
