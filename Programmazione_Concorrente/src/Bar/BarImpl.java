package Bar;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarImpl extends BarAbstract{
    private int ID;
    private Stack<Persona> filaCassa;
    private Stack<Persona> filaBancone;
    private int cCassa;
    private int cBancone;
    private int contaP;
    private Lock cassaLock;
    private Lock bancoLock;
    private Condition cassaLib;
    private Condition banconaLib;
    private Condition cassaPaga;

    public BarImpl(int ID){
        this.ID = ID;
        this.filaCassa = new Stack<Persona>();
        this.filaBancone = new Stack<Persona>();
        this.cCassa = 0;
        this.cBancone = 0;
        this.contaP = 0;
        this.cassaLock = new ReentrantLock();
        this.bancoLock = new ReentrantLock();
        this.cassaLib = this.cassaLock.newCondition();
        this.banconaLib = this.bancoLock.newCondition();
        this.cassaPaga = this.cassaLock.newCondition();
    }

    public int getID(){
        return ID;
    }

    public boolean cassaLibera(){
        if (this.cCassa == 0) return true;
        return false;
    }

    public boolean bancoLibero(){
        if (this.cBancone == 0) return true;
        return false;
    }

    public boolean confrontaFile(){
        if (cCassa <= cBancone){
            return true;
        }
        return false;
    }//True se la fila della cassa minore o uguale a quella del banco

    public void pagaAllaCassa(){
        try {
            this.cassaLock.lock();
            while (this.cCassa > 0) {
                this.cassaLib.await();
            }
            this.cassaPaga.signal();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.cassaLock.unlock();
        }
    }

    public void beviCaffe(){
        try{
            this.bancoLock.lock();
            while (this.cBancone > 4) {

            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {

        }
    }

    public void serviCliente(){
        try{
            this.cassaLock.lock();
            while(this.cCassa == 0){
                this.cassaPaga.await();
            }
            Thread.sleep(this.r.nextInt(10, 100));
            this.cCassa--;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            this.cassaLock.unlock();
        }
    }

    public void run(){
        Cassa c = new CassaImpl(1, this);
        (new Thread(c)).start();
        while(true){
            try{
                Thread.sleep(this.r.nextInt(100, 1000));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            (new Thread(new PersonaImpl(this.contaP, this))).start();
            this.contaP++;
        }
    }

}
