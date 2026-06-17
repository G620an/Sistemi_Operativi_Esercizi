package Funivia;

public class TuristaImpl implements Turista {
    private int ID;
    private int tipo;
    private Funivia funivia;

    public TuristaImpl(int ID, int tipo, Funivia funivia) {
        this.ID = ID;
        this.tipo = tipo;
        this.funivia = funivia;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Funivia getFunivia() {
        return this.funivia;
    }

    @Override
    public int getTipo() {
        return tipo;
    }

    public void run(){
        this.funivia.turistaSali(this.getTipo(), this.getID());
        System.out.println("Sono il turista " + this.getID() + " sono salito sulla funivia...");
        this.funivia.turistaScendi(this.getTipo(), this.getID());
        System.out.println("Sono il turista " + this.getID() + " sono sceso dalla funivia");
    }
}
