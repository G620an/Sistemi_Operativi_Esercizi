import Prova.*;

public static void main(String[] args){
    int n = 400;
    int thread = 20;

    double[] v = new double[n];
    for(int i = 0; i < n; i++){
        v[i] = Math.random();
    }

    for(int i=0 ; i<n; i+=n/thread){
        int in = i; int fin = i+=(n/thread-1);
        Sommatore s = new Sommatore(in , fin, v);
        s.start();
    }
    System.out.println("---------------------------");
    System.out.println(Arrays.stream(v).sum());

}
