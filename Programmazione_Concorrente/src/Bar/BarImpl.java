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
    private Condition pago;
    private boolean finito;

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
        this.pago = this.cassaLock.newCondition();
        this.finito = false;
    }

    public int getID(){
        return ID;
    }

    public boolean cassaLibera(){
        return this.cCassa == 0;
    }

    public boolean bancoLibero(){
        return this.cBancone == 0;
    }

    public boolean confrontaFile(){
        return cCassa <= cBancone;
    }//True se la fila della cassa minore o uguale a quella del banco

    public void beviCaffe(){
        try{
            try{
                this.bancoLock.lock();
                while (this.cBancone > 4){
                    this.banconaLib.await();
                }
                this.cBancone++;
            }finally {
                this.bancoLock.unlock();
            }
            Thread.sleep(this.r.nextInt(20000, 40000)); //Ogni cliente impiega tra i 20 e i 40 secondi per bere il caffè
            this.bancoLock.lock();
            this.cBancone--; //Devo liberare il bancone
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.bancoLock.unlock();
        }
    }

    public void pagaAllaCassa(){
        try {
            this.cassaLock.lock();
            this.cCassa++;
            while (this.cCassa > 1) {
                this.cassaLib.await();
            }
            this.finito = false;
            this.cassaPaga.signal();
            while(!finito){
                this.pago.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.cassaLock.unlock();
        }
    }

    public void serviCliente(){
        try{
            this.cassaLock.lock();
            while(this.cCassa < 1){
                this.cassaPaga.await();
            }
            System.out.println("Sono la cassa: sto servendo un cliente...");
            Thread.sleep(this.r.nextInt(5000, 10000)); //Ogni persona impiega tra i 5 e i 10 secondi per pagare
            System.out.println("Sono la cassa: ho finito di servire il cliente");
            this.pago.signal();
            this.finito = true;
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
                Thread.sleep(this.r.nextInt(1000, 10000));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            (new Thread(new PersonaImpl(this.contaP, this))).start();
            this.contaP++;
        }
    }

}
