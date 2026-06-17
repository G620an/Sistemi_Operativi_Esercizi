package Funivia;

public interface Turista extends Runnable {
    int getID();
    int getTipo();
    Funivia getFunivia();
}
