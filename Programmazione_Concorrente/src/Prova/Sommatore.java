package Prova;

import static java.lang.Math.abs;

public class Sommatore extends Thread{
    private long start;
    private long end;
    private long[] v;
    private long somma;
    public Sommatore(long start, long end, long[] v){
        this.start = start;
        this.end = end;
        this.v = v;
    }
    @Override
    public void run(){
        if(start < 0 || end >= v.length){
            start = abs(start);
            end = v.length - 1;
        }
        for(long i=start; i<=end; i++){
            somma += v[(int)i];
        }
    }

    public long getSomma(){
        return somma;
    }
}
