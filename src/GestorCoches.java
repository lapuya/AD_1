import java.io.File;
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
				case 5:
					actualizarFichero();
					break;
			}
			op = menu();
		}
		
	}

	private void actualizarFichero() {
		// TODO Auto-generated method stub
		
	}

	private void listarCoches() {
		// TODO Auto-generated method stub
		
	}

	private void buscarCoche() {
		// TODO Auto-generated method stub
		
	}

	private void borrarCoche() {
		// TODO Auto-generated method stub
		
	}

	private void añadirCoche() {
		// TODO Auto-generated method stub
		
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

	public void cargarCoches(File fn) {
		// TODO Auto-generated method stub
		
	}

	public void cargarVacia() {
		// TODO Auto-generated method stub
		
	}
}
