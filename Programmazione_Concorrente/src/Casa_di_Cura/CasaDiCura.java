package Casa_di_Cura;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public abstract class CasaDiCura implements Runnable{
    public final static int Posti_Sala_Attesa = 3;
    public final static Random r = new Random();
    private LinkedList<Paziente> pazienti = new LinkedList<Paziente>();

    public abstract int getID();

    public abstract void pazienteEntra() throws InterruptedException; //Il pazziente deve poter entrare in sala di attesa

    public abstract void pazienteEsci() throws InterruptedException; //rilascio il semaforo che blocca i pazienti in sala di attesa

    public abstract void chiamaEIniziaOperazione() throws InterruptedException; //Cerca di chiamare un nuovo paziente dalla sala di attesa

    public abstract void fineOperazione() throws InterruptedException;//il medico segnala la fine delle operazioni non aspetta che il paziente esca
    //Il medico termina le operazioni e prepara la sala

    public abstract void addCount();

    public abstract int getCount();

    public String toString(){
        return this.getPazienti();
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof CasaDiCura cdc){
            return cdc.getID() == this.getID();
        }
        return false;
    }

    public int hash(){
        return Objects.hash(this.getID());
    }

    public void lancioCasualePaziente() throws InterruptedException {
        Thread.sleep(this.r.nextInt(100, 1000));
        Paziente p = new PazienteImpl(this.getCount(), this);
        this.pazienti.add(p);
        this.addCount();
        Thread tp = new Thread(p);
        tp.start();
    }

    public String getPazienti(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pazienti{").append("\n");
        Iterator<Paziente> it = this.pazienti.iterator();
        while (it.hasNext()){
            sb.append(it.next()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public void run(){
        Medico medico = new MedicoImpl(1, this);
        Thread tm = new Thread(medico);
        tm.start();
        while(true){
            try {
                this.lancioCasualePaziente();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}