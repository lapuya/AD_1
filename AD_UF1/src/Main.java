import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner lector;

	public static void main(String[] args) {
		lector = new Scanner(System.in);
		ArrayList<Coche> coches = new ArrayList<Coche>();
		int opc = 0;

		File bd = new File("coches.dat");

		if (bd.exists()) {

			boolean eof = false;

			try (FileInputStream fis = new FileInputStream(bd); ObjectInputStream ois = new ObjectInputStream(fis);) {

				while (!eof) {
					coches.add((Coche) ois.readObject());
				}
				System.out.println("BD de coches leida correctamente.");
			} catch (EOFException e1) {
				eof = true;
			} catch (IOException e2) {
				System.out.println("Error al leer los coches del archivo");
				e2.printStackTrace();
			} catch (ClassNotFoundException e3) {
				System.out.println("La clase Coche no está cargada en memoria");
				e3.printStackTrace();
			}
		}

		while (opc != 6) {
			mostrarMenu();
			try {
				opc = lector.nextInt();
				lector.nextLine();
				switch (opc) {
				case 1:
					nuevoCoche(coches);
					break;
				case 2:
					if (!coches.isEmpty()) {
						borrarCoche(coches);
					} else {
						System.out.println("No hay datos, introduzca algún vehículo");
					}
					break;
				case 3:
					if (!coches.isEmpty()) {
						consultarCoche(coches);
					} else {
						System.out.println("No hay datos, introduzca algún vehículo");
					}
					break;
				case 4:
					if (!coches.isEmpty()) {
						listadoCoches(coches);
					} else {
						System.out.println("No hay datos, introduzca algún vehículo");
					}
					break;
				case 5:
					if (!coches.isEmpty()) {
						exportarCoches(coches);
					} else {
						System.out.println("No hay datos, introduzca algún vehículo");
					}
					break;
				case 6:
					System.out.println("Programa finalizado");
					break;

				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error, debe ser un número");
				lector.nextLine();
			}
		}

		try (FileOutputStream fos = new FileOutputStream(bd); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
						for (Coche coche : coches) {
				oos.writeObject(coche);
			}
			System.out.println("Lista de coches Actualizada");
		} catch (IOException e) {
			
			System.out.println("No se ha podido guardar el fichero");
			System.out.println(e.getMessage());
		}
		lector.close();
	}

	public static void mostrarMenu() {
		System.out.println("------ COCHES MATRICULADOS ------------");
		System.out.println("---------------------------------------");
		System.out.println("1. Añadir nuevo coche");
		System.out.println("2. Borrar coche");
		System.out.println("3. Consulta coche");
		System.out.println("4. Listado de coches");
		System.out.println("5. Exportar coches");
		System.out.println("6. Finalizar programa");
		System.out.println("---------------------------------------");
	}

	public static void nuevoCoche(ArrayList<Coche> coches) {
		
		String id = null, matricula, marca, modelo, color;
		
		System.out.println("Introduce el id:");
		id = lector.nextLine();
		System.out.println("Introduce la matrícula:");
		matricula = lector.nextLine();
		System.out.println("Introduce la marca:");
		marca = lector.nextLine();
		System.out.println("Introduce el modelo:");
		modelo = lector.nextLine();
		System.out.println("Introduce el color:");
		color = lector.nextLine();
		Coche c = new Coche(id, matricula, marca, modelo, color);
		coches.add(c);
		//coches.add(new Coche(id, matricula, marca, modelo, color));
		System.out.println("Coche añadido.");
	}

	public static void borrarCoche(ArrayList<Coche> coches) {
		String tmp;
		System.out.println("Introduce el id del vehículo");
		tmp = lector.nextLine();
	//	System.out.println((coches.remove(new Coche(tmp)) ? "Vehiculo borrado con exito"
	//			: "El id no es válido " + tmp + " intruducida no se encuentra en la BD"));
	}

	public static void consultarCoche(ArrayList<Coche> coches) {
		String tmp;
		System.out.println("Introduce la matrícula del vehículo");
		tmp = lector.nextLine();

	//	System.out.println(
	//			coches.contains(new Coche(tmp)) ? "Vehiculo en la BD: \n " + coches.get(coches.indexOf(new Coche(tmp)))
	//					: "El id " + tmp + " intruducida no se encuentra en la BD");
	}

	public static void listadoCoches(ArrayList<Coche> coches) {
		if (!coches.isEmpty()) {
			for (Coche coche : coches) {
				System.out.println(coche);
			}
		} else {
			System.out.println("No hay datos que mostrar");
		}
	}

	public static void exportarCoches(ArrayList<Coche> coches) {
		try (FileWriter fw = new FileWriter("coches.txt"); BufferedWriter bw = new BufferedWriter(fw);) {
			for (Coche coche : coches) {

				bw.write(coche.toString());

				bw.newLine();
			}
			System.out.println("Listado exportado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}