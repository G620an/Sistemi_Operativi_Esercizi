package Lettori_Scrittori_Monitor_Nativi;

public class ScrittoreImpl implements Scrittore{
    private Biblioteca bib;
    private int ID;
    public ScrittoreImpl(int ID, Biblioteca bib){
        this.ID = ID;
        this.bib = bib;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Biblioteca getBib() {
        return bib;
    }

    public void run(){
        try{
            System.out.println("Sono lo scrittore " + this.getID() + " voglio scrivere");
            getBib().inizioScrittura();
            System.out.println("Sono lo scrittore " + this.getID() + " sto scrivendo...");
            Thread.sleep(r.nextInt(10000, 100000));
            System.out.println("Sono lo scrittore " + this.getID() + " ho finito di scrivere");
            getBib().fineScrittura();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
