package Bar;

public class PersonaImpl extends PersonaAbstract{
    private int ID;
    private Bar bar;
    public PersonaImpl(int ID, Bar bar){
        this.ID = ID;
        this.bar = bar;
    }

    public int getID() {
        return this.ID;
    }

    public Bar vaiBar() {
        return this.bar;
    }

    public void run(){
        if (this.bar.cassaLibera()){
            this.bar.pagaAllaCassa();
        }
        if(this.bar.bancoLibero()){
            this.bar.beviCaffe();
        }
        if (this.bar.confrontaFile()){
            this.bar.pagaAllaCassa();
        }else{
            this.bar.beviCaffe();
        }
    }

}
