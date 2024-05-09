package P1794;

import java.io.*;

public class Main {

    public static boolean canIn(int size, int[] pre) {//判断这条鱼会不会被吃掉
        for (int j = 0; j < pre.length; j++) {
            if ((size >= pre[j] / 10 && size <= pre[j] / 2)
                    || (size >= pre[j] * 2 && size <= pre[j] * 10)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter ps = new PrintWriter(new OutputStreamWriter(System.out));
            String[] split = br.readLine().split(" ");
            int minSize = Integer.parseInt(split[0]);//新加入鱼的最小范围
            int maxSize = Integer.parseInt(split[1]);//新加入鱼的最大范围
            int n = Integer.parseInt(br.readLine());//鱼缸里面鱼的数量
            int[] pre = new int[n];//鱼缸中已经有的鱼
            split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                pre[i] = Integer.parseInt(split[i]);
            }
            int res = 0;
            for (int i = minSize; i <= maxSize; i++) {
                if (canIn(i, pre)) {
                    res++;
                }
            }
            ps.println(res);
            ps.flush();
            ps.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
