import Prova.Popola;
import Prova.Sommatore;


public class Codice_Somma{
    public static long somma()throws Exception{
        int N = 100;
        long n = 100000000;
        long passo = (n+1)/N + 1;
        long[] v = new long[(int)n+1];

        Popola[] p = new Popola[(int)N];
        long k = 1;
        for(int i=0; i<N; i++){
            p[i] =  new Popola(k, k+passo, v);
            k += passo + 1;
        }

        for(int i=0; i<N; i++) p[i].start();
        for(int i=0; i<N; i++) p[i].join();


        Sommatore[] s = new Sommatore[N];
        k=1;
        for(long i=0; i<N; i++){
            s[(int)i] = new Sommatore(k , k+passo, v);
            k += passo+1;
        }

        for(int i=0; i<N; i++) s[i].start();
        for(int i=0; i<N; i++) s[i].join();

        long somma = 0;
        for(int i=0; i<N; i++) somma += s[i].getSomma();


        long sommaGauss = (n *(n+1))/2;
        System.out.println("Somma_Gauss: " + sommaGauss);
        System.out.println("Somma_Concorrente: " + somma);

        //for(long i=0; i<v.length; i++) System.out.prlong(v[i] + " ");
        return somma;
    }
}

public static void main(String[] args) throws Exception{
    long d = System.currentTimeMillis();
    Codice_Somma.somma();
    long durata_concorrente = System.currentTimeMillis() - d;
    System.out.println("Durata concorrente: " + durata_concorrente);
    long n = 100000000;
    d = System.currentTimeMillis();
    long s = 0;
    for(long i=1;i<=n;i++){
        s+=i;
    }
    long durata_iterativo = System.currentTimeMillis() - d;

    System.out.println("Somma_Iterativa:" + s);
    System.out.println("Durata iterativo: " + durata_iterativo);
}
