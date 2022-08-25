import java.util.LinkedList;

public class ProcesoInicial extends Thread {
    private Buzon buzon;
    private LinkedList<String> subconjuntos;

    public ProcesoInicial(Buzon buzon, LinkedList<String> subconjuntos) {
        this.buzon = buzon;
        this.subconjuntos = subconjuntos;
    }

    public void send() {
        // enviar los subconjuntos al buzon de inicio
        while (!subconjuntos.isEmpty()) {

            // espera activa
            while (buzon.isFull()) {
                
            }
            // envia el mensaje al buzon
            if (subconjuntos.size() > 0) {
                
                buzon.add(subconjuntos.removeFirst());
            }

        }

    }

    public void run() {
        send();
    }

}
