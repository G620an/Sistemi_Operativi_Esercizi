package Funivia;

public class PilotaImpl implements Pilota{
    private int ID;
    private Funivia funivia;

    public PilotaImpl(int ID, Funivia funivia) {
        this.ID = ID;
        this.funivia = funivia;
    }
    @Override
    public Funivia getFunivia() {
        return this.funivia;
    }
    public int  getID() {
        return ID;
    }
    public void run(){
        while(true){
            this.funivia.pilotaStart();
            this.funivia.pilotaEnd();
        }
    }
}
