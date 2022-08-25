public class ProcesoIntermedio extends Thread {

    private Buzon buzonEntrada;
    private Buzon buzonSalida;
    private int numProceso;
    private int nivelTransformacion;
    private String mensajeActual;
    private boolean fin=false;


    public ProcesoIntermedio(Buzon buzonEntrada, Buzon buzonSalida,int numProceso, int nivelTransformacion) {
        this.buzonEntrada=buzonEntrada;
        this.buzonSalida=buzonSalida;
        this.numProceso=numProceso;
        this.nivelTransformacion=nivelTransformacion;
    }

    //metodo para recoger un mensaje del buzon de entrada
    public void receive() {
        synchronized(buzonEntrada){
            while(buzonEntrada.isEmpty()){
                try {
                    buzonEntrada.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mensajeActual=buzonEntrada.send();
            System.out.println("mensaje recibido: " + mensajeActual);
            if(!mensajeActual.equals("FIN")){
                mensajeActual=mensajeActual+ "T" + nivelTransformacion+"" + numProceso;
                buzonEntrada.notifyAll();   
            }
            else{
                fin=true;
            }
            
        }
      
    }
    //metodo para enviar un mensaje al buzon de salida
    public void send(){
        synchronized(buzonSalida){
            while(buzonSalida.isFull()){
                try{
                    buzonSalida.wait();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            buzonSalida.add(mensajeActual);
            System.out.println("mensaje enviado : " +mensajeActual);
            buzonSalida.notifyAll();
        }

    }

    public void run(){
        while(!fin){
            receive();
            send();
        }
    }
  
}
