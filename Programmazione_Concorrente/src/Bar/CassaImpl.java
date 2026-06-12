package Bar;

public class CassaImpl extends CassaAbstract{
    private int ID;
    private int contaCaffe; //Conto i caffè pensando a 1€ per caffe, per sapere quando ho in cassa
    private Bar bar;

    public CassaImpl(int ID, Bar bar){
        this.ID = ID;
        this.bar = bar;
    }

    public int getID(){
        return ID;
    }

    public Bar getBar(){
        return bar;
    }

    public void run(){
        while(true){
            this.bar.serviCliente();
        }
    }
}
