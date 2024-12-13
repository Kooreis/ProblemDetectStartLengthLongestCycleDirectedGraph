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