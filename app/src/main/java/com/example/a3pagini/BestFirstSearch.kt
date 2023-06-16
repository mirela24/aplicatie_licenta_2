package com.example.a3pagini

import com.example.a3pagini.Nod
import java.util.*

class BestFirstSearch(
    private val nrNoduri: Int,
    mAd: MatriceRara,
    valEur: FloatArray,
    start: Int,
    stop: Int
) {
    private val pred: IntArray
    var priorityQueue: PriorityQueue<Nod>

    init {
        pred = IntArray(nrNoduri)
        val vizitat = BooleanArray(nrNoduri)
        for (i in 0 until nrNoduri) {
            pred[i] = -1
            vizitat[i] = false
        }
        priorityQueue = PriorityQueue()
        priorityQueue.add(Nod(start, valEur[start]))
        vizitat[start] = true
        while (!priorityQueue.isEmpty()) {
            val u = daNodCuValEuristicaMin()
            if (u == stop) ///return

            for (i in 0 until nrNoduri) {
                if (!vizitat[i] && mAd.get(u,i) != 0f) {
                    vizitat[i] = true
                    pred[i] = u
                    priorityQueue.add(Nod(i, valEur[i]))
                }
            }
        }
    }

    private fun daNodCuValEuristicaMin(): Int {
        val nod = priorityQueue.remove()
        return nod.nod
    }

    fun daTraseu(stop: Int): List<Int> {
        val rez: MutableList<Int> = ArrayList()
        var temp = stop
        while (temp >= 0) {
            rez.add(0, temp)
            temp = pred[temp]
        }
        return rez
    }
}