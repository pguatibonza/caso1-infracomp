import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread {
    private LinkedList<String> subconjuntos = new LinkedList<String>();
    private Buzon buzon;
    private int contador = 0;
    private CyclicBarrier barrera;

    public ProcesoFinal(Buzon buzon, CyclicBarrier barrera) {
        this.buzon = buzon;
        this.barrera = barrera;
    }

    // metodo para recibir mensajes del buzon
    public void receive() {
        synchronized (buzon) {
            while (buzon.isEmpty()) {
                try {
                    buzon.wait();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
            String mensaje = buzon.send();
            if (mensaje.equals("FIN")) {
                contador++;
            } else {
                subconjuntos.add(mensaje);
            }
            if (contador == 3) {
                System.out.println("Fin del proceso");
            }
            System.out.println("mensaje recibido.....................: " + mensaje);
            buzon.notifyAll();
        }

    }

    // imprime los subconjuntos
    public void print() {
        for (String subconjunto : subconjuntos) {
            System.out.println(subconjunto);
        }
    }

    // metodo para ejecutar el proceso
    public void run() {
        receive();
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
