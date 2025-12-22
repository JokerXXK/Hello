public class RotatingSLList<Item> extends SLList<Item> {
    
    /** Rotates the list to the right by moving the last element to the front. */
    public void rotateRight() {
        Item x = this.removeLast();
        this.addFirst(x);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rsl = new RotatingSLList<>();
        rsl.addLast(10);
        rsl.addLast(11);
        rsl.addLast(12);
        rsl.addLast(13);

        rsl.rotateRight();
        rsl.print(); // Expected output: 13 10 11 12

    }
}
