import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcesoInicial extends Thread {
    private Buzon buzon;
    private LinkedList<String> subconjuntos;
    private CyclicBarrier barrera;

    public ProcesoInicial(Buzon buzon, LinkedList<String> subconjuntos,CyclicBarrier barrera) {
        this.buzon = buzon;
        this.subconjuntos = subconjuntos;
        this.barrera=barrera;
    }

    public void send() {
        synchronized (buzon) {
            while (!subconjuntos.isEmpty()) {
                while (buzon.isFull()) {
                    this.yield();
                }
                if (subconjuntos.size() > 0) {
                    buzon.add(subconjuntos.removeFirst());
                }
                buzon.notifyAll();
            }
            // envia 3 mensajes de fin

            if (subconjuntos.size() == 0) {
                for (int i = 0; i < 3; i++) {
                    buzon.add("FIN");
                }
            }
        }
    }

    // enviar los subconjuntos al buzon de inicio

    public void run() {
        send();
        
        try {
            barrera.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

}
