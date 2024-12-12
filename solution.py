Here is a Python console application that solves the problem:

```python
from collections import defaultdict

class Graph:
    def __init__(self, vertices):
        self.graph = defaultdict(list)
        self.V = vertices

    def addEdge(self, u, v):
        self.graph[u].append(v)

    def DFS(self, v, visited, recStack, cycle_length, cycle_start):
        visited[v] = True
        recStack[v] = True

        for neighbor in self.graph[v]:
            if visited[neighbor] == False:
                cycle_length[v] = 1 + self.DFS(neighbor, visited, recStack, cycle_length, cycle_start)
                cycle_start[v] = neighbor
            elif recStack[neighbor] == True:
                cycle_length[v] = 1
                cycle_start[v] = v

        recStack[v] = False
        return cycle_length[v]

    def findLongestCycle(self):
        visited = [False] * (self.V + 1)
        recStack = [False] * (self.V + 1)
        cycle_length = [-1] * (self.V + 1)
        cycle_start = [-1] * (self.V + 1)

        for node in range(self.V):
            if visited[node] == False:
                self.DFS(node, visited, recStack, cycle_length, cycle_start)

        max_cycle_start = cycle_length.index(max(cycle_length))
        max_cycle_length = max(cycle_length)

        cycle = [max_cycle_start]
        for _ in range(max_cycle_length):
            cycle.append(cycle_start[cycle[-1]])

        return cycle

if __name__ == "__main__":
    g1 = Graph(4)
    g1.addEdge(0, 1)
    g1.addEdge(1, 2)
    g1.addEdge(2, 3)
    g1.addEdge(3, 0)
    print(g1.findLongestCycle())
```

This program creates a directed graph and finds the longest cycle in it. The `findLongestCycle` function uses depth-first search to find cycles, and keeps track of the longest one found so far. The cycle is returned as a list of vertices.