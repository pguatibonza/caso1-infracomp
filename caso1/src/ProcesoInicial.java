import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcesoInicial extends Thread {
    private Buzon buzon;
    private LinkedList<String> subconjuntos;
    private boolean flag=true;

    

    public ProcesoInicial(Buzon buzon, LinkedList<String> subconjuntos) {
        this.buzon = buzon;
        this.subconjuntos = subconjuntos;
        
    }

    public void send() {
        synchronized (buzon) {
            while (!subconjuntos.isEmpty() || flag) {
                while (buzon.isFull()) {
                    try {
                        buzon.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (subconjuntos.size() > 0) {
                    buzon.add(subconjuntos.removeFirst());
                }
                // envia 3 mensajes de fin
                if (subconjuntos.size() == 0 && flag) {
                    for (int i = 0; i < 3; i++) {
                        subconjuntos.add("FIN");
                    }
                    flag=false;
                    
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
