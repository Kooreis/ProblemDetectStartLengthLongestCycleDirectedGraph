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