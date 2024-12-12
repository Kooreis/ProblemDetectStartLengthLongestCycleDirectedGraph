Here is a C# console application that solves the problem:

```csharp
using System;
using System.Collections.Generic;

public class Graph
{
    private int V;
    private List<int>[] adj;

    public Graph(int V)
    {
        this.V = V;
        adj = new List<int>[V];
        for (int i = 0; i < V; i++)
            adj[i] = new List<int>();
    }

    public void AddEdge(int v, int w)
    {
        adj[v].Add(w);
    }

    public void DetectCycle()
    {
        bool[] visited = new bool[V];
        bool[] recStack = new bool[V];

        for (int i = 0; i < V; i++)
            if (DetectCycleUtil(i, visited, recStack))
                return;
    }

    private bool DetectCycleUtil(int v, bool[] visited, bool[] recStack)
    {
        if (recStack[v])
            return true;

        if (visited[v])
            return false;

        visited[v] = true;

        recStack[v] = true;
        List<int> children = adj[v];

        foreach (int c in children)
            if (DetectCycleUtil(c, visited, recStack))
                return true;

        recStack[v] = false;

        return false;
    }

    public static void Main(string[] args)
    {
        Graph g1 = new Graph(4);
        g1.AddEdge(0, 1);
        g1.AddEdge(0, 2);
        g1.AddEdge(1, 2);
        g1.AddEdge(2, 0);
        g1.AddEdge(2, 3);
        g1.AddEdge(3, 3);
        g1.DetectCycle();
    }
}
```

This program creates a directed graph and checks for a cycle in the graph. If a cycle is detected, the program returns true; otherwise, it returns false. The program uses a depth-first search (DFS) to traverse the graph and a recursive helper function to check for cycles. The helper function uses two boolean arrays: visited[] to keep track of the vertices that have been visited, and recStack[] to keep track of the vertices in the recursion stack. If a vertex is reached that is already in the recursion stack, then there is a cycle in the tree.