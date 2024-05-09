package P1719;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int getRest(int i) {
        int rest = 1;
        while (i >= rest * 2) {
            rest <<= 1;
        }
        return i - rest + 1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        ArrayList<Integer> list = new ArrayList<>();//记录每个k需要移动的操作数
        for (int i = 0; i < n; i++) {
            int num = scan.nextInt();
            if (num == k) {
                int rest = getRest(i + 1);
                list.add(rest);
            }
        }
        int res = 0;
        for (Integer num : list) {
            if (num == 1 || res >= num) {//如果在2的k次方或者前面删除的次数大于等于该数需要删除的次数 直接删除
                res++;
            } else {
                res = Math.max(res, num);
            }
        }
        System.out.println(res);
    }
}
