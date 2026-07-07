import Esame_SISOP.Esame;
import Esame_SISOP.EsameImpl;

public static void main(String[] args) throws InterruptedException {
    Esame e = new EsameImpl(135);
    (new Thread(e)).start();
}