package Casa_di_Cura;

public class Esegui extends Thread{
    private CasaDiCura cdc;
    public Esegui(CasaDiCura cdc){
        this.cdc = cdc;
    }

    public void run(){
        (new Thread(cdc)).start();
        /*
        try{

            while(true){
                System.out.println(this.cdc);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        */
    }

}
