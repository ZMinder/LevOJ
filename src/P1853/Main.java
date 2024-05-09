package P1853;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int magic = scan.nextInt();//初始魔法值
        int dist = scan.nextInt();//起始位置与出口的距离
        int sink = scan.nextInt();//岛屿沉没时间
        solve(magic, dist, sink);
        scan.close();
    }


    public static void solve(int magic, int dist, int sink) {
        int dis1 = 0;//存放跑步的距离
        int dis2 = 0;//存放闪烁的距离
        for (int i = 1; i <= sink; i++) {
            dis1 += 17;
            if (magic >= 10) {//能施法则施法
                dis2 += 60;
                magic -= 10;
            } else {
                magic += 4;//不能施法则回蓝
            }
            if (dis2 > dis1) {//施法比跑步快 就使用施法覆盖跑步
                dis1 = dis2;
            }
            if (dis1 >= dist) {//如果到达出口 则离开
                System.out.println("Yes");
                System.out.println(i);
                return;
            }
        }
        //没走出去
        System.out.println("No");
        System.out.println(dis1);
    }
}
