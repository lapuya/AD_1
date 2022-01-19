import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCoches {
	
	private ArrayList<Coche> coches = new ArrayList<Coche>();
	
	public void run() {
		int op;
		
		op = menu();
		
		while (op != 5) {
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
			}
			op = menu();
		}
		actualizarFichero();

		
	}

	private void actualizarFichero() {

		try(FileOutputStream fichero = new FileOutputStream("coches.dat", true);
				DataOutputStream escritor = new DataOutputStream (fichero);){
			for (Coche c : coches) {
				escritor.writeUTF(c.getId());
				escritor.writeUTF(c.getMatricula());
				escritor.writeUTF(c.getMarca());
				escritor.writeUTF(c.getModelo());
				escritor.writeUTF(c.getColor());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	private void listarCoches() {
		
		for(Coche c : coches) {
			System.out.println(c.toString());
			
		}
		
	}

	private void buscarCoche() {
		// TODO Auto-generated method stub
		
	}

	private void borrarCoche() {
		// TODO Auto-generated method stub
		
	}

	private void añadirCoche() {
		// TODO Auto-generated method stub
		String id, matricula, marca, modelo, color;
		Scanner sc = new Scanner (System.in);
		
		//Posible try-catch
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
		coches.add(c);
		
		
	}

	private int menu() {
		// TODO Auto-generated method stub
		int op;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------- GESTOR DE COCHES ----------");
		System.out.println("1. Añadir nuevo coche.");
		System.out.println("2. Borrar coche por id.");
		System.out.println("3. Consulta coche por id.");
		System.out.println("4. Listado de coches.");
		System.out.println("5. Terminar el programa.");
		op = sc.nextInt();
		
		while (op < 1 || op > 5) {
			System.out.println("ERROR EN LA ELECCION. ELIJA UN VALOR ENTRE 1 Y 5");
			System.out.println("1. Añadir nuevo coche.");
			System.out.println("2. Borrar coche por id.");
			System.out.println("3. Consulta coche por id.");
			System.out.println("4. Listado de coches.");
			System.out.println("5. Terminar el programa.");
			
			op = sc.nextInt();

		}

		
		return op;
	}

	public void cargarCoches() {
		File fn = new File("coches.dat");
		if (fn.exists()) { //SE CREA AL FINAL (si se quita da error)
			try(FileInputStream fichero = new FileInputStream(fn);
					DataInputStream lector = new DataInputStream(fichero);){
				boolean eof = false;
				while(!eof) {
					try {
						String id = lector.readUTF();
						String matricula = lector.readUTF();
						String marca = lector.readUTF();
						String modelo = lector.readUTF();
						String color = lector.readUTF();
						Coche c = new Coche(id, matricula, marca, modelo, color);
						coches.add(c);
					} catch (EOFException e1) {
						eof = true; 
					} catch (IOException e2) {
						System.out.println("Ha ocurrido un error inesperado.");
						System.out.println(e2.getMessage());
						break;
					}
				}
			
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return;
			}
		}
		
	}

	public void cargarVacia() {
		// TODO Auto-generated method stub
		
	}
}
