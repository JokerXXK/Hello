package src.structure_and_algorithm;

public interface MinPQ<Item> {
    public void add(Item item);
    public Item getSmallest();
    public Item removeSmallest();
    public int size();
}
