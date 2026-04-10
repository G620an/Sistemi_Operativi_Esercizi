package Prova;

public class Stampatore extends Thread{
    private int start;
    private int end;
    public Stampatore(int inizio, int fine){
        this.start = inizio;
        this.end = fine;
    }
    @Override
    public void run(){
        for(int i=start;i<=end;i++){
            System.out.print(i+" ");
        }
    }
}
