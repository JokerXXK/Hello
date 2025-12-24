package Map61B;

public class ExceptionDemo {
    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("hello", 5);
        System.out.println(map.get("yolp"));
    }
}
