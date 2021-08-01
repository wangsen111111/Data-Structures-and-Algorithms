package com.company;

import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private static List<List<Integer>> result=new ArrayList<>();
    private static LinkedList<Integer> path=new LinkedList<>();
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        DFS(0,graph);
        return result;
    }
    public static void DFS(int node, int[][] graph){
        if(node==graph.length-1){
            path.add(node);
            result.add(new ArrayList<>(path));
            return;
        }
        path.add(node);
        for(int i=0;i<graph[node].length;i++){
            DFS(graph[node][i],graph);
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(allPathsSourceTarget(graph));
    }
}
