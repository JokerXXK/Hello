package Map61B;

public class IterationDemo {
    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("hello", 5);
        map.put("syrups", 10);
        map.put("kingdom", 10);

        // ArrayMap<String, Integer>.keyIterator mapi = map.new keyIterator(); // BEGIN: fix raw type
        // while (mapi.hasNext()) {
        //     System.out.println(mapi.next());
        // }

        for (String key : map) {
            System.out.println(key);
        }
        
        
    }
}
