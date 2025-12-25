package src.structure_and_algorithm;
//O(N^2) 时间复杂度，O(1)空间复杂度，稳定排序算法
public class InsertionSort {
    
        public static void sort(Comparable[] a) {
            int N = a.length;
            // 从第二个元素开始，因为单个元素默认有序
            for (int i = 1; i < N; i++) {
                // 将 a[i] 插入到 a[i-1], a[i-2], a[i-3]... 之中
                // 只要当前的元素比前一个元素小，就不断交换
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                    exch(a, j, j-1);
                }
            }
        }
    
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    
        private static void exch(Object[] a, int i, int j) {
            Object tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        
        public static void main(String[] args) {
            Integer[] arr = {5, 2, 9, 1, 5, 6};
            sort(arr);
            System.out.println("Sorted array:");
            for (int num : arr) {
                System.out.print(num + " ");
            }
        }
}
