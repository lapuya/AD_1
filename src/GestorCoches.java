import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCoches {
	
	private ArrayList<Coche> coches = new ArrayList<Coche>();
	
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
		actualizarFichero();

		
	}
	
	//REQUERIMIENTO 2
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
		for(Coche c : coches) 
			System.out.println(c.toString());
		System.out.println("\n");
	}

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
	 * Se ha pensado en no dejar seguir al usuario hasta que introduzca un id o matricula valido
	 * pero el usuario puede no querer seguir con la operacion asi que, si introduce un dato invalido
	 * se cancela directamente y vuelve al menu
	 */
	private boolean añadirCoche() {
		// TODO Auto-generated method stub
		String id, matricula, marca, modelo, color;
		Scanner sc = new Scanner (System.in);
		
		//Posible try-catch
		System.out.println("Introduzca el id del coche:");
		id = sc.nextLine();
		if(repetido(id, "id")) {
			System.out.println("--- ESTE COCHE YA EXISTE EN LA BASE DE DATOS ---\n");
			return false;
		}
		System.out.println("Introduzca la matricula del coche:");
		matricula = sc.nextLine();
		if(repetido(matricula, "matricula")){
			System.out.println("--- ESTE COCHE YA EXISTE EN LA BASE DE DATOS ---\n");
			return false;
		}
		System.out.println("Introduzca la marca del coche: ");
		marca = sc.nextLine();
		System.out.println("Introduzca el modelo del coche: ");
		modelo = sc.nextLine();
		System.out.println("Introduzca el color del coche: ");
		color = sc.nextLine();
		
		Coche c = new Coche (id, matricula, marca, modelo, color);
		coches.add(c);
		
		return true;
	}

	private boolean repetido(String info, String tipo) {
		if (tipo.equals("id")) {
			for(Coche c : coches) {
				if (c.getId().equals(info))
					return true;
			}
		} else if (tipo.equals("matricula")) {
			for (Coche c : coches) {
				if (c.getMatricula().equals(info))
					return true;
			}
		}
		
		return false;
		
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
}
