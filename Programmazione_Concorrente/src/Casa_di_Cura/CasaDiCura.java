public abstract class CasaDiCura implements Runnable{
    public final static int Posti_Sala_Attesa = 10;
    public abstract void pazienteEntra() throws InterruptedException;
        //Il pazziente deve poter entrare in sala di attesa
    public abstract void pazienteEsce() throws InterruptedException;
        //rilascio il semaforo che blocca i pazienti in sala di attesa
    public abstract void chiamaEIniziaOperazioni() throws InterruptedException;
        //Cerca di chiamare un nuovo paziente dalla sala di attesa
    public abstract void fineOperazioni() throws InterruptedException;
        //il medico segnala la fine delle operazioni non aspetta che il paziente esca
        //Il medico termina le operazioni e prepara la sala
}