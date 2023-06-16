package com.example.a3pagini

class Dijkstra(private val nrNoduri: Int, mAd: MatriceRara, start: Int, noduri: ArrayList<Nod>) {
    private val dist: FloatArray //va tine distanta cea mai scurta de la sursa la nodul i
    private val pred: IntArray
    private val vizitat: BooleanArray //va fi true daca nodul i este inclus in arborele de drum minim

    init {
        dist = FloatArray(noduri.size+1)
        pred = IntArray(noduri.size+1)
        vizitat = BooleanArray(nrNoduri)
        for (i in 0 until nrNoduri) {
            dist[i] = Float.MAX_VALUE
            pred[i] = -1
            vizitat[i] = false
        }
        dist[start] = 0f
        for (nr in 0 until nrNoduri) {
            val u = minNod(dist, vizitat)
            vizitat[u] = true
            for (i in 0 until nrNoduri) {
                if (!vizitat[i] && mAd.get(u,i) != 0f && dist[u] != Float.MAX_VALUE && (dist[u] + mAd.get(u,i) < dist[i])) {
                    pred[i] = u
                    dist[i] = dist[u] + mAd.get(u,i)
                }
            }
        }
    }

    fun minNod(dist: FloatArray, v: BooleanArray): Int {
        var min = Float.MAX_VALUE
        var min_index = -1
        for (i in 0 until nrNoduri) {
            if (!v[i] && (min_index == -1 || dist[i] < min)) {
                min = dist[i]
                min_index = i
            }
        }
        return min_index
    }

    fun daTraseu(stop: Int): List<Int>? {
        val rez: MutableList<Int> = ArrayList()
        var temp = stop
        while (temp >= 0) {
            rez.add(0, temp)
            temp = pred[temp]
        }
        return rez
    }
}