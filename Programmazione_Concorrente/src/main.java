import Bar.Bar;
import Bar.BarImpl;
import Bar.Esegui;

public static void main(String[] args) throws InterruptedException {
    Bar b = new BarImpl(1);
    Esegui es = new Esegui(b);
    es.start();
}