package Bar;

import java.util.Objects;

public abstract class CassaAbstract implements Cassa{
    public String toString() {
        return "CassaAbstract(ID: " + getID() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Cassa c){
            return c.getID() == this.getID();
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(getID());
    }
}
