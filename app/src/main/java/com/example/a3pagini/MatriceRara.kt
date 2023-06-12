package com.example.a3pagini

class MatriceRara(nrLin: Int,nrCol: Int) {
    private var nrl: Int
    private var nrc: Int
    private lateinit var valori: ArrayList<ElementMatriceRara>
    init {
        nrl = nrLin
        nrc = nrCol
        valori = ArrayList<ElementMatriceRara>()
    }
    public fun getNrLin(): Int{
        return nrl
    }
    public fun getNrCol(): Int{
        return nrc
    }
    public fun set(lin: Int, col:Int, valoare:Float){
        for(i in valori.indices){
            if(valori[i].lin==lin && valori[i].col==col){
                if(valoare==0f){
                    valori.removeAt(i)
                }
                else{
                    valori[i].valoare=valoare
                }
                return
            }
        }
        if(valoare!=0f){
            var el:ElementMatriceRara = ElementMatriceRara()
            el.lin=lin
            el.col=col
            el.valoare=valoare
            valori.add(el)
        }
    }
    public fun get(lin: Int, col:Int): Float{
        for(el in valori){
            if(el.lin==lin && el.col==col)return el.valoare
        }
        return 0f
    }
}
