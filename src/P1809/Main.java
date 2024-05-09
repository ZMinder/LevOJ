package P1809;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//路径长度
        int m = scan.nextInt();//积水个数
        int k = scan.nextInt();//跳跃的最远距离
        int[] minJump = new int[n + 1];//为1表示有水坑 记录走到i位置最少需要踩多少水坑
        for (int i = 0; i < m; i++) {
            int pos = scan.nextInt();
            minJump[pos] = 1;
        }
        LinkedList<Integer> preMin = new LinkedList<>();//记录可以到达i的位置最少踩坑数
        preMin.addLast(1);
        for (int i = 2; i < n + 1; i++) {
            minJump[i] += minJump[preMin.getFirst()];//当前位置的最少踩坑数
            if (preMin.getFirst() <= i - k) {//preMin.getFirst()不在 i+1-k ~ i 范围内
                preMin.pollFirst();
            }
            while (!preMin.isEmpty() && minJump[i] < minJump[preMin.peekLast()]) {//将踩坑数少的往前面放
                preMin.pollLast();
            }
            preMin.addLast(i);//将当前位置添加进去
        }
        System.out.println(minJump[n]);
    }
}

