package B3445;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//节点个数
        int m = scan.nextInt();//边的个数
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();//记录了某个节点与其邻接节点的关系
        for (int i = 0; i < n; i++) {
            map.put(i + 1, new HashMap<>());//int[] 0下标表示邻接点 1下标表示距离
        }
        for (int i = 0; i < m; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int weight = scan.nextInt();
            map.get(from).put(to, weight);
            map.get(to).put(from, weight);
        }
        long res = getRes(map);
        System.out.println(res);
    }


    public static long getRes(HashMap<Integer, HashMap<Integer, Integer>> map) {
        int[] order = new int[map.size() + 1];
        long distance = minDistance(map, order);//起点到终点的最短距离
        long max = -1;
        int from = map.size();
        int to = order[from];
        while (to != 0) {
            map.get(from).put(to, map.get(from).get(to) * 2);
            map.get(to).put(from, map.get(to).get(from) * 2);
            long dis = minDistance(map, null);
            max = Math.max(max, dis);
            map.get(from).put(to, map.get(from).get(to) / 2);
            map.get(to).put(from, map.get(to).get(from) / 2);
            from = to;
            to = order[from];
        }
        return max - distance;
    }


    //获取未确定最短距离的节点中起点到该节点最短的节点
    public static int getMin(HashMap<Integer, Long> distance, HashSet<Integer> select) {
        long minDistance = Integer.MAX_VALUE;
        int res = -1;
        for (Map.Entry<Integer, Long> entry : distance.entrySet()) {
            if (!select.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minDistance = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }

    public static long minDistance(HashMap<Integer, HashMap<Integer, Integer>> map, int[] order) {
        HashMap<Integer, Long> distance = new HashMap<>();//记录起点到其余顶点的最短距离
        HashSet<Integer> select = new HashSet<>();
        distance.put(1, 0l);
        int minNode = getMin(distance, select);
        while (minNode != -1) {
            long dis = distance.get(minNode);//起点到minNode的最短距离
            HashMap<Integer, Integer> nexts = map.get(minNode);
            for (Map.Entry<Integer, Integer> entry : nexts.entrySet()) {
                if (!distance.containsKey(entry.getKey()) ||
                        dis + entry.getValue() < distance.get(entry.getKey())) {//更新
                    distance.put(entry.getKey(), dis + entry.getValue());
                    if (order != null) {
                        order[entry.getKey()] = minNode;
                    }
                }
            }
            select.add(minNode);
            minNode = getMin(distance, select);
        }
        return distance.get(map.size());
    }
}
