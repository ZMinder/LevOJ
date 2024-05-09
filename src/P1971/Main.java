package P1971;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int X = scan.nextInt();
        double logX = Math.log10(X);
        int N = scan.nextInt();
        double[] logValues = new double[500001]; // 预先计算一些对数值，用于加速
        double two = 0;
        for (int i = 2; i <= 500000; i += 2) {
            two += Math.log10(i);
            logValues[i] = two;
        }
        for (int i = 0; i < N; i++) {
            int monster = scan.nextInt();
            int res = method1(logX, monster,logValues);
            System.out.println(res);
        }
    }

//    @Test
//    public void test() {
//        int X = (int) (Math.random() * 1000000) + 1;
//        double logX = Math.log10(X);
//        int n = 10000;
//        double[] logValues = new double[5000001]; // 预先计算一些对数值，用于加速
//        double two = 0;
//        for (int i = 2; i <= 5000000; i += 2) {
//            two += Math.log10(i);
//            logValues[i] = two;
//        }
//        System.out.println("-------------------");
//        for (int i = 0; i < n; i++) {
//            int monster = (int) (Math.random() * 100000) + 1;
//            int res1 = method(logX, monster);
//            int res2 = method1(logX, monster, logValues);
//            if (res1 != res2) {
//                System.out.println("X:" + X);
//                System.out.println("monster:" + monster);
//                System.out.println("res1:" + res1);
//                System.out.println("res2:" + res2);
//            }
//        }
//    }

    public static int method1(double logX, int monster, double[] logValues) {
        double ret = logX;
        int left = 0;
        int right = monster * 5;
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            ret = (mid + 1) * logX + ((mid % 2 == 0) ? logValues[mid] : logValues[mid - 1]);
            if (ret < monster) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = Math.min(res, mid);
            }
        }
        return res;
    }

    public static int method(double logX, int monster) {
        double res = 0;
        double two = 0;
        int i;
        for (i = 1; res < monster; i++) {
            if (i % 2 == 0) {
                two += Math.log10(i);
            }
            res = (i + 1) * logX + two;
        }
        return i - 1;
    }
}