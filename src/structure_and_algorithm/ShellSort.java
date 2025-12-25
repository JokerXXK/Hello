package src.structure_and_algorithm;
// 时间复杂度取决于增量序列。平均通常被认为是 $O(N^{1.3})$ 到 $O(N^{1.5})$ 之间。
// 最坏情况使用某些序列时为 $O(N^2)$，使用最优序列（如 Sedgewick 序列）可达 $O(N^{4/3})$。
// 最好情况$O(N \log N)$ 或 $O(N \log^2 N)$。
//Shell原始序列 (n/2, n/4, ..., 1)	          O(n²)	        最坏情况可能达到平方级
//Hibbard增量 (1, 3, 7, ..., 2^k-1)	          O(n^(3/2))	大约 O(n^{1.5})
//Sedgewick增量序列	                          O(n^(4/3))	实践中表现很好
//Knuth增量 (1, 4, 13, ..., (3^k-1)/2)	      O(n^(3/2))	约为 O(n^{1.5})
public class ShellSort {
    
        public static void sort(Comparable[] a) {
            int N = a.length;
            int h = 1;
            
            // 使用 Knuth 序列确定初始增量: 1, 4, 13, 40, 121, ...
            while (h < N/3) h = 3 * h + 1; 
    
            while (h >= 1) {
                // 将数组变为 h-有序
                for (int i = h; i < N; i++) {
                    // 将 a[i] 插入到 a[i-h], a[i-2h], a[i-3h]... 之中
                    for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                        exch(a, j, j-h);
                    }
                }
                h = h / 3; // 缩小增量
            }
        }
    
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    
        private static void exch(Object[] a, int i, int j) {
            Object tmp = a[i]; a[i] = a[j]; a[j] = tmp;
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
