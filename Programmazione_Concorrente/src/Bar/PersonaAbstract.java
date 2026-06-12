package Bar;

import java.util.Objects;

public abstract class PersonaAbstract implements Persona{
    public String toString(){
        return "Persona(ID: " + this.getID() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Persona p){
            return this.getID() == p.getID();
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(this.getID());
    }
}
