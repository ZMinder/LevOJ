package P1822;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static int getDepth(HashMap<Integer, ArrayList<Integer>> map, int id) {
        if (map.get(id) == null) {//叶子节点
            return 1;
        }
        ArrayList<Integer> list = map.get(id);
        int max = 0;//记录该节点下面的层数
        for (Integer next : list) {
            max = Math.max(max, getDepth(map, next));
        }
        return max + 1;
    }

    //快速输入输出 OJ数据输入不正确的话会RE
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            ArrayList<Integer> next = map.getOrDefault(from, new ArrayList<>());
            next.add(to);
            map.put(from, next);
        }
        int depth = getDepth(map, 1);
        System.out.println(depth - 1);
    }
}
