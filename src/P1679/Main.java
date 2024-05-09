package P1679;

import java.util.LinkedList;
import java.util.Scanner;

/*
夏天的时候，ThinkSpirit 实验室去海边玩了。蒙起眼睛的Crush 举起了木棒，他要在大家的帮助下打西瓜。
沙滩可以被描述成一个N×M 的网格，其中某些格子为不可移动的障碍物。Crush 总是在格子上，
始终在格子上移动，且不会走出边界。西瓜也处于一个格子上，当Crush 走到西瓜所在格子，他就会举起木棒，重重挥下 —— 啪！
好心的其它成员当然会说出提示，来帮助Crush，每个提示都会让Crush走向相邻的一个可走的格子。
问最少需要多少条提示，才能让Crush 打到西瓜？
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[][] arr = new int[N][M];
        int[][] pos = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = scan.nextInt();
                pos[i][j] = Integer.MAX_VALUE;
            }
        }
        int curRow = scan.nextInt() - 1;
        int curCol = scan.nextInt() - 1;
        int targetRow = scan.nextInt() - 1;
        int targetCol = scan.nextInt() - 1;
        process(arr, pos, curRow, curCol);
        System.out.println(
                pos[targetRow][targetCol] == Integer.MAX_VALUE ? -1 : pos[targetRow][targetCol]);
    }

    //从当前位置到达目标位置的最短距离
    public static void process(int[][] arr, int[][] pos, int curRow, int curCol) {
        LinkedList<int[]> queue = new LinkedList<>();
        int[] cur = new int[]{curRow, curCol};
        queue.add(cur);
        pos[curRow][curCol] = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            int count = pos[cur[0]][cur[1]];
            curRow = cur[0];
            curCol = cur[1];
            if (curRow - 1 >= 0 && arr[curRow - 1][curCol] == 0 && pos[curRow - 1][curCol] == Integer.MAX_VALUE) {
                pos[curRow - 1][curCol] = count + 1;
                int[] temp = new int[]{curRow - 1, curCol};
                queue.add(temp);
            }
            if (curRow + 1 < arr.length && arr[curRow + 1][curCol] == 0 && pos[curRow + 1][curCol] == Integer.MAX_VALUE) {
                pos[curRow + 1][curCol] = count + 1;
                int[] temp = new int[]{curRow + 1, curCol};
                queue.add(temp);
            }
            if (curCol - 1 >= 0 && arr[curRow][curCol - 1] == 0 && pos[curRow][curCol - 1] == Integer.MAX_VALUE) {
                pos[curRow][curCol - 1] = count + 1;
                int[] temp = new int[]{curRow, curCol - 1};
                queue.add(temp);
            }
            if (curCol + 1 < arr[0].length && arr[curRow][curCol + 1] == 0 && pos[curRow][curCol + 1] == Integer.MAX_VALUE) {
                pos[curRow][curCol + 1] = count + 1;
                int[] temp = new int[]{curRow, curCol + 1};
                queue.add(temp);
            }
        }
    }
}
