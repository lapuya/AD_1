import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCoches {
	
	private ArrayList<Coche> coches = new ArrayList<Coche>();
	
	//Metodo run-ejecutar del gestor de coches. Muesta el menú y llama métodos de acuerdo a la opción elegida
	public void run() {
		int op;
		
		op = menu();
		
		while (op != 6) {
			switch (op)
			{
				case 1:
					añadirCoche();
					break;
				case 2:
					borrarCoche();
					break;
				case 3:
					buscarCoche();
					break;
				case 4:
					listarCoches();
					break;
				case 5:
					exportarDatos(); //REQUERIMIENTO 2
					break;
			}
			op = menu();
		}
		//Cuando se sale del programa es cuando se actualiza el fichero .dat
		actualizarFichero();
	}
	
	//REQUERIMIENTO 2: escribir en un ficherto .txt los datos con formato id-matricula-marca-modelo-color
	private void exportarDatos() {
		//Implementado con autoclose
		try(FileWriter fw = new FileWriter("coches.txt");
				BufferedWriter pw = new BufferedWriter(fw);) {
			for (Coche c : coches) {
				pw.write(c.getId());
				pw.write("-");
				pw.write(c.getMatricula());
				pw.write("-");
				pw.write(c.getMarca());
				pw.write("-");
				pw.write(c.getModelo());
				pw.write("-");
				pw.write(c.getColor());
				pw.newLine();
				pw.flush(); //borramos el stream
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("------ COCHES EXPORTADOS A ARCHIVO ------\n");
	}
	
	//Método que escribe en el .dat todo el array de objetos
	private void actualizarFichero() {

		try(FileOutputStream fichero = new FileOutputStream("coches.dat");
				ObjectOutputStream oos = new ObjectOutputStream (fichero);){
			oos.writeObject(coches);
			
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	//Método que muestra todos los coches
	private void listarCoches() {
		for(Coche c : coches) 
			System.out.println(c.toString());
		System.out.println("\n");
	}
	
	//Método para buscar un coche por id
	private void buscarCoche() {
		Scanner sc = new Scanner (System.in);
		String idBuscar;
		boolean encontrado = false;
		
		System.out.println("Introduzca el id del coche a buscar: ");
		idBuscar = sc.nextLine();
		for (Coche c : coches) {
			if (c.getId().equals(idBuscar)) {
				System.out.println("------ COCHE ENCONTRADO ------");
				System.out.println(c.toString());
				encontrado = true;
				break ;
			}
		}
		if (!encontrado)
			System.out.println("------ COCHE NO ENCONTRADO ------\n");
		
	}
	
	//Método para borrar un coche por id
	private void borrarCoche() {
		Scanner sc = new Scanner (System.in);
		String idBorrar;
		boolean encontrado = false;
		
		System.out.println("Introduzca el id del coche a borrar: ");
		idBorrar = sc.nextLine();
		for (Coche c : coches) {
			if (c.getId().equals(idBorrar)) {
				encontrado = true;
				coches.remove(c);
				System.out.println("Coche borrado con éxito.\n");
				break ;
			}
		}
		if(!encontrado)
			System.out.println("------ COCHE A BORRAR NO EXISTE ------\n");
		
	}
	
	/***
	 * Requerimiento 3: no puede repetirse id ni matricula
	 * Pedimos los datos, llamamos a un metodo repetido que buscara en la lista
	 */
	private boolean añadirCoche() {
		// TODO Auto-generated method stub
		String id, matricula, marca, modelo, color;
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Introduzca el id del coche:");
		id = sc.nextLine();
		System.out.println("Introduzca la matricula del coche:");
		matricula = sc.nextLine();
		System.out.println("Introduzca la marca del coche: ");
		marca = sc.nextLine();
		System.out.println("Introduzca el modelo del coche: ");
		modelo = sc.nextLine();
		System.out.println("Introduzca el color del coche: ");
		color = sc.nextLine();
		
		Coche c = new Coche (id, matricula, marca, modelo, color);
		if (repetido(c)) {
			System.out.println("--- ESTE COCHE YA EXISTE EN LA BASE DE DATOS ---\n");
			System.out.println("--- COMPRUEBA ID O MATRICULA ---\n");

		}else
			coches.add(c);
		//coches.add(c);
		
		return true;
	}
	
	//Metodo que busca si un coche ya existe en la lista. Invoca el metodo equals()
	private boolean repetido(Coche coche) {
		for (Coche c : coches) {
			if (c.equals(coche)) {
				return true;
			}
		}
		return false;
	}
	
	//Método menu, que pide al usuario una opción y lo retorna
	private int menu() {
		int op;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------- GESTOR DE COCHES ----------");
		System.out.println("1. Añadir nuevo coche.");
		System.out.println("2. Borrar coche por id.");
		System.out.println("3. Consulta coche por id.");
		System.out.println("4. Listado de coches.");
		System.out.println("5. Exportar coches a archivo de texto.");
		System.out.println("6. Terminar el programa.");
		op = sc.nextInt();
		
		while (op < 1 || op > 6) {
			System.out.println("ERROR EN LA ELECCION. ELIJA UN VALOR ENTRE 1 Y 5");
			System.out.println("1. Añadir nuevo coche.");
			System.out.println("2. Borrar coche por id.");
			System.out.println("3. Consulta coche por id.");
			System.out.println("4. Listado de coches.");
			System.out.println("5. Exportar coches a archivo de texto.");
			System.out.println("6. Terminar el programa.");
			
			op = sc.nextInt();

		}

		
		return op;
	}
	
	//Leemos el fichero .dat si existe. Si no, al final se creará el archivo
	public void cargarCoches() {
		File fn = new File("coches.dat");
		if (fn.exists()) {
			try(FileInputStream fichero = new FileInputStream(fn);
					ObjectInputStream ois = new ObjectInputStream(fichero);){
				
					coches = (ArrayList<Coche>)ois.readObject();
					
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}  catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
		}
		
	}
}
