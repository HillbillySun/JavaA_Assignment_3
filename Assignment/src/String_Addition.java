import java.util.Scanner;

public class String_Addition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String number = in.next();
        int n = in.nextInt();
        long sum = 0;
        for (int i = 0; i < separate(number, n).length; i++) {
            sum += inverseNum(separate(number, n)[i]);
        }
        System.out.println(sum);
    }

    static String[] separate(String str, int n) {
        int length;
        if (str.length() % n == 0) {
            length = str.length() / n;
        } else {
            length = str.length() / n + 1;
        }
        String[] part = new String[length];
        int j = 0;
        for (int i = 0; i < str.length(); i += n) {
            part[j] = str.substring(i, Math.min(i + n, str.length()));
            j++;
        }
        return part;
    }

    static long inverseNum(String a) {
        char[] Num = a.toCharArray();
        int realLength = a.length() - 1;
        for (int i = 0; i < a.length() / 2; i++) {
            char change = Num[i];
            Num[i] = Num[realLength - i];
            Num[realLength - i] = change;
        }
        String done = String.valueOf(Num);
        return Long.parseLong(done);
    }
}
