package com.example.a3pagini

class Nod{
    public var Id: Int = 0
    public var X: Float = 0f
    public var Y: Float = 0f
    public var Z: Float = 0f
    public var nume: String = ""

    private var valEuristica = 0f
    public var nod = 0

    constructor(nod: Int, valEuristica: Float) {
        this.valEuristica = valEuristica
        this.nod = nod
    }

    constructor() {}

    operator fun compareTo(nod: Nod): Int {
        if (valEuristica < nod.valEuristica) return -1
        return if (valEuristica > nod.valEuristica) 1 else 0
    }
}