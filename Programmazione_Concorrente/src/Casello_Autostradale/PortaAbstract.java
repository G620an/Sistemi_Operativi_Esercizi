package Casello_Autostradale;

import java.util.Objects;

public abstract class PortaAbstract extends Thread implements Porta{
    @Override
    public String toString() {
        return "ID: " + this.getID();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(o instanceof Porta p){
            return this.getID() == p.getID();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getID());
    }
}
