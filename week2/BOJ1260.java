import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static boolean[] visited;
    public static ArrayList<Integer>[] list;
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int value) {
        visited[value] = true;
        sb.append(value).append(" ");
        if (list[value] != null) {
            for (int e : list[value]) {
                if (!visited[e]) {
                    dfs(e);
                }
            }
        }
    }

    public static void bfs(int value) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(value);
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            visited[tmp] = true;
            if (list[tmp] != null) {
                for (int e : list[tmp]) {
                    if (!visited[e]) {
                        queue.offer(e);
                        visited[e] = true;
                    }
                }
            }
            sb.append(tmp).append(" ");
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }
        for (int i = 1; i <= n; i++) {
            if (list[i] != null) {
                Collections.sort(list[i]);
            }
        }
        dfs(v);
        sb.append("\n");
        Arrays.fill(visited, false);
        bfs(v);
        System.out.print(sb);
    }
}
