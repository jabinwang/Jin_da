package com.jabin.da.base.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class SparseGraph implements Graph{

    private int n;
    private int m;
    private boolean directed;
    private ArrayList<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    public int V(){
        return n;
    }

    public int E(){
        return m;
    }

    public void addEdge(int v, int w) {
        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    public boolean hasEdge(int v, int w) {
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].get(i) == w) {
                return true;
            }
        }
        return false;
    }

    public Iterable<Integer> adj(int v) {
        return g[v];
    }

    @Override
    public void show() {

    }
}
