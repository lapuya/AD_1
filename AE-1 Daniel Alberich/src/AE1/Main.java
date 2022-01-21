package AE1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main  {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		
		File file = new File("coches.dat");
		
		// Si el file existe lo leo para meterlo en el array
		if (file.exists()) {
			
			try (FileInputStream fichero = new FileInputStream("coches.dat");
					DataInputStream lector = new DataInputStream (fichero);){
				
							boolean eof = false;
				while (!eof) {
				
					try {
						String id = lector.readUTF();
						String matricula = lector.readUTF();
						String marca = lector.readUTF();
						String modelo = lector.readUTF();
						String color = lector.readUTF();
						
						Coche c = new Coche(id, matricula, marca, modelo, color);
						
						listaCoches.add(c);
					} catch (EOFException e1) {
							eof = true;
					} catch (IOException e2) {
						System.out.println("Error en la lectura de datos.");
						System.out.println(e2.getMessage());
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al abrir el fichero");
				System.out.println(e.getMessage());
				return;
			}	
			
			
			
		}
		
		//MENÚ
		while (opcion != 5) {		
			System.out.println("-------------------------------");
			System.out.println("-------ESCOJA UNA OPCIÓN-------");
			System.out.println("-------------------------------");
			System.out.println("1- Añadir nuevo coche.");
			System.out.println("2- Borrar coche por ID.");
			System.out.println("3- Consultar coche por ID.");
			System.out.println("4- Listado de coches.");
			System.out.println("5- Cerrar el programa.");
			System.out.println("-------------------------------");
			
			opcion = sc.nextInt();
	
			switch(opcion) {
			
				case 1:
					//Añadir coche
					
					Coche coche = new Coche();
					
					System.out.println("Introduce el ID del coche: ");
					String id = sc.nextLine();								//No me deja meter ID
					coche.setId(id);
					
					System.out.println("Introduce la matrícula del coche: ");
					String matricula = sc.nextLine();
					coche.setMatricula(matricula);
					
					System.out.println("Introduce la marca del coche: ");
					String marca = sc.nextLine();
					coche.setMarca(marca);
					
					System.out.println("Introduce el modelo del coche: ");
					String modelo = sc.nextLine();
					coche.setModelo(modelo);
					
					System.out.println("Introduce el color del coche: ");
					String color = sc.nextLine();
					coche.setColor(color);
					
					listaCoches.add(coche);
					break;
					
					
				case 2:
					//borrar por ID
					
					
					System.out.println("Introduce el ID del coche que deseas borrar:");
					String borrar = sc.nextLine();
					
					//Buscar el objeto con ese ID por el Array (y borrarlo)
					
					break;
				case 3:
					//consultar por ID;
					
					
					System.out.println("Introduce el ID del coche que deseas buscar:");
					String buscar = sc.nextLine();
					
					
					//Lo mismo que para borrar pero leyendolo
					
					
					break;
				case 4:
					//listado (Como en la lectura)
					
					//MAL, hay que leerlo del array para que los datos del listado incluyan los coches que se han metido antes de cerrar el programa.
					
					/*try (FileInputStream fis = new FileInputStream(file);
							 ObjectInputStream ois = new ObjectInputStream(fis);) {
							
							listaCoches = (ArrayList<Coche>)ois.readObject();
							
							
							System.out.println("Listado de coches:");
							
							for(Coche c : listaCoches){
								System.out.println(c);
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} */
					
					
					break;
				case 5:
					//Terminar
	
					//Meter los datos del array en el .dat
					try (FileOutputStream fos = new FileOutputStream(file);
							 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
							
							oos.writeObject(listaCoches);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					System.out.println("Programa Cerrado.");
				break;
				
			
			}
		}
	}
}