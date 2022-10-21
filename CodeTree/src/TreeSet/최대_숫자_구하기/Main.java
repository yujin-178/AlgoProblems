package TreeSet.최대_숫자_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 1; i <= M; i++)
            set.add(i);

        st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();
        for(int i = 0; i < N;i++){
            set.remove(Integer.parseInt(st.nextToken()));
            sb.append(set.last()).append("\n");
        }

        System.out.println(sb);

    }
}
