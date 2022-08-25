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
        CyclicBarrier barrera= new CyclicBarrier(11);
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
        ProcesoInicial procesoInicial= new ProcesoInicial(buzonInicio,subconjuntos,barrera);
        ProcesoFinal procesoFinal=new ProcesoFinal(buzonFinal,barrera);
        ProcesoIntermedio[][] procesosIntermedios=new ProcesoIntermedio[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (j==0){
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonInicio, buzonesIntermedios[i][j],i,j,barrera);
                }
                else if (j==1){
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonesIntermedios[i][j-1], buzonesIntermedios[i][j],i,j,barrera);
                }
                else{
                    procesosIntermedios[i][j]=new ProcesoIntermedio(buzonesIntermedios[i][j-1], buzonFinal,i,j,barrera);
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
        //Espera a que los procesos terminen
        procesoInicial.join();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                procesosIntermedios[i][j].join();
            }
        }
        procesoFinal.join();
        //Imprimir los buzones
        procesoFinal.print();

       
        
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


