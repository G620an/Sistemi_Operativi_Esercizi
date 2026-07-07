package Esame_SISOP;

public class StudenteImpl implements Studente {
    private int MAT;
    private Esame esame;

    public StudenteImpl(int MAT, Esame esame) {
        this.MAT = MAT;
        this.esame = esame;
    }

    public int getMAT() {
        return MAT;
    }

    public Esame getEsame() {
        return esame;
    }

    public void run(){
        try{
            Thread.sleep(r.nextInt(10, 2000));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Sono lo studente " + this.getMAT() + " voglio sostenere lo scritto...");
        if (this.getEsame().scritto(this.getMAT())){
            System.out.println("Sono lo studente " + this.getMAT() + " ho passato lo scritto voglio sostenere l'orale...");
            if (this.getEsame().orale(this.getMAT())){
                System.out.println("Sono lo studente " + this.getMAT() + " ho passato l'orale, voto finale: " + this.getEsame().votoComplessivo(this.getMAT()));
            }else{
                System.out.println("Sono lo studente " + this.getMAT() + " non ho passato l'orale, piango");
            }
        }else{
            System.out.println("Sono lo studente " + this.getMAT() + " non ho passato lo scritto, piango");
        }
    }
}
