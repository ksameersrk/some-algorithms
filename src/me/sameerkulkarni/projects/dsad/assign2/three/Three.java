package me.sameerkulkarni.projects.dsad.assign2.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Three
{
    static int getUnvisitedMinDistanceIndex(int distanceFromSrc[], boolean visited[])
    {
        int minValue = Integer.MAX_VALUE;
        int minIndex = Integer.MIN_VALUE;
        for (int i = 0; i < distanceFromSrc.length; i++)
        {
            if (!visited[i] && distanceFromSrc[i] <= minValue)
            {
                minValue = distanceFromSrc[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static int dijkstra(int graph[][], int src, int des)
    {
        int n = graph[0].length;
        int distanceFromSrc[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int i = 0; i < n; i++)
        {
            distanceFromSrc[i] = Integer.MAX_VALUE;
        }
        distanceFromSrc[src] = 0;

        for (int count = 0; count < n-1; count++)
        {
            int x = getUnvisitedMinDistanceIndex(distanceFromSrc, visited);
            visited[x] = true;
            for (int y = 0; y < n; y++)
            {
                if (!visited[y] && graph[x][y] != -1 && distanceFromSrc[x] != Integer.MAX_VALUE
                        && (distanceFromSrc[x] + graph[x][y]) < distanceFromSrc[y])
                {
                    distanceFromSrc[y] = distanceFromSrc[x] + graph[x][y];
                }
            }
        }
        return distanceFromSrc[des];
    }

    static int getTimeInMinutes(int speed, int distance) {
        return (distance * 60) / speed;
    }

    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/me/sameerkulkarni/projects/dsad/assgin2/three/input.txt"));
        int n = scanner.nextInt();
        int src = scanner.nextInt();
        int des = scanner.nextInt();
        int speed = scanner.nextInt();
        int graph[][] = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        int distance = dijkstra(graph, src, des);
        System.out.println("Shortest Distance is " + distance+"KM");
        System.out.println("Will be reaching " + getTimeInMinutes(speed, distance) + " minutes after 10AM");
    }
}