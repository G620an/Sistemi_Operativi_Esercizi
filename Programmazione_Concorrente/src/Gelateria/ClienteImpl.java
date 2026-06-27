package Gelateria;

public class ClienteImpl implements Cliente{
    private int ID;
    private Gelateria gelateria;
    public ClienteImpl(int ID, Gelateria gelateria){
        this.ID = ID;
        this.gelateria = gelateria;
    }

    public Gelateria getGelateria(){
        return gelateria;
    }

    @Override
    public void run(){
        System.out.println("Sono il cliente " + this.ID + " voglio mangiare il gelato...");
        this.getGelateria().mangiaGelato();
        System.out.println("Sono il cliente " + this.ID + " ho finito di mangiare il gelato ora pago...");
        this.getGelateria().pagaGelato();
        System.out.println("Sono il cliente " + this.ID + " ho finito di pagare, torno a casa");
    }
}
