package B4809;

import java.util.Scanner;

/*
众所不知，rly 现在不会玩国际象棋。但是，作为一个 OIer，rly 当然做过八皇后问题。
这里再啰嗦几句，皇后可以攻击到同行同列同对角线，在 n*n 的方格中摆 n 个皇后使其互不攻击到，
求不同的解的数量，这就是经典的 n 皇后问题。现在问题推广到 n 皇后问题，
这个问题对于你而言实在是小菜一叠。但因为上一次 rly 把棋盘弄破了，又拿不出新的，
所以 rly 打算难一点点，问题就是破棋盘上的 n 皇后问题。他想知道……（你们懂的）。

棋子都是相同的。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[][] arr = readInfo(N, scan);
        int ways = getWays(arr);
        System.out.println(ways);
    }

    public static int getWays(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] pos = new int[arr.length];
        int ways = process(arr, pos, 0);
        return ways;
    }

    //在之前摆放位置的前提下，第i个及其之后的摆放方法数
    public static int process(int[][] arr, int[] pos, int i) {
        if (i == arr.length) {
            return 1;
        }
        int ways = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[i][j] == 0) {//在当前棋盘位置可放的基础上
                pos[i] = j;
                if (isValid(pos, i)) {//检查有效性
                    ways += process(arr, pos, i + 1);
                }
            }
        }
        return ways;
    }

    //之前摆放位置有效
    public static boolean isValid(int[] pos, int i) {
        //保证之前摆放的位置与当前行不同列 不同斜线
        for (int j = 0; j < i; j++) {
            if (pos[j] == pos[i] || Math.abs(pos[i] - pos[j]) == Math.abs(i - j)) {
                return false;
            }
        }
        return true;
    }

    public static int[][] readInfo(int N, Scanner scan) {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        return arr;
    }
}
