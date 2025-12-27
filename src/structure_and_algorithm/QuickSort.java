package src.structure_and_algorithm;

import edu.princeton.cs.algs4.StdRandom;

// 排序算法     最好情况         平均情况           最坏情况       空间复杂度          稳定性
// 选择排序     $O(N^2)$        $O(N^2)$           $O(N^2)$        $O(1)$            不稳定
// 插入排序     $O(N)$          $O(N^2)$           $O(N^2)$        $O(1)$             稳定
// 归并排序     $O(N \log N)$   $O(N \log N)$      $O(N \log N)$   $O(N)$             稳定
// 快速排序     $O(N \log N)$   $O(N \log N)$      $O(N^2)$        $O(\log N)$       不稳定
// 希尔排序     $O(N \log N)$   $O(N^{1.3})$       $O(N^2)$        $O(1)$            不稳定

public class QuickSort {
        public static void sort(Comparable[] a) {
            // 消除对输入的依赖（防止最坏情况发生）
            StdRandom.shuffle(a); 
            sort(a, 0, a.length - 1);
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return; // 基准情况
            int j = partition(a, lo, hi); // 切分
            sort(a, lo, j - 1);  // 排序左半部分
            sort(a, j + 1, hi);  // 排序右半部分
        }

        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1; // 左右扫描指针
            Comparable v = a[lo];   // 基准元素
            
            while (true) {
                // 扫描左边，找到大于 v 的元素
                //如果将条件改为 lessEqual（即扫描时跳过相等的元素），在面对所有元素都相等的数组时，切分点会始终落在最边缘，导致快速排序退化为 $O(N^2)$
                while (less(a[++i], v)) if (i == hi) break;
                // 扫描右边，找到小于 v 的元素
                while (less(v, a[--j])) ;
                
                if (i >= j) break; // 指针相遇则退出
                exch(a, i, j);     // 交换元素
            }
            exch(a, lo, j); // 将基准元素 v 放到正确的位置
            return j;       // 返回基准元素的位置
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
