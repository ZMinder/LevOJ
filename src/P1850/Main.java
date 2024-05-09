package P1850;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] heights = new int[n];//记录身高
        for (int i = 0; i < n; i++) {
            heights[i] = scan.nextInt();
        }
        int res = getMinLeave(n, heights);
        System.out.println(res);
    }

    //找到每个位置左侧和右侧最长递增子序列   反向考虑保留最多学生
    public static int getMinLeave(int n, int[] heights) {
        int[] left = new int[n];//以i作为右边界 左边最长递增子序列长度
        int[] right = new int[n];//以i作为左边界 右边最长递增子序列长度
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (heights[j] < heights[i]) {//左边比我矮的
                    //考虑以其作为左边最长递增子序列的右侧位置
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (heights[j] < heights[i]) {//右边比我矮的
                    //考虑以其作为右边最长递增子序列的左侧位置
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, left[i] + right[i]);
        }
        return n - max - 1;
    }


    //找出每一个位置左边最少去掉的人数和右边最少去掉的人数
    public static int minLeave(int n, int[] heights) {
        int[] left = new int[n];//记录i位置左边想要满足升序规则需要去掉的人数
        int[] right = new int[n];//记录i位置右边想要满足降序规则需要去掉的人数
        for (int i = 1; i < n; i++) {
            int less = 0;
            int minLess = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (less == 0 && heights[j] >= heights[i]) {//统计左边第一个比我矮的之前比我高的
                    left[i]++;
                } else if (heights[j] < heights[i]) {//左边遇到比我矮的 以他作为左边第一个
                    minLess = Math.min(minLess, i - j - 1 + left[j]);
                    less++;
                }
            }
            if (less > 0) {
                left[i] = minLess;
            }
        }
        for (int i = n - 2; i >= 0; i--) {//统计右边不满足降序规则的人数
            int less = 0;
            int minLess = Integer.MAX_VALUE;
            for (int j = i + 1; j < n; j++) {
                if (less == 0 && heights[j] >= heights[i]) {//右边遇到第一个比我矮的之前比我高的
                    right[i]++;
                } else if (heights[j] < heights[i]) {//右边遇到比我矮的 一塔作为右边第一个
                    minLess = Math.min(minLess, j - i - 1 + right[j]);
                    less++;
                }
            }
            if (less > 0) {
                right[i] = minLess;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {//统计每个位置不满足合唱队形的人数
            min = Math.min(left[i] + right[i], min);
        }
        return min;
    }
}
