package P1779;

import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int target = scan.nextInt();
//        int[] start = new int[N];
//        int[] dist = new int[N];
        //记录每个跳板的起始距离和可跳的最远距离
        HashMap<Integer, Integer> jump = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int start = scan.nextInt();
            int dist = scan.nextInt();
            jump.put(start, start + dist);
        }
//        for (int i = 0; i < N; i++) {
//            start[i] = scan.nextInt();
//            dist[i] = scan.nextInt();
//        }
//        int res = minSteps(start, dist, target);
        int res = minSteps(target, jump);
        System.out.println(res);
        scan.close();
    }

    public static int minSteps(int[] start, int[] dist, int target) {
        int[] dp = new int[start.length];//记录每个跳板的最远距离
        int minSteps = start[0];//考虑到达每一个跳板的最少走的距离
        dp[0] = start[0] + dist[0];
        for (int i = 0; i < start.length - 1; i++) {
            if (dp[i] < start[i + 1]) {//当前跳板的最远距离到不了下一个跳板
                minSteps += start[i + 1] - dp[i];
                dp[i + 1] = start[i + 1] + dist[i + 1];
            } else {
                //当前跳板的最远距离可以到达下一个跳板
                //下一个跳板的最远距离=(下一个跳板的跳跃距离与当前跳板的最远距离比较)
                dp[i + 1] = Math.max(dp[i], start[i + 1] + dist[i + 1]);
            }
        }
        minSteps += Math.max(0, target - dp[start.length - 1]);
        return minSteps;
    }


//    @Test
//    public void test() {
//        for (int j = 0; j < 100; j++) {
//            int N = (int) (Math.random() * 100000) + 1;
//            int target = (int) (Math.random() * 1000000000) + 1;
//            HashMap<Integer, Integer> jump = new HashMap<>();
//            for (int i = 0; i < N; i++) {
//                int start = (int) (Math.random() * target) + 1;
//                int dist = (int) (Math.random() * 1000000000) + 5;
//                while (start + dist > target) {
//                    start = (int) (Math.random() * target);
//                    dist = (int) (Math.random() * 1000000000) + 1;
//                }
//                jump.put(start, start + dist);
//            }
////            System.out.println("----------" + j + "-------------");
////            System.out.println("N:" + N);
////            System.out.println("target:" + target);
////            System.out.println(jump);
//            int res = minSteps(target, jump);
//            System.out.println(j + ": " + res);
//        }
//    }

    //minSteps数组开过大 堆内存溢出
    public static int minSteps(int target, HashMap<Integer, Integer> jump) {
        int[] minSteps = new int[target + 1];
        int pre = 0;//达到最远距离时跳板的位置
        int preEnd = jump.getOrDefault(0, 0);//可以凭借跳板达到的最远距离
        for (int i = 1; i <= target; i++) {
            if (i <= preEnd) {//i在之前能跳的最远距离内
                minSteps[i] = minSteps[pre];
                //最远距离是否需要更新
                //如果i处没有跳板 preEnd不需要更新
                //如果i处有跳板 preEnd小于i跳的最远距离才更新
                if (jump.containsKey(i) && jump.get(i) > preEnd) {
                    preEnd = jump.get(i);
                    pre = i;
                }
            } else {
                minSteps[i] = minSteps[i - 1] + 1;//只能从前一个走过来
                //此时preEnd必须更新
                pre = i;//pre肯定是i 从i继续跳到最远
                preEnd = jump.containsKey(i) ? jump.get(i) : i;
            }
        }
        return minSteps[target];
    }
}
