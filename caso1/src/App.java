import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Ingrese el tamaño del buzon de inicio y fin: ");
        int tamanioBuzonGrande=scanner.nextInt();
        System.out.println("Ingrese el tamaño del buzon intermedio: ");
        int tamanioBuzon=scanner.nextInt();
        System.out.println("Ingrese la cantidad de subconjuntos: ");
        int n=scanner.nextInt();
        LinkedList<String> subconjuntos= crearSubconjuntos(n);
        
        //imprimir todos los subconjuntos
        
        
        //Creacion de los buzones
        Buzon buzonInicio= new Buzon(tamanioBuzonGrande);
        Buzon buzonFinal= new Buzon(tamanioBuzonGrande);
        Buzon[][] buzonesIntermedios=new Buzon[3][2];
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                buzonesIntermedios[i][j]=new Buzon(tamanioBuzon);
            }
        }
        //Creacion de los procesos
        ProcesoInicial procesoInicial= new ProcesoInicial(buzonInicio,subconjuntos);
        ProcesoFinal procesoFinal=new ProcesoFinal(buzonFinal);
        ProcesoIntermedio[][] procesosIntermedios=new ProcesoIntermedio[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (j==0){
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonInicio, buzonesIntermedios[i][j],i,j);
                }
                else if (j==1){
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonesIntermedios[i][j-1], buzonesIntermedios[i][j],i,j);
                }
                else{
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonesIntermedios[i][j-1], buzonFinal,i,j);
                }
            }
        }
        //Inicio de los procesos
        procesoInicial.start();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                procesosIntermedios[i][j].start();
            }
        }
        procesoFinal.start();
       
        

       
        
    }
    
    //Metodo para crear los subconjuntos
    public static LinkedList<String> crearSubconjuntos( int n){
        LinkedList<String> subconjuntos= new LinkedList<String>();
        for(int i=0;i<n;i++){
            subconjuntos.add("M"+i);
        }
        return subconjuntos;
    }

}


