import java.io.File;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorCoches gestor = new GestorCoches();

		File fn = new File("coches.dat");
		if (fn.exists()) 
			gestor.cargarCoches(fn);
		gestor.run();
	}

}
