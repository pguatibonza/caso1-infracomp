import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcesoFinal extends Thread {
    private LinkedList<String> subconjuntos = new LinkedList<String>();
    private Buzon buzon;
    private int contador = 0;
    

    public ProcesoFinal(Buzon buzon ) {
        this.buzon = buzon;
        
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
                System.out.println("FIN");
            } else {
                subconjuntos.add(mensaje);
            }
            if (contador == 3) {
                System.out.println("Fin del proceso, 3 fins");
            }
            //System.out.println("mensaje recibido en proceso final: " + mensaje);
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
        while(contador<3){
            receive();
        }
        print();
    }
}
