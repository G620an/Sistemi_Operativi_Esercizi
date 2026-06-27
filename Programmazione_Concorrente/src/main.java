import Gelateria.GelateriaImpl;
import Gelateria.Gelateria;

public static void main(String[] args) throws InterruptedException {
    Gelateria g = new GelateriaImpl(1,10);
    (new Thread(g)).start();
}