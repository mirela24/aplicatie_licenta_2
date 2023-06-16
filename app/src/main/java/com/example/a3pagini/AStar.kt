package com.example.a3pagini

import com.example.a3pagini.Nod
import java.util.*

class AStar(private val nrNoduri: Int, mAd: MatriceRara, h: FloatArray, start: Int, stop: Int) {
    private val pred: IntArray
    var priorityQueue: PriorityQueue<Nod>
    private val f: FloatArray
    private val g: FloatArray

    init {
        pred = IntArray(nrNoduri)
        val vizitat = BooleanArray(nrNoduri)
        f = FloatArray(nrNoduri)
        g = FloatArray(nrNoduri)
        for (i in 0 until nrNoduri) {
            pred[i] = -1
            vizitat[i] = false
            f[i] = Float.MAX_VALUE
            g[i] = Float.MAX_VALUE
        }
        g[start] = 0f
        f[start] = h[start]
        priorityQueue = PriorityQueue()
        priorityQueue.add(Nod(start, f[start]))
        while (!priorityQueue.isEmpty()) {
            val u = daNodCuValEuristicaMin()
            vizitat[u] = true
            if (u == stop)  ////return

            for (i in 0 until nrNoduri) {
                if (!vizitat[i] && mAd.get(u,i) != 0f) {
                    vizitat[i] = true
                    val tentativa_g = g[u] + mAd.get(u,i)
                    if (tentativa_g >= g[i]) continue
                    pred[i] = u
                    g[i] = tentativa_g
                    f[i] = g[i] + h[i]
                    priorityQueue.add(Nod(i, f[i]))
                }
            }
        }
    }

    private fun daNodCuValEuristicaMin(): Int {
        val nod = priorityQueue.remove()
        return nod.nod
    }

    fun daTraseu(stop: Int): List<Int> {
        val rez:  MutableList<Int> = ArrayList()
        var temp = stop
        while (temp >= 0) {
            rez.add(0, temp)
            temp = pred[temp]
        }
        return rez
    }
}