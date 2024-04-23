import static java.lang.Math.*;

import java.util.Scanner;

public class Cyclone {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] board = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = in.nextInt();
            }
        }
        int Start = in.nextInt();
        int k = in.nextInt();
        int x = location(board, Start)[0];
        int y = location(board, Start)[1];
        int center = fourMax(x, y, board.length - 1 - x, board[0].length - 1 - y);
        int length = 2 * center + 1;
        int[][] real = new int[length][length];
        int[][] isO = new int[length][length];
        int[][] mark = new int[length + 2][length + 2];
        int direction = 1;
        int step = 0;
        int Target = 0;
        int xT = Transform(x, y, center)[0];
        int yT = Transform(x, y, center)[1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                real[i + xT][j + yT] = board[i][j];
                isO[i + xT][j + yT]++;
            }
        }
        int i = 0, j = length - 1;
        while (i < length && j < length && (i != center || j != center)) {
            if (isO[i][j] == 1) {
                step++;
                if (step == m * n - k) {
                    Target = real[i][j];
                    break;
                }
            }
            mark[i + 1][j + 1]++;
            if (direction == 1 && (j == 0 || mark[i + 1][j] == 1)) {
                direction = 2;
            } else if (direction == 2 && (i == length - 1 || mark[i + 2][j + 1] == 1)) {
                direction = 3;
            } else if (direction == 3 && (j == length - 1 || mark[i + 1][j + 2] == 1)) {
                direction = 4;
            } else if (direction == 4 && (i == 0 || mark[i][j + 1] == 1)) {
                direction = 1;
            }
            switch (direction) {
                case 1: {
                    j--;
                    break;
                }
                case 2: {
                    i++;
                    break;
                }
                case 3: {
                    j++;
                    break;
                }
                case 4: {
                    i--;
                    break;
                }
            }
        }
        System.out.println(Target);
    }

    static int[] location(int[][] board, int Start) {
        int[] coordinate = new int[2];
        Loop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == Start) {
                    coordinate[0] = i;
                    coordinate[1] = j;
                    break Loop;
                }
            }
        }
        return coordinate;
    }

    static int fourMax(int a, int b, int c, int d) {
        int m1 = max(a, b);
        int m2 = max(c, d);
        return max(m1, m2);
    }

    static int[] Transform(int x, int y, int center) {
        return new int[]{center - x, center - y};
    }
}
