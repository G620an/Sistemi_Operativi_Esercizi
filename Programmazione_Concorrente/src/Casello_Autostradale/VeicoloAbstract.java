package Casello_Autostradale;

import java.util.Objects;

public abstract class VeicoloAbstract extends Thread implements Veicolo{

    @Override
    public String toString(){
        return "ID: " + this.getID();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(o instanceof Veicolo v){
            return this.getID() == v.getID();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getID());
    }
}
