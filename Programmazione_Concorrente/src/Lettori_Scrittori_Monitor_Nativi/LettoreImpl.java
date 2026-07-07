package Lettori_Scrittori_Monitor_Nativi;

public class LettoreImpl implements Lettore{
    private Biblioteca bib;
    private int ID;
    public LettoreImpl(int ID, Biblioteca bib){
        this.ID = ID;
        this.bib = bib;
    }

    public int getID(){
        return ID;
    }
    public Biblioteca getBib(){
        return bib;
    }

    public void run(){
        try{
            System.out.println("Sono il lettore " + this.getID() + " voglio leggere...");
            this.getBib().inizioLettura();
            System.out.println("Sono il lettore " + this.getID() + " sto leggendo...");
            Thread.sleep(r.nextInt(1000, 10000));
            System.out.println("Sono il lettore " + this.getID() + " ho finito di leggere");
            this.getBib().fineLettura();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
