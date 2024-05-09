package P1869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());//n个士兵
            int[] height = new int[n];//n个士兵的身高
            int[][] dp = new int[n][2];//dp[i][0/1]表示以第i个士兵结尾且变化方向为负/正的最长序列长度
            String[] split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {//读入数据 并且初始化dp
                height[i] = Integer.parseInt(split[i]);
                dp[i][0] = dp[i][1] = 1;//不管哪个位置 初始长度均为1
            }
            int max = 0;//记录全局最长序列长度
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (height[j] > height[i]) {//填dp[i][0] 向前找比我高的 并且dp[j][1]最大的
                        dp[i][0] = Math.max(dp[j][1] + 1, dp[i][0]);
                    }
                    if (height[j] < height[i]) {//填dp[i][1] 向前找比我矮的 并且dp[j][0]最大的
                        dp[i][1] = Math.max(dp[j][0] + 1, dp[i][1]);
                    }
                    max = Math.max(max, Math.max(dp[i][0], dp[i][1]));//统计全局最长序列长度
                }
            }
            System.out.println(n - max);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
