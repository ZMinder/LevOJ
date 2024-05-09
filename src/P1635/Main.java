package P1635;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
    static class MoonCake {
        int eatingTime;
        int expiryTime;
        long happiness;

        public MoonCake(int eatingTime, int expiryTime, long happiness) {
            this.eatingTime = eatingTime;
            this.expiryTime = expiryTime;
            this.happiness = happiness;
        }

        @Override
        public String toString() {
            return "MoonCake{" +
                    "eatingTime=" + eatingTime +
                    ", expiryTime=" + expiryTime +
                    ", happiness=" + happiness +
                    '}';
        }
    }

    public static class MyComparator implements Comparator<MoonCake> {
        @Override
        public int compare(MoonCake o1, MoonCake o2) {
            return o1.expiryTime - o2.expiryTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 月饼个数
        int T = scanner.nextInt(); // 最大时间

        MoonCake[] moonCakes = new MoonCake[n];
        // 读取每个月饼的属性
        for (int i = 0; i < n; i++) {
            int ti = scanner.nextInt(); // 进食时间
            int bi = scanner.nextInt(); // 过期时间
            long hi = scanner.nextInt(); // 快乐值
            moonCakes[i] = new MoonCake(ti, bi, hi);
        }
        long maxHappy = getRealMaxHappy(n, T, moonCakes);
        System.out.println(maxHappy);
    }

    public static long getRealMaxHappy(int n, int T, MoonCake[] moonCakes) {
        Arrays.sort(moonCakes, new MyComparator());//根据过期时间排序
        boolean[] attack = new boolean[n];//记录前i个月饼是否冲突
        long[] happy = new long[n];//记录前i个月饼的快乐累计值
        int[] eatTime = new int[n];//记录前i个月饼的进食累计时间
        attack[0] = true;
        eatTime[0] = moonCakes[0].eatingTime;
        happy[0] = moonCakes[0].happiness;
        int up = -1;
        for (int i = 1; i < n; i++) {
            eatTime[i] = eatTime[i - 1] + moonCakes[i].eatingTime;
            attack[i] = attack[i - 1] ? moonCakes[i].expiryTime >= eatTime[i] : false;
            if (attack[i] == false && attack[i - 1] == true) {
                up = i;
            }
            happy[i] = happy[i - 1] + moonCakes[i].happiness;
        }

        //dp[i][j] 表示i及其之后的月饼在剩余j时间内可以获得的最大快乐值
        long[][] dp = new long[moonCakes.length][T + 1];
        for (int i = 0; i <= T; i++) {//初始化最后一行
            dp[dp.length - 1][i] = i >= moonCakes[moonCakes.length - 1].eatingTime
                    ? moonCakes[moonCakes.length - 1].happiness : 0;
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j <= T; j++) {
                dp[i][j] = dp[i + 1][j];
                if (moonCakes[i].eatingTime <= j) {//当前月饼进食时间小于j
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i + 1][j - moonCakes[i].eatingTime] + moonCakes[i].happiness);
                }
            }
        }
        long maxHappy = 0;
        for (int i = 1; i < (up == -1 ? T : moonCakes[up].expiryTime); i++) {
            long res = getMaxHappy(attack, happy, eatTime, moonCakes, i, dp);
            maxHappy = Math.max(maxHappy, res);
        }
        return maxHappy;
    }

    //t时间离开餐馆 获取的最大快乐值
    public static long getMaxHappy(boolean[] attack, long[] happy, int[] eatTime,
                                   MoonCake[] moonCakes, int t, long[][] dp) {
        int less = binarySearch(moonCakes, t);
        if (less != -1 && !attack[less]) {//存在在t之前过期的月饼 并且过期的月饼进食之间存在冲突
            return -1;
        }
        t -= less == -1 ? 0 : eatTime[less];//剩余的进食时间
        if (t == 0 || less == moonCakes.length - 1) {//没有进食时间了 或者 所有月饼都在t之前过期
            return happy[less];
        }
        less += 1;//剩余时间进食的起始月饼
        long happiness = 0;
        for (int i = 1; i <= t; i++) {
            happiness = Math.max(happiness, dp[less][i]);
        }
        return happiness + (less == 0 ? 0 : happy[less - 1]);
    }

    //找到过期时间小于等于time的最右边的
    public static int binarySearch(MoonCake[] moonCakes, int time) {
        int left = 0;
        int right = moonCakes.length - 1;
        int less = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (moonCakes[mid].expiryTime > time) {
                right = mid - 1;
            } else {
                left = mid + 1;
                less = Math.max(less, mid);
            }
        }
        return less;
    }
}
