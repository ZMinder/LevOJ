package P1854;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//n个数
        while (n != 0) {
            String[] nums = new String[n];//存储n个数
            for (int i = 0; i < n; i++) {
                nums[i] = scan.next();//以字符串形式读取
            }
            //定义比较规则：字符串首字符大的排在前面 如果相等 后续字符按照首字符规则进行比较
            //如果一直相等 字符串长度短的放在前面 如果完全相等 无所谓顺序
            //谁在前面更大 谁就在前面
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
//                    return (o2 + o1).compareTo(o1 + o2); //代码量更少
                    if (o1.length() == 0 || o2.length() == 0) {//递归截止条件
                        return o2.length() - o1.length();
                    }
                    int p1 = 0;
                    int p2 = 0;
                    //下标未越界 并且相等
                    while (p1 < o1.length() && p2 < o2.length() && o1.charAt(p1) == o2.charAt(p2)) {
                        p1++;
                        p2++;
                    }
                    //两字符串完全相等
                    if (p1 == o1.length() && p2 == o2.length()) {
                        return 0;
                    }
                    if (p1 == o1.length()) {//o1字符串比o2短 且o1与o2前缀相等
                        return compare(o1, o2.substring(p2));
                    }
                    if (p2 == o2.length()) {//o2字符串比o1短 且o2与o1前缀相等
                        return compare(o1.substring(p1), o2);
                    }
                    //两字符串均未遍历完 意味着找到一个不相等的字符
                    return o2.charAt(p2) - o1.charAt(p1);
                }
            };
            Arrays.sort(nums, comparator);
            for (int i = 0; i < n; i++) {//将排完序的数组拼接起来
                System.out.print(nums[i]);
            }
            System.out.println();
            n = scan.nextInt();//读取下一组数据的个数
        }
    }
}
