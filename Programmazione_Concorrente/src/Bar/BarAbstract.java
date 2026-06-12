package Bar;

import java.util.Objects;

public abstract class BarAbstract implements Bar{
    public String toString(){
        return "Bar(ID: " + this.getID() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Bar b){
            return this.getID() == b.getID();
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(this.getID());
    }
}
