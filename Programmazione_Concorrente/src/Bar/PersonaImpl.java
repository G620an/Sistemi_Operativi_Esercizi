package Bar;

public class PersonaImpl extends PersonaAbstract{
    private int ID;
    private Bar bar;
    public PersonaImpl(int ID, Bar bar){
        this.ID = ID;
        this.bar = bar;
    }

    public int getID() {
        return this.ID;
    }

    public Bar vaiBar() {
        return this.bar;
    }

    public void run(){
        if (this.bar.cassaLibera()){
            System.out.println("Sono il cliente " + this.getID() + " la cassa è libera sto andando a pagare...");
            this.bar.pagaAllaCassa();
            System.out.println("Sono il cliente " + this.getID() + " ho finito di pagare alla cassa e ora provo a bere un caffè");
            this.bar.beviCaffe();
            System.out.println("Sono il cliente " + this.getID() + " ho finito di bere il caffè me ne vado");
        }else if(this.bar.bancoLibero()){
            System.out.println("Sono il cliente " + this.getID() + " il banco è libero bevo il caffe...");
            this.bar.beviCaffe();
            System.out.println("Sono il cliente " + this.getID() + " ho bevuto il caffè ora pago...");
            this.bar.pagaAllaCassa();
            System.out.println("Sono il cliente " + this.getID() + " ho pagato quindi me ne vado");
        }else if (this.bar.confrontaFile()){
            System.out.println("Sono il cliente " + this.getID() + " la fila alla cassa è più corta, vado prima a pagare...");
            this.bar.pagaAllaCassa(); //La fila alla cassa è minore
            System.out.println("Sono il cliente " + this.getID() + " ho pagato ora provo a bere il caffè...");
            this.bar.beviCaffe();
            System.out.println("Sono il cliente " + this.getID() + " ho finito di bere il caffè me ne vado");
        }else{
            System.out.println("Sono il cliente " + this.getID() + " la fila al bancone è più corta...");
            this.bar.beviCaffe(); //La fila al bancone è minore
            System.out.println("Sono il cliente " + this.getID() + " ho bevuto il caffè ora pago...");
            this.bar.pagaAllaCassa();
            System.out.println("Sono il cliente " + this.getID() + " ho pagato quindi me ne vado");
        }
    }

}
