package Casa_di_Cura;

public abstract class CasaDiCura implements Runnable{
    public final static int Posti_Sala_Attesa = 10;
    public abstract int getID();
    public abstract void pazienteEntra() throws InterruptedException;
        //Il pazziente deve poter entrare in sala di attesa
    public abstract void pazienteEsci() throws InterruptedException;
        //rilascio il semaforo che blocca i pazienti in sala di attesa
    public abstract void chiamaEIniziaOperazione() throws InterruptedException;
        //Cerca di chiamare un nuovo paziente dalla sala di attesa
    public abstract void fineOperazione() throws InterruptedException;
        //il medico segnala la fine delle operazioni non aspetta che il paziente esca
        //Il medico termina le operazioni e prepara la sala

    public String toString(){
        return "(ID: " + this.getID() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof CasaDiCura cdc){
            return cdc.getID() == this.getID();
        }
    }

    public int hash(){
        return Objects.hash(this.getId());
    }
}