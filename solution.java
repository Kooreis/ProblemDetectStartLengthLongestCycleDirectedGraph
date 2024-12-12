Here is a Java console application that solves the problem:

```java
import java.util.*;

public class Main {
    static int V;
    static int[] color;
    static int[] par;
    static int cycle_start, cycle_end;

    static boolean dfs(int v, ArrayList<Integer>[] adj) {
        color[v] = 1;
        for (Integer u : adj[v]) {
            if (color[u] == 0) {
                par[u] = v;
                if (dfs(u, adj))
                    return true;
            } else if (color[u] == 1) {
                cycle_end = v;
                cycle_start = u;
                return true;
            }
        }
        color[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a].add(b);
        }
        color = new int[V];
        par = new int[V];
        cycle_start = -1;

        for (int i = 0; i < V; i++) {
            if (color[i] == 0 && dfs(i, adj))
                break;
        }

        if (cycle_start == -1) {
            System.out.println("Acyclic");
        } else {
            ArrayList<Integer> cycle = new ArrayList<>();
            cycle.add(cycle_start);
            for (int v = cycle_end; v != cycle_start; v = par[v])
                cycle.add(v);
            cycle.add(cycle_start);
            Collections.reverse(cycle);

            System.out.println("Cycle found: ");
            for (int v : cycle)
                System.out.print(v + " ");
            System.out.println();
            System.out.println("Length of cycle is " + cycle.size());
        }
    }
}
```

This program reads from the console the number of vertices in the graph and then the edges of the graph. It then uses a depth-first search to find a cycle in the graph. If a cycle is found, it prints the vertices in the cycle and the length of the cycle. If no cycle is found, it prints "Acyclic".