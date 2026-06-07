package Casa_di_Cura;

import java.util.Objects;

public abstract class MedicoAbstract implements Medico{
    public String toString(){
        return "(ID: " + this.getID() + "; CasaDiCura: " + this.getCasaDiCura() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Medico m){
            return m.getID() == this.getID();
        }
        return false;
    }

    public int hash(){
        return Objects.hash(this.getID());
    }
    
}