package Lettori_Scrittori_Monitor_Nativi;

public interface Biblioteca extends Runnable{
    public void inizioLettura();
    public void fineLettura();
    public void inizioScrittura();
    public void fineScrittura();
}
