import java.util.Scanner;

public class Multi_capacity_classroom_booking {
    public static class Information {
        String type;
        String name;
        int num;

        Information(String input) {
            String[] part = input.split(",");
            type = part[0];
            name = part[1];
            num = Integer.parseInt(part[2]);
        }
    }

    static int RealLength(int[][] a, int b) {
        int Length = 0;
        for (int i = 0; i < a[b].length; i++) {
            if (a[b][i] != 0) {
                Length++;
            }
        }
        return Length;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Information[] list = new Information[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Information(in.next());
        }
        int i = 0, j = 0, k = 0;
        int[][] Course = new int[3][n];
        int[][] Room = new int[3][n];
        for (Information element : list) {
            if (element.type.equals("C")) {
                switch (element.name) {
                    case "theory": {
                        Course[0][i] = element.num;
                        i++;
                        break;
                    }
                    case "lab": {
                        Course[1][j] = element.num;
                        j++;
                        break;
                    }
                    case "art": {
                        Course[2][k] = element.num;
                        k++;
                        break;
                    }
                }
            } else if (element.type.equals("R")) {
                switch (element.name) {
                    case "theory": {
                        Room[0][i] = element.num;
                        i++;
                        break;
                    }
                    case "lab": {
                        Room[1][j] = element.num;
                        j++;
                        break;
                    }
                    case "art": {
                        Room[2][k] = element.num;
                        k++;
                        break;
                    }
                }
            }
        }
        int cont = 0;
        int max = RealLength(Course, 0) + RealLength(Course, 1) + RealLength(Course, 2);
        for (int l = 0; l < 3; l++) {
            Loop:
            for (int m = 0; m < n; m++) {
                int min = Integer.MAX_VALUE;
                if (Course[l][m] != 0) {
                    for (int o = 0; o < n; o++) {
                        if (Room[l][o] >= Course[l][m] && ((Room[l][o] - Course[l][m]) < min)) {
                            min = Room[l][o] - Course[l][m];
                        }
                    }
                    for (int p = 0; p < n; p++) {
                        if (Room[l][p] - Course[l][m] == min) {
                            Room[l][p] = 0;
                            cont++;
                            continue Loop;
                        }
                    }
                }
            }
        }
        if (cont == max) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}