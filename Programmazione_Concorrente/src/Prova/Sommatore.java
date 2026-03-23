package Prova;

public class Sommatore extends Thread{
    private int inizio;
    private int fine;
    private double[] v;
    private double ris = 0.0;

    public Sommatore(int inizio, int fine, double[] v) {
        this.inizio = inizio;
        this.fine = fine;
        this.v = v;
    }

    public double somma(){
        double som = v[inizio];
        this.inizio++;
        for(int i=inizio; i<=fine; i++){
            som += v[i];
        }
        System.out.println(som);
        return som;
    }

    public void run(){
        this.ris = this.somma();
    }

    public double getRis(){
        return ris;
    }
}
