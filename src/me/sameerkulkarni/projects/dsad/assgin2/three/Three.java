package me.sameerkulkarni.projects.dsad.assgin2.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Three
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static int minDistance(int dist[], Boolean sptSet[], int n)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < n; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    static int dijkstra(int graph[][], int src, int des)
    {
        int n = graph[0].length;
        int dist[] = new int[n]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[n];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < n; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < n-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, n);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < n; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=-1 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        return dist[des];
    }

    // Driver method
    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/me/sameerkulkarni/projects/dsad/assgin2/three/input.txt"));
        int n = scanner.nextInt();
        int src = scanner.nextInt();
        int des = scanner.nextInt();
        int speed = scanner.nextInt();
        int graph[][] = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        int distance = dijkstra(graph, src, des);
        System.out.println("Shortest Distance is " + distance+"KM");
        int timeInMinutes = (distance * 60) / speed;
        System.out.println("Will be reaching " + timeInMinutes + " minutes after 10AM");
    }
}