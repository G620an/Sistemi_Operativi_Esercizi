import Prova.*;

public static void main(String[] args)throws Exception{
Stampatore s1 = new Stampatore(0 , 100);
Stampatore s2 = new Stampatore(101, 200);
s1.start();
s1.join(); //Una sorta di wait fino a che questo thread non termina
s2.start();
}
