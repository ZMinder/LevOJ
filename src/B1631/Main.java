package B1631;

import java.util.*;

public class Main {
    public static class Edge {
        public int weight;//当前边的权重
        public int from;//起始顶点
        public int to;//结束顶点

        public Edge(int weight, int from, int to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }


    public static int readInfo(Scanner scan, HashMap<Integer, HashSet<Edge>> nodes) {
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {//初始化结点集合
            nodes.put(i + 1, new HashSet<>());
        }
        int m = scan.nextInt();
        int party = scan.nextInt();//party农场的编号
        for (int i = 0; i < m; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int weight = scan.nextInt();
            //加入邻接边
            nodes.get(from).add(new Edge(weight, from, to));
        }
        return party;
    }

    public static int getMinNode(HashMap<Integer, Integer> distance, HashSet<Integer> selected) {
        int minNode = -1;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
            if (!selected.contains(entry.getKey()) && entry.getValue() < min) {
                min = entry.getValue();
                minNode = entry.getKey();
            }
        }
        return minNode;
    }

    public static HashMap<Integer, Integer> getMinDistance(HashMap<Integer, HashSet<Edge>> nodes, int end, int start) {
        HashMap<Integer, Integer> distance = new HashMap<>();//记录从start到其余各个节点的最短距离
        HashSet<Integer> selected = new HashSet<>();
        distance.put(start, 0);//到自己的距离为0
        int minNode = getMinNode(distance, selected);
        while (minNode != -1) {
            int dis = distance.get(minNode);//到minNode的最短距离
            HashSet<Edge> edges = nodes.get(minNode);
            for (Edge edge : edges) {
                int to = edge.to;
                if (distance.containsKey(to)) {//更新
                    distance.put(to, Math.min(distance.get(to), dis + edge.weight));
                } else {//插入
                    distance.put(to, dis + edge.weight);
                }
            }
            selected.add(minNode);
            minNode = getMinNode(distance, selected);
        }
        return distance;
    }

    public static HashMap<Integer, HashSet<Edge>> reverse(HashMap<Integer, HashSet<Edge>> nodes) {
        HashMap<Integer, HashSet<Edge>> bak = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            bak.put(i + 1, new HashSet<>());
        }
        for (Map.Entry<Integer, HashSet<Edge>> entry : nodes.entrySet()) {
            HashSet<Edge> edges = entry.getValue();
            for (Edge edge : edges) {
                int from = edge.to;
                int to = edge.from;
                bak.get(from).add(new Edge(edge.weight, from, to));
            }
        }
        return bak;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, HashSet<Edge>> nodes = new HashMap<>();
        int party = readInfo(scan, nodes);//party农场的编号
        HashMap<Integer, Integer> home = getMinDistance(nodes, 1, party);//获取party回家的最短距离
        //反向建图
        nodes = reverse(nodes);
        HashMap<Integer, Integer> forwardParty = getMinDistance(nodes, 1, party);//获取party回家的最短距离
        int dis = -1;
        for (int i = 0; i < nodes.size(); i++) {
            dis = Math.max(dis, home.get(i + 1) + forwardParty.get(i + 1));
        }
        System.out.println(dis);
    }
}
