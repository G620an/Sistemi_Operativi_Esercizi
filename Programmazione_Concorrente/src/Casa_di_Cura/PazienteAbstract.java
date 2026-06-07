package Casa_di_Cura;

import java.util.Objects;

public abstract class PazienteAbstract implements Paziente{
    public String toString(){
        return "(ID: " + this.getID() + "; Casa di Cura: " + this.getCasaDiCura() + ")"; 
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Paziente p){
            return p.getID() == this.getID();
        }
        return false;
    }

    public int hash(){
        return Objects.hash(this.getID());
    }
}