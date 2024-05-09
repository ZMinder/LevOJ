package P1305;


import java.util.ArrayList;
import java.util.Scanner;

/*
有一个整数 n，把从 1 到 n 的数字无重复的排列成环，且使每相邻两个数（包括首尾）的和都为素数，
称为素数环。为了简便起见，我们规定每个素数环都从 1 开始。
 */
public class Main {
    public static void main(String[] args) {
        int count = 1;
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {//多组输入
            int num = scan.nextInt();
            System.out.println("Case " + count++ + ":");
            ArrayList<String> list = new ArrayList<>();
            boolean[] isVisited = new boolean[num];
            isVisited[0] = true;
            //获取素数环的所有情况并存储在list中
            getWays(list, isVisited, 1, "1");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println();
        }
        scan.close();
    }


    public static void getWays(ArrayList<String> list, boolean[] isVisited, int count, String str) {
        if (count == isVisited.length) {//填完了
            list.add(str);
            return;
        }
        //获取上一个数
        int pre = 0;
        for (int i = str.length() - 1; i >= 0 && str.charAt(i) != ' '; i--) {
            pre += (str.charAt(i) - '0') * Math.pow(10, str.length() - i - 1);
        }
        for (int i = 2; i <= isVisited.length; i++) {
            if (!isVisited[i - 1]) {
                if (count == isVisited.length - 1) {//该填最后一个空 最后一个空还需要与第一个数判断是否满足条件
                    if (!isValid(i + 1)) {
                        return;
                    }
                }
                //当前空所填数是否与前一个数累加和是素数
                if (isValid(i + pre)) {
                    isVisited[i - 1] = true;
                    getWays(list, isVisited, count + 1, str + " " + i);
                    isVisited[i - 1] = false;
                }
            }
        }
    }

    //检查num是否为素数
    public static boolean isValid(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
