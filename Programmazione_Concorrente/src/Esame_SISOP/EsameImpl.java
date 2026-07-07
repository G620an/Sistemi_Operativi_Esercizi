package Esame_SISOP;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EsameImpl implements Esame{
    private int N;
    private Lock lock;
    private Condition entraScritto;
    private Condition entraOrale;
    private HashMap<Integer, Integer[]> voti;
    private int occupatiScritto;
    private int occupatiOrale;
    private LinkedList<Integer> codaScritto;
    private LinkedList<Integer> codaOrale;

    public EsameImpl(int N){
        this.N = N;
        lock = new ReentrantLock();
        entraScritto = lock.newCondition();
        entraOrale = lock.newCondition();
        voti = new HashMap<>();
        occupatiScritto = 0;
        occupatiOrale = 0;
        codaScritto = new LinkedList<>();
        codaOrale = new LinkedList<>();
    }

    public boolean scritto(int MAT){
        try{
            lock.lock();
            codaScritto.add(MAT);
            while(occupatiScritto >= maxPostiScritto || codaScritto.indexOf(MAT) > maxPostiScritto){
                entraScritto.await();
            }
            occupatiScritto++;
            lock.unlock();
            Thread.sleep(r.nextInt(5000, 40000));
            lock.lock();
            int voto = r.nextInt(minVoto, maxVoto+1);
            Integer[] v = {voto, 0};
            voti.put(MAT, v);
            occupatiScritto--;
            codaScritto.remove(codaScritto.indexOf(MAT));
            entraScritto.signalAll();
            if(voto < passaScritto){
                return false;
            }else{
                return true;
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return true;
    }

    public boolean orale(int MAT){
        try{
            lock.lock();
            codaOrale.add(MAT);
            while(occupatiOrale >= maxPostiOrale || codaOrale.indexOf(MAT) > maxPostiOrale){
                entraOrale.await();
            }
            occupatiOrale++;
            lock.unlock();
            Thread.sleep(r.nextInt(1000, 20000));
            lock.lock();
            int voto = r.nextInt(minVoto, maxVoto+1);
            Integer[] v = voti.get(MAT);
            v[1] = voto;
            voti.put(MAT, v);
            occupatiOrale--;
            codaOrale.remove(codaOrale.indexOf(MAT));
            entraOrale.signalAll();
            if(voto < passaEsame){
                return false;
            }else{
                return true;
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return true;
    }

    public int votoComplessivo(int MAT){
        Integer[] v = voti.get(MAT);
        return (v[0]+v[1])/2;
    }

    public void run(){
        for(int i = 0; i < N; i++){
            (new Thread(new StudenteImpl(i, this))).start();
        }
    }
}
