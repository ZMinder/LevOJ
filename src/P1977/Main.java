package P1977;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static long prim(int n, int[] nums) {
        long res = 0;
        long[] lowest = new long[n];//记录最小生成树到其他节点的最小距离 0表示该节点已经加入
        for (int i = 1; i < n; i++) {//初始化
            lowest[i] = nums[0] + nums[i];
        }
        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;
            int to = -1;
            for (int j = 0; j < n; j++) {
                if (lowest[j] != 0 && lowest[j] < min) {//未加入的 并且距离最小的
                    min = lowest[j];
                    to = j;
                }
            }
            lowest[to] = 0;//标记为已经挑选
            res += min;//累加最小路径和
            for (int j = 1; j < n; j++) {//更新其他节点到最小生成树的距离
                lowest[j] = Math.min(nums[to] + nums[j], lowest[j]);
            }
        }
        return res;
    }

    @Test
    public void test() {
        for (int j = 0; j < 1000; j++) {
            int n = (int) (Math.random() * 10000) + 1;
//        System.out.println(n);
            int[] nums = new int[n];
            long sum = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                nums[i] = (int) (Math.random() * 100000) + 1;
                sum += nums[i];
                min = Math.min(min, nums[i]);
                //            System.out.print(nums[i] + " ");
            }
            long res = prim(n, nums);
            long ans = sum + (n - 2) * min;
            if (res != ans) {
                System.out.println("fuck");
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());//n个会场
            int[] nums = new int[n];//n个会场的人数
            String[] split = br.readLine().split(" ");
            long sum = 0;//所有会场人数累加
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {//读取数据
                nums[i] = Integer.parseInt(split[i]);
                sum += nums[i];
                min = Math.min(min, nums[i]);//最小生成树必然是会场人数最少的会场到其余节点
            }
            System.out.println(sum + (n - 2) * min);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
