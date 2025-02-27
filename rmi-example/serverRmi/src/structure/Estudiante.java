package structure;

import java.io.Serializable;

public class Estudiante implements Serializable {
    private String nombre;
    private String codigo;
    public Estudiante(){}

    public Estudiante(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
