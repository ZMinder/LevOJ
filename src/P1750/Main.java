package P1750;


import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
求出数组中逆序对的数量
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = readInfo(scan, n);
        long ret = mergeSort(arr, 0, n - 1);
        System.out.print(ret);
        scan.close();
    }

    @Test
    public void test() {
        String inputFile = "D:\\Code\\LevOJ\\src\\P1750\\input.txt";
        String outputFile = "D:\\Code\\LevOJ\\src\\P1750\\output.txt";

        try {
            Scanner scanner = new Scanner(new File(inputFile));
            FileWriter writer = new FileWriter(outputFile);

            // 获取文件行数，用于进度条
            int lines = countLines(inputFile);
            JFrame frame = new JFrame("Progress");
            JProgressBar progressBar = new JProgressBar(0, lines);
            frame.add(progressBar);
            frame.setSize(300, 100);
            frame.setVisible(true);

            int currentLine = 0;
            while (scanner.hasNextLine()) {
                if (!scanner.hasNextInt()) {
                    break; // 没有下一个整数，结束循环
                }

                int n = scanner.nextInt();
                int[] arr = new int[n];

                for (int i = 0; i < n; i++) {
                    if (!scanner.hasNextInt()) {
                        break; // 没有足够的整数，结束循环
                    }
                    arr[i] = scanner.nextInt();
                }

                long result = mergeSort(arr, 0, arr.length - 1);
                writer.write(result + "\n");

                // 更新进度条
                currentLine += 2;
                progressBar.setValue(currentLine);
            }

            scanner.close();
            writer.close();

            System.out.println("Output written to file: " + outputFile);
            frame.dispose(); // 关闭进度条窗口
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countLines(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        int count = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }
        scanner.close();
        return count;
    }


    public static long mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        long leftInfo = mergeSort(arr, left, mid);
        long rightInfo = mergeSort(arr, mid + 1, right);
        return leftInfo + rightInfo + merge(arr, left, mid, right);
    }

    public static long merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        long ret = 0;
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                ret += right - j + 1;
                help[index++] = arr[i++];
            } else {
                help[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            help[index++] = arr[i++];
        }
        while (j <= right) {
            help[index++] = arr[j++];
        }
        for (int k = 0; k < index; k++) {
            arr[left + k] = help[k];
        }
        return ret;
    }

    public static int[] readInfo(Scanner scan, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        return arr;
    }
}
