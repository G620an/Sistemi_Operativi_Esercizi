import Lettori_Scrittori_Monitor_Nativi.Biblioteca;
import Lettori_Scrittori_Monitor_Nativi.BibliotecaImplFIFO;

public static void main(String[] args) throws InterruptedException {
    Biblioteca b = new BibliotecaImplFIFO(30);
    (new Thread(b)).start();
}