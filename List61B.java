public interface List61B<Item> {
 
    public void addLast(Item x);

    public Item getLast() ;

    public int size() ;

    public Item get(int i);

    public Item removeLast();

    public void insert(Item x, int position);

    public void addFirst(Item x);

    public Item getFirst();

    default public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
