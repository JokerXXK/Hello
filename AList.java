public class AList<Item> implements List61B<Item> {
    private Item[] items;
    private int size;

    public AList() {
        items = (Item[]) new Object[100]; // Changed to create an Object array and cast it
        size = 0;
    }

    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity]; // Changed to create an Object array and cast it
        System.arraycopy(items, 0, a, 0, size);
        items = a;       
    }

    @Override
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + 1);      
        }
        items[size] = x;
        size = size + 1;
    } 

    @Override
    public Item getLast() {
        return items[size - 1];
    }

    @Override    
    public int size() {
        return size;
    }

    @Override
    public Item get(int i) {
        return items[i];
    }

    @Override
    public Item removeLast() {
        Item last = getLast();
        items[size - 1] = null;
        size = size - 1;
        return last; 
    }

    @Override
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;
        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }

    @Override
    public void addFirst(Item x) {
        insert(x, 0);
    }
    @Override
    public Item getFirst() {
        return items[0];
    }
}
