package P1821;

import java.util.*;

/*
给定一棵二叉树的结点数，已知各结点之间的父子关系，求该二叉树的深度。根结点的深度记为 0。
注意：本题不是完全二叉树。
输入
第一行输入一个数字(1<=n<=50000) ，代表总结点数量，下标为 1 的结点为根结点；
接下来n−1 行，每行两个数字u、v(1<=u,v<=n) ，代表u 是v 的父结点。
输出
输出仅一行，回答该二叉树的深度。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, ArrayList<Integer>> map = readInfo(scan);
        int depth = getDepth(map, 1);
        System.out.println(depth);
    }

    //获取二叉树深度
    public static int getDepth(HashMap<Integer, ArrayList<Integer>> map, int head) {
        if (!map.containsKey(head)) {//叶子节点不包含在map中
            return 0;
        }
        ArrayList<Integer> list = map.get(head);
        int maxHeight = Integer.MIN_VALUE;
        for (int cur : list) {
            int curHeight = getDepth(map, cur);
            maxHeight = Math.max(maxHeight, curHeight);
        }
        return maxHeight + 1;
    }

    public static HashMap<Integer, ArrayList<Integer>> readInfo(Scanner scan) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int N = scan.nextInt();
        for (int i = 1; i < N; i++) {
            int parent = scan.nextInt();
            int son = scan.nextInt();
            ArrayList<Integer> list = null;
            if (!map.containsKey(parent)) {//检查是否需要new一个list
                list = new ArrayList<>();
            } else {
                list = map.get(parent);
            }
            list.add(son);
            map.put(parent, list);
        }
        return map;
    }
}
