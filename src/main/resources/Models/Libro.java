package Models;

import java.util.ArrayList;

public class Libro {
    private String codigoLibro;
    private String tituloLibro;

    private Integer existencia;

    private String codigoCategoria;

    private Double precio;

    private Autor codigoAutor;

    private ArrayList<Prestamo> prestamosList;

    public Libro() {
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Autor getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(Autor codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public ArrayList<Prestamo> getPrestamosList() {
        return prestamosList;
    }

    public void setPrestamosList(ArrayList<Prestamo> prestamosList) {
        this.prestamosList = prestamosList;
    }
}
