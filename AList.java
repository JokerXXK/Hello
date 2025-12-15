public class AList<Item> {
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


    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + 1);      
        }
        items[size] = x;
        size = size + 1;
    } 

    public Item getLast() {
        return items[size - 1];
    }

    public int size() {
        return size;
    }

    public Item get(int i) {
        return items[i];
    }

    public Item removeLast() {
        Item last = getLast();
        items[size - 1] = null;
        size = size - 1;
        return last; 
    }
}
