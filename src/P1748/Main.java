package P1748;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/*
求和问题可以被看做是以下的公式，给定 A，B，C，D 四个列表，计算有多少四元组满足
(a, b, c, d) ∈ A × B × C × D 且 a + b + c + d = 0。我们推测所有的列表都有 n 个数字。
注：不同的四元组是指元素位置不一样的四元组
数据范围n<=2e3
 */
public class Main {
    //暴力解法超时，采用两两组合的方式，将两个数组所有可能相加的方式累加，并进行排序，之后在其中进行二分查找
    //注意四元组只要求下标不一样，所有值相同均可
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scan.nextInt();
            int ret = readInfo(scan, n);
            System.out.println(ret);
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        int ret = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                int l = mid;
                while (l >= 0 && arr[l--] == target) {
                    ret++;
                }
                while (mid < arr.length - 1 && arr[++mid] == target) {
                    ret++;
                }
                break;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ret;
    }


    public static int readInfo(Scanner scan, int n) {
        int[][] arr = new int[4][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j][i] = scan.nextInt();
            }
        }
        int[] newArr = new int[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newArr[index++] = arr[0][i] + arr[1][j];
            }
        }
        Arrays.sort(newArr);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = arr[2][i] + arr[3][j];
                count += binarySearch(newArr, -value);
            }
        }
        return count;
    }
}
