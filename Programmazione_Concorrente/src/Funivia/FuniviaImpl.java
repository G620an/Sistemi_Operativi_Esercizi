package Funivia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FuniviaImpl implements Funivia {
    private boolean tPiedi;
    private Lock fun;
    private Condition entraFunivia;
    private Condition esciFunivia;
    private Condition svegliaPilota;
    private Condition aspetta;
    private boolean finito;
    private boolean chiama;
    private int[][] posti;
    private int i;
    private int cT;

    public FuniviaImpl(){
        this.fun = new ReentrantLock();
        this.entraFunivia = this.fun.newCondition();
        this.esciFunivia = this.fun.newCondition();
        this.svegliaPilota = this.fun.newCondition();
        this.aspetta = this.fun.newCondition();
        this.posti = new int[Funivia.maxPosti][2];
        this.finito = false;
        this.chiama = false;
        this.i = 0;
        this.cT = 0;
    }

    @Override
    public void pilotaStart() {
        try{
            int div;
            if (this.tPiedi) {
                div = 2;
            }else{
                div = 1;
            }
            this.fun.lock();
            while(!this.chiama){
                this.svegliaPilota.await();
            }
            this.chiama = false;
            Thread.sleep(5*Funivia.minutoMilli); //5 * 1 minuto
            for(int k = 0; k<Funivia.maxPosti/div; k+=div){
                System.out.println(this.posti[k][0]+" "+this.posti[k][1]);
            }
            this.finito = true;
            aspetta.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.fun.unlock();
        }
    }

    @Override
    public void pilotaEnd() {
        try{
            this.fun.lock();
            Thread.sleep(2*Funivia.minutoMilli);
            this.i = 0;
            this.tPiedi = !this.tPiedi;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.fun.unlock();
        }
    }

    @Override
    public void turistaSali(int t, int ID) {
        try{
            this.fun.lock();
            if (t == 1){
                while(this.i >= Funivia.maxPosti || this.tPiedi){
                    this.entraFunivia.await();
                }
                this.posti[i][0] = t;
                this.posti[i][1] = ID;
                i += 2;
            }else{
                while(this.i >= Funivia.maxPosti || !this.tPiedi){
                    this.entraFunivia.await();
                }
                this.posti[i][0] = t;
                this.posti[i][1] = ID;
                i ++;
            }
            this.chiama = true;
            this.finito = false;
            this.svegliaPilota.signal();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.fun.unlock();
        }
    }

    @Override
    public void turistaScendi(int t, int ID) {
        try{
            this.fun.lock();
            while(!finito){
                this.aspetta.await();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.fun.unlock();
        }
    }

    @Override
    public void run() {
        Pilota p = new PilotaImpl(1, this);
        (new Thread(p)).start();
        while(true){
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            int id = this.cT;
            int tipo = Funivia.r.nextInt(2);
            (new Thread(new TuristaImpl(id, tipo, this))).start();
            this.cT ++;

        }
    }

}
