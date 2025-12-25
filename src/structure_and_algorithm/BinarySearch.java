package src.structure_and_algorithm;

public class BinarySearch {
    static int rank(String[] sorts, String x, int lo, int hi){
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int res = x.compareTo(sorts[mid]);
            if (res == 0)
                return mid;
            if (res > 0)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }

    static int rank2(String[] sorts, String x, int lo, int hi){
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        int res = x.compareTo(sorts[mid]);
        if (res > 0)
            return rank2(sorts, x, mid + 1, hi);
        else if (res < 0)
            return rank2(sorts, x, lo, mid - 1);
        else
            return mid;

    }

    public static void main(String[] args) {
        String[] sorts = {"apple", "banana", "cherry", "date", "fig", "grape", "kiwi"};
        String x = "cherry";
        int result = rank2(sorts, x, 0, sorts.length - 1);
        if (result != -1) {
            System.out.println(x + " found at index: " + result);
        } else {
            System.out.println(x + " not found in the array.");
        }
    }
}
