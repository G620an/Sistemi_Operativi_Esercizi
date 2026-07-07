package Azienda_Agricola;

public interface AziendaAgricola extends Runnable{
    public static final int tRifornimento = 10000; // 10 secondi perchè 10 minuti è eccessivo per provarlo
    public static int sacchi = 200;
    public static int costo = 3;
    public static int tSpostamento = 1000; //1 secondo perchè 1 minuto è troppo

    public void paga(int qta);
    public void spostaSacchetti(int qta);
    public void rifornisci();
}
