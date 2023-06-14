package com.example.a3pagini

class Dijkstra(private val nrNoduri: Int, mAd: Array<IntArray>, start: Int, noduri: ArrayList<Nod>) {
    private val dist: IntArray //va tine distanta cea mai scurta de la sursa la nodul i
    private val pred: IntArray
    private val vizitat: BooleanArray //va fi true daca nodul i este inclus in arborele de drum minim

    init {
        dist = IntArray(noduri.size+1)
        pred = IntArray(noduri.size+1)
        vizitat = BooleanArray(nrNoduri)
        for (i in 0 until nrNoduri) {
            dist[i] = Int.MAX_VALUE
            pred[i] = -1
            vizitat[i] = false
        }
        dist[start] = 0
        for (nr in 0 until nrNoduri) {
            val u = minNod(dist, vizitat)
            vizitat[u] = true
            for (i in 0 until nrNoduri) {
                if (!vizitat[i] && mAd[u][i] != 0 && dist[u] != Int.MAX_VALUE && (dist[u] + mAd[u][i] < dist[i])) {
                    pred[i] = u
                    dist[i] = dist[u] + mAd[u][i]
                }
            }
        }
    }

    fun minNod(dist: IntArray, v: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var min_index = -1
        for (i in 0 until nrNoduri) {
            if (!v[i] && (min_index == -1 || dist[i] < min)) {
                min = dist[i]
                min_index = i
            }
        }
        return min_index
    }
}