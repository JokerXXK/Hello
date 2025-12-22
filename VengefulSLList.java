public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> lostItems;

    public VengefulSLList() {
        lostItems = new SLList<>();
    }

    public VengefulSLList(Item x) {
        super(x);
        lostItems = new SLList<>();
    }

    public void prinLostItems() {
        lostItems.print();
    }

    @Override
    public Item removeLast(){
        Item lastItem = super.removeLast();
        lostItems.addLast(lastItem);
        return lastItem;
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> vsl = new VengefulSLList<>();
        vsl.addLast(1);
        vsl.addLast(5);
        vsl.addLast(10);
        vsl.addLast(13);
        
        vsl.removeLast();
        vsl.removeLast();

        System.out.println("The fallen are: ");
        vsl.prinLostItems();
    }
    
}
