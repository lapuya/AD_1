import java.io.Serializable;
import java.util.Objects;


public class Coche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id, matricula, marca, modelo, color;
	
	//Constructor
	public Coche(String id, String matricula, String marca, String modelo, String color) {
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}
	
	//GETTERS Y SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}
	
	//Métodos equals y hashCode para la comparación de objetos (no pueden coincidir en id ni matricula)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;

        return this.getId().equals(coche.getId()) || this.getMatricula().equals(coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, marca, modelo, color);
    }

}
