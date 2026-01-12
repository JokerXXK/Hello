package src.structure_and_algorithm;

import java.util.Scanner;

public class Heap2 {

    // This class should not be instantiated.
    private Heap2() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /***************************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array to standard output
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串（空格分隔，按回车结束输入）：");
        
        // 简单的读取示例
        String input = scanner.nextLine();
        String[] a = input.split("\\s+"); // 按空格拆分
        
        Heap.sort(a);
        
        System.out.println("排序结果：");
        for (String s : a) {
            System.out.print(s + " ");
        }
        scanner.close();
    }
}