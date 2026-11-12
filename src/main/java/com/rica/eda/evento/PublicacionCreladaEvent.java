package com.rica.eda.evento;

public class PublicacionCreladaEvent extends EventoDeDominio {
    private String id;
    private String correoAutor;
    private String titulo;
    private Integer anio;

    public PublicacionCreladaEvent() {
        super("publicaciones-service");
    }

    public PublicacionCreladaEvent(String id, String correoAutor, String titulo, Integer anio) {
        super("publicaciones-service");
        this.id = id;
        this.correoAutor = correoAutor;
        this.titulo = titulo;
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public String getCorreoAutor() {
        return correoAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    @Override
    public String getTipo() {
        return "publicacion.creada";
    }
}
