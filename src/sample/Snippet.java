package sample;

public class Snippet {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub


        Pipe p1 = new BlockingQueue();
        Pipe p2 = new BlockingQueue();
        Pipe p3 = new BlockingQueue();


        Filter a1 = new FilterGui(null, p1); //Ce filtre est pour load de l'interafce graphique et récupération des inputs
        Filter b1 = new FilterCalcul(p1, p2); // Ce filter est pour le calcul
        Filter c1 = new FilterTrace(p2, p3);//Ce filtre est pour écrire dans un fichier et lire la trace.


        Thread th1 = new Thread(a1);
        Thread th2 = new Thread(b1);
        Thread th3 = new Thread(c1);



        th1.start();
       th2.start();
  th3.start();
        /*       th4.start();*/

    }



}