import Funivia.FuniviaImpl;

public static void main(String[] args) throws InterruptedException {
    FuniviaImpl f = new FuniviaImpl();
    (new Thread(f)).start();
}