public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;


    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    // Getter
    public String getTitulo() {
        return titulo;
    }

    // Setter
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion > 0) {
            this.anioPublicacion = anioPublicacion;
        } else {
            System.out.println("Año de publicación inválido.");
        }
    }

    public void mostrarInformacion() {
        System.out.println("Hola, el libro es " + titulo + " escrito por " + autor + " en el año " + anioPublicacion);
    }
}
