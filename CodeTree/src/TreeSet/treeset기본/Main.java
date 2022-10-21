package TreeSet.treeset기본;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static int n;
    public static TreeSet<Integer> s = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String c = sc.next();
            if (c.equals("add")) {
                s.add(sc.nextInt());
            } else if (c.equals("largest")) {
                sb.append(s.size() ==0 ? "None" : s.last()).append("\n");
            } else if (c.equals("smallest")) {
                sb.append(s.size() == 0 ? "None" : s.first()).append("\n");
            } else if (c.equals("lower_bound")) {
                int tmp = sc.nextInt();
                sb.append(s.ceiling(tmp) == null ? "None" : s.ceiling(tmp)).append("\n");
            } else if (c.equals("upper_bound")) {
                int tmp = sc.nextInt();
                sb.append(s.higher(tmp) == null ? "None" : s.higher(tmp)).append("\n");
            } else if (c.equals("remove")) {
                s.remove(sc.nextInt());
            } else if (c.equals("find")) {
                int tmp = sc.nextInt();
                sb.append(s.contains(tmp)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
