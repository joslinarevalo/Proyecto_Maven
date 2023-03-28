package Models;

public class CategoriaLibros {
    private String idCategoriaLibro;
    private String categoriaLibro;

    public CategoriaLibros() {
    }

    public CategoriaLibros(String idCategoriaLibro, String categoriaLibro) {
        this.idCategoriaLibro = idCategoriaLibro;
    }

    public String getIdCategoriaLibro() {
        return idCategoriaLibro;
    }

    public void setIdCategoriaLibro(String idCategoriaLibro) {
        this.idCategoriaLibro = idCategoriaLibro;
    }

    public String getCategoriaLibro() {
        return categoriaLibro;
    }

    public void setCategoriaLibro(String categoriaLibro) {
        this.categoriaLibro = categoriaLibro;
    }
}

