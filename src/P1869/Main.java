package P1869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());//n个士兵
            int[] height = new int[n];//n个士兵的身高
            String[] split = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {//读入数据 并且初始化dp
                height[i] = Integer.parseInt(split[i]);
            }
            int res = 1;//最后保留的序列至少长度为1
            int preDiff = 0;//前一次出现相反关系的的正或者负
            int curDiff = 0;//当前是正或者负
            for (int i = 1; i < n; i++) {
                curDiff = height[i] - height[i - 1];//当前身高与前一个的正负福关系
                if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                    //preDiff==0主要是考虑i=1的情况
                    //此时是出现了前后相反的情况 如果if不成立 一定是出现了非递减或非递增的情况
                    //当第一次出现curDiff和preDiff相反时 保留当前的i和i-1 丢弃preDiff计算时的i
                    res++;
                    preDiff = curDiff;
                }
            }
            System.out.println(n - res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
