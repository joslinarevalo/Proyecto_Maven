package Models;

public class Alumno {
    private String carnet;
    private String nombre;
   private String apellido;
    private String direccion;
    private String fechanacimiento;
    private String fechaingreso;
    private String  genero;
    private String  estado;

    public Alumno(String carnet, String nombre, String apellido, String direccion, String fechanacimiento, String fechaingreso, String genero, String estado) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fechanacimiento = fechanacimiento;
        this.fechaingreso = fechaingreso;
        this.genero = genero;
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
