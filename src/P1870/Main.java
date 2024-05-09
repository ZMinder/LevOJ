package P1870;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//村庄的数量
        int[] hp = new int[n];//每个村庄出口可以杀敌的血量
        for (int i = 0; i < n; i++) {
            hp[i] = scan.nextInt();
        }
        int[] dp = new int[n];//记录每个村庄及其之后的村庄 最大杀敌数
        for (int i = 0; i < n; i++) {
            dp[i] = -1;//初始值-1 表示没计算过
        }
        long res = maxKill2(hp, n);
        System.out.println(res);
    }

    @Test
    public void test() {
        for (int j = 0; j < 10; j++) {
            int n = (int) (Math.random() * 1000000) + 1;
            int[] hp = new int[n];//每个村庄出口可以杀敌的血量
            for (int i = 0; i < n; i++) {
                hp[i] = (int) (Math.random() * 1000) + 1;
            }
            int[] dp = new int[n];//记录每个村庄及其之后的村庄 最大杀敌数
            for (int i = 0; i < n; i++) {
                dp[i] = -1;//初始值-1 表示没计算过
            }
//            int res = maxKill(hp, n, 0, dp);
            long res2 = maxKill2(hp, n);
            System.out.println(res2);
//            if (res != res2) {
//                System.out.println(Arrays.toString(hp));
//            }
//            System.out.println(Arrays.toString(dp));
//            System.out.println(res);
//            System.out.println(maxKill2(hp,n));
        }
    }

    /**
     * 从第i个及其之后的村庄 考虑修建出口 最大的杀敌数量
     *
     * @param hp
     * @param n
     * @param i
     * @return
     */
    public static int maxKill(int[] hp, int n, int i, int[] dp) {
        if (i >= n) {//无村庄可修建出口
            return 0;
        }
        if (dp[i] != -1) {//计算过
            return dp[i];
        }
        //当前村庄不修建出口
        int kill = maxKill(hp, n, i + 1, dp);
        //修建出口 当前杀敌数 + 后续第二个及其之后的杀敌数
        kill = Math.max(kill, hp[i] + maxKill(hp, n, i + 2, dp));
        dp[i] = kill;
        return kill;
    }

    public static long maxKill2(int[] hp, int n) {
        long[] dp = new long[n];//记录每个村庄及其之后的村庄 最大杀敌数
        dp[n - 1] = hp[n - 1];//初始化最后一个
        if (n - 2 >= 0) {
            dp[n - 2] = Math.max(hp[n - 1], hp[n - 2]);//初始化倒数第2个
        }
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], hp[i] + dp[i + 2]);//当前村庄修建和不修建
        }
        return dp[0];
    }
}
