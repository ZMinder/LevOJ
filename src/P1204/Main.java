package P1204;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            int n = scan.nextInt();//电池的数量
            int[] hours = new int[n];//电池的使用时间
            for (int i = 0; i < n; i++) {
                hours[i] = scan.nextInt();
            }
            double res = maximizeFlightTime(hours);
            System.out.printf("%.1f\n", res);
        }
    }

    public static double maximizeFlightTime(int[] batteries) {
        int maxTime = 0;
        int totalOtherTime = 0;
        for (int battery : batteries) {
            maxTime = Math.max(maxTime, battery);
            totalOtherTime += battery;
        }
        totalOtherTime -= maxTime;
        if (maxTime > totalOtherTime) {
            // 最持久的电池能玩的时间比其他电池一共能玩的时间还长
            return totalOtherTime;
        } else {
            // 最持久的电池能玩的时间比其他电池一共能玩的时间短（或一样久）
            return (maxTime + totalOtherTime) / 2.0;
        }
    }
}
