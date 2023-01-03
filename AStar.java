import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    private int nrNoduri;
    private int pred[];
    PriorityQueue<Nod> priorityQueue;
    private int f[];
    private int g[];

    public AStar(int nrNoduri,int mAd[][], int[] h, int start, int stop){
        this.nrNoduri=nrNoduri;
        pred=new int[nrNoduri];
        boolean vizitat[]=new boolean[nrNoduri];
        f=new int[nrNoduri];
        g=new int[nrNoduri];
        for (int i=0; i<nrNoduri; i++) {
            pred[i] = -1;
            vizitat[i] = false;
            f[i]=Integer.MAX_VALUE;
            g[i]=Integer.MAX_VALUE;
        }
        g[start]=0;
        f[start]=h[start];
        priorityQueue=new PriorityQueue<Nod>();
        priorityQueue.add(new Nod(start, f[start]));
        while(!priorityQueue.isEmpty()){
            int u=daNodCuValEuristicaMin();
            vizitat[u]=true;
            if(u==stop)return;
            for (int i = 0; i < nrNoduri; i++) {
                if (!vizitat[i] && mAd[u][i] != 0) {
                    vizitat[i]=true;
                    int tentativa_g=g[u]+mAd[u][i];
                    if(tentativa_g>=g[i])continue;
                    pred[i] = u;
                    g[i]=tentativa_g;
                    f[i]=g[i]+h[i];
                    priorityQueue.add(new Nod(i,f[i]));
                }
            }
        }
    }

    private int daNodCuValEuristicaMin()    {
        Nod nod = priorityQueue.remove();
        return nod.nod;
    }
    List<Integer> daTraseu(int stop){
        List<Integer> rez=new ArrayList<Integer>();
        int temp=stop;
        while(temp>=0){
            rez.add(0,temp);
            temp=pred[temp];
        }
        return rez;
    }
}

