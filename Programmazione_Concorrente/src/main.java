import Casa_di_Cura.CasaDiCuraLC;
import Casa_di_Cura.Esegui;

public static void main(String[] args) throws InterruptedException {
    Esegui esegui = new Esegui(new CasaDiCuraLC(1));
    esegui.start();
}