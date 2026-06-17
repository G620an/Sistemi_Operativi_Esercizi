package Bar;

public class Esegui extends Thread{
    private Bar b;
    public Esegui(Bar b){
        this.b = b;
    }

    public void run(){
        (new Thread(this.b)).start();
    }

}
