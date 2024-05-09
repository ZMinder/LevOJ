package P1855;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//接水人数
        int m = scan.nextInt();//水龙头个数
        int[] water = new int[n];//每名同学接水时间
        for (int i = 0; i < n; i++) {
            water[i] = scan.nextInt();
        }
        int res = getSumTime(n, m, water);
        System.out.println(res);
    }

    public static int getSumTime(int n, int m, int[] water) {
        //使用优先级队列 存储每个水龙头当前同学接水截止时间
        PriorityQueue<Integer> pos = new PriorityQueue<>();
        int i = 0;//遍历下标
        while (pos.size() < m) {
            pos.offer(water[i++]);//初始化每个水龙头为前m个同学接水
        }
        while (i < n) {
            int least = pos.poll();//弹出第一个接完水的同学
            pos.offer(water[i++] + least);//立刻下一个同学接上
            while (i < n && pos.peek() == least) {//当前时刻还有其他水龙头接完水 并且还有人能接上
                pos.poll();//弹出
                pos.offer(water[i++] + least);//下一个同学接上
            }
        }
        //所有水龙头最后停止时间
        int max = 0;
        while (!pos.isEmpty()) {
            max = Math.max(max, pos.poll());//依次弹出 取最后停止的一个
        }
        return max;
    }
}
