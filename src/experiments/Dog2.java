package src.experiments;
import java.util.Comparator;
public class Dog2 implements Comparable<Dog2> {
    private String name;
    private int size;

    public Dog2(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    @Override
    public int compareTo(Dog2 o) {
        return this.size - o.size;
    }

    private static class NameComparator implements Comparator<Dog2>{
        public int compare(Dog2 d1, Dog2 d2){
            return d1.name.compareTo(d2.name);
        }
    }

    public static Comparator<Dog2> getNameComparator(){
        return new NameComparator();
    }
    
}
