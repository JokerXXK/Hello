package src.experiments;
import java.util.Comparator;

public class Dog2Laucher {
    public static void main(String[] args) {
        Dog2 d1 = new Dog2("Elyse", 3);
        Dog2 d2 = new Dog2("Sture", 9);
        Dog2 d3 = new Dog2("Artemesios", 15);
        Dog2[] dogs = {
            d1, d2, d3
        };
        Dog2 maxDog = (Dog2) Maximizer.max(dogs);
        maxDog.bark();

        Comparator<Dog2> nc = Dog2.getNameComparator();
        if (nc.compare(d1, d3)>0) {
            d1.bark();
        }else{
            d3.bark();
        }
    }
}
