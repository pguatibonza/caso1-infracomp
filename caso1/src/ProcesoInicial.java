import java.util.LinkedList;

public class ProcesoInicial extends Thread {
    private Buzon buzon;
    private LinkedList<String> subconjuntos;
   

    public ProcesoInicial(Buzon buzon, LinkedList<String> subconjuntos) {
        this.buzon = buzon;
        this.subconjuntos = subconjuntos;

    }

    public void send() {

        while (!subconjuntos.isEmpty()) {
            while (buzon.isFull()) {
                ProcesoInicial.yield();
            }
            synchronized (buzon) {
                if (subconjuntos.size() > 0) {
                    buzon.add(subconjuntos.removeFirst());
                }
                buzon.notifyAll();
            }
        }

    }

    // enviar los subconjuntos al buzon de inicio

    public void run() {
        send();
    }

}
