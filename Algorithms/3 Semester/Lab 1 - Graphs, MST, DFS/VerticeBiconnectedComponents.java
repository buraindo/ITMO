import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class VerticeBiconnectedComponents {
    private static HashMap<Integer, List<Integer>>[] Graph;
    private static Boolean[] Used;
    private static int[] Component;
    private static int[] Enter;
    private static Stack<Integer> Stack;
    private static int Time;
    private static int ComponentCount;

    private static int Dfs(int v, int parent) {
        Time++;
        Enter[v] = Time;
        int min = Time;
        for(Map.Entry<Integer, List<Integer>> e : Graph[v].entrySet())
        {
            int u = e.getKey();
            List<Integer> vertices = e.getValue();
            if (u == parent) continue;
            int next;
            int initialSize = Stack.size();
            for(int vertex : vertices)
            {
                if (Used[vertex]) continue;
                Stack.push(vertex);
                Used[vertex] = true;
            }

            if (Enter[u] == 0) {
                next = Dfs(u, v);
                if (next >= Enter[v]) {
                    ComponentCount++;
                    while (Stack.size() != initialSize) {
                        Component[Stack.pop()] = ComponentCount;
                    }
                }
            } else {
                next = Enter[u];
            }
            min = Math.min(min, next);
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        Graph = new HashMap[n];
        Stack = new Stack<>();
        Used = new Boolean[m];
        Component = new int[m];
        Enter = new int[n];
        for (int i = 0; i < n; i++) {
            Graph[i] = new HashMap<>();
            Enter[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            Used[i] = false;
            Component[i] = 0;
            String[] localInput = reader.readLine().split(" ");
            int u = Integer.parseInt(localInput[0]) - 1;
            int v = Integer.parseInt(localInput[1]) - 1;
            if (!Graph[u].containsKey(v)) {
                Graph[u].put(v, new ArrayList<>(Collections.singletonList(i)));
            } else {
                Graph[u].get(v).add(i);
            }

            if (!Graph[v].containsKey(u)) {
                Graph[v].put(u, new ArrayList<>(Collections.singletonList(i)));
            } else {
                Graph[v].get(u).add(i);
            }
        }
        reader.close();

        for (int v = 0; v < n; v++) {
            if (Enter[v] == 0) {
                Stack.clear();
                Time = 0;
                Dfs(v, -1);
            }
        }

        System.out.println(ComponentCount);
        for (int i = 0; i < m; i++) {
            System.out.print(Component[i] + " ");
        }
    }
}
