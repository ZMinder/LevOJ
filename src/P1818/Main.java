package P1818;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        long[] weights = new long[n];//饼干提供的饱腹感
        weights[0] = scan.nextLong();
        for (int i = 1; i < n; i++) {
            weights[i] = scan.nextLong();
            weights[i] += weights[i - 1];
        }
        long[] uppers = new long[q];//饱腹感上限
        for (int i = 0; i < q; i++) {
            uppers[i] = scan.nextInt();
        }
        getCookies(weights, uppers);
    }

    @Test
    public void test() {
        int maxN = 200000; // 饼干的最大数量
        int maxWeight = 1000; // 饼干的最大饱腹感

        Random random = new Random();

        int n = random.nextInt(maxN) + 1; // 生成随机的饼干数量
        int q = random.nextInt(maxN) + 1; // 生成随机的询问次数
        System.out.println(n + " " + q);
        // 生成饼干的饱腹感
        long totalWeight = 0;
        long[] weights = new long[n];
        for (int i = 0; i < n; i++) {
            weights[i] = random.nextInt(maxWeight) + 1;
            totalWeight += weights[i];
        }

        // 生成每次询问的饱腹感上限
        long[] uppers = new long[q];
        for (int i = 0; i < q; i++) {
            uppers[i] = random.nextInt((int) totalWeight) + 1;
        }

        // 调用计算函数
        getCookies(weights, uppers);
    }

    public static void getCookies(long[] weights, long[] uppers) {
        for (int i = 0; i < uppers.length; i++) {
            int ret = binarySearch(weights, uppers[i]);
            System.out.print((ret + 1) + " ");
        }
    }

    public static int binarySearch(long[] arr, long target) {
        int left = 0;
        int right = arr.length - 1;
        int more = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                more = Math.min(more, mid);
            }
        }
        return more;
    }
}
