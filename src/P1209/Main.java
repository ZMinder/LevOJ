package P1209;

import java.util.Scanner;

/*
小明喜欢滑雪，为了获得速度，滑的区域必须向下倾斜，而且当你滑到坡底，
你不得不再次走上坡或者等待升降机来载你。小明想知道在一个区域中最长底滑坡。
区域由一个二维数组给出。数组的每个数字代表点的高度。下面是一个例子:

Copy to Clipboard
1  2  3  4 5
16 17 18 19 6
15 24 25 20 7
14 23 22 21 8
13 12 11 10 9
一个人可以从某个点滑向上下左右相邻四个点之一，当且仅当高度减小。
在上面的例子中，一条可滑行的滑坡为 24-17-16-1 . 当然 25-24-23-...-3-2-1 更长。
事实上，这是最长的一条.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = scan.nextInt();
        int col = scan.nextInt();
        int[][] arr = new int[row][col];
        readInfo(arr, scan);
        int ret = getMaxLength1(arr);
        System.out.println(ret);
        scan.close();
    }

    public static int getMaxLength1(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int M = arr[0].length;
        int[][] length = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                length[i][j] = -1;//初始化为-1表示长度未确定
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, process(arr, length, i, j));
            }
        }
        return max;
    }

    //获取当前位置的最大滑坡长度
    public static int process(int[][] arr, int[][] length, int i, int j) {
        if (length[i][j] != -1) {
            return length[i][j];
        }
        int max = 0;
        if (i - 1 >= 0 && arr[i][j] > arr[i - 1][j]) {//上
            max = Math.max(max, process(arr, length, i - 1, j));
        }
        if (i + 1 < arr.length && arr[i][j] > arr[i + 1][j]) {//下
            max = Math.max(max, process(arr, length, i + 1, j));
        }
        if (j - 1 >= 0 && arr[i][j] > arr[i][j - 1]) {//左
            max = Math.max(max, process(arr, length, i, j - 1));
        }
        if (j + 1 < arr[0].length && arr[i][j] > arr[i][j + 1]) {//下
            max = Math.max(max, process(arr, length, i, j + 1));
        }
        length[i][j] = max + 1;
        return length[i][j];
    }

    public static void readInfo(int[][] arr, Scanner scan) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
    }
}
