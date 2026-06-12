package Bar;

import java.util.Random;

public interface Cassa extends Runnable{
    public static final Random r = new Random();
    public int getID();
    public Bar getBar();
}
