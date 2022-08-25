import java.util.LinkedList;

public class Buzon {
    private LinkedList<String> buzon = new LinkedList<String>();
    private int tamanioBuzon;

    public Buzon(int tamanioBuzon) {
        this.tamanioBuzon = tamanioBuzon;
    }
    //Metodo para agregar un mensaje al buzon
    public void add(String mensaje) {
        if (buzon.size() < tamanioBuzon) {
            buzon.add(mensaje);

        }
    }
    //Metodo para enviar un mensaje del buzon
    public String send() {
        if (buzon.size() > 0) {
            return buzon.removeFirst();
        }
        return null;
    }


    //Metodo para saber si el buzon esta lleno
    public boolean isFull() {
        return buzon.size() == tamanioBuzon;
    }
    //metodo para saber si el buzon esta vacio
    public boolean isEmpty() {
        return buzon.size() == 0;
    }


}
