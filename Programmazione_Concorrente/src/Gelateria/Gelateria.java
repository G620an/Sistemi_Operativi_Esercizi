package Gelateria;

import java.util.Random;

public interface Gelateria extends Runnable{
    public static final Random r = new Random();
    public void mangiaGelato();
    public void pagaGelato();
}
