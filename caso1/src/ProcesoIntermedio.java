public class ProcesoIntermedio extends Thread {

    private Buzon buzonEntrada;
    private Buzon buzonSalida;
    private int numProceso;
    private int nivelTransformacion;


    public ProcesoIntermedio(Buzon buzonEntrada, Buzon buzonSalia,int numProceso, int nivelTransformacion) {
        this.buzonEntrada=buzonEntrada;
        this.buzonSalida=buzonSalida;
        this.numProceso=numProceso;
        this.nivelTransformacion=nivelTransformacion;
    }
  
}
