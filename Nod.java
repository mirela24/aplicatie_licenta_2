
public class Nod implements Comparable<Nod> {
    public int valEuristica;
    public int nod;

    public Nod(int nod, int valEuristica){
        this.valEuristica=valEuristica;
        this.nod=nod;
    }
    public Nod(){}

    @Override
    public int compareTo(Nod nod){
        if (valEuristica < nod.valEuristica)
            return -1;
        if (valEuristica > nod.valEuristica)
            return 1;
        return 0;
    }
}
