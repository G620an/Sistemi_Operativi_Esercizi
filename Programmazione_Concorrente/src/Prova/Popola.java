package Prova;

public class Popola extends Thread{
    private long start;
    private long end;
    private long[] v;
    public Popola(long start, long end, long[] v){
        this.start = start;
        this.end = end;
        this.v = v;
    }
    @Override
    public void run(){
        if(end >= v.length) end = v.length-1;
        for(long i=start; i<=end; i++){
            v[(int)i] = i;
        }
    }
}
