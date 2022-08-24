import java.util.LinkedList;

public class ProcesoInicial extends Thread{
    private Buzon buzon;
    private LinkedList<String> subconjuntos;
    public ProcesoInicial(Buzon buzon, LinkedList<String> subconjuntos) {
        this.buzon = buzon;
        this.subconjuntos=subconjuntos;
    }
    
}
