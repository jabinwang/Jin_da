package com.jabin.da.base.graph;

import java.util.ArrayList;

public class DenseGraph implements Graph{
    private int n;
    private int m;
    private boolean directed;
    private boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new boolean[n][n];
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        if (hasEdge(v, w)) {
            return;
        }
        g[v][w] = true;
        if(!directed) {
            g[w][v] = true;
        }
        m++;
    }

    public boolean hasEdge(int v, int w) {
        return g[v][w];
    }

    public Iterable<Integer> adj(int v) {
        ArrayList<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }


    @Override
    public void show() {

    }
}
