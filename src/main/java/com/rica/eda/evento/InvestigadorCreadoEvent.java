package com.rica.eda.evento;

public class InvestigadorCreadoEvent extends EventoDeDominio {
    private String correo;
    private String nombre;
    private String institucion;

    public InvestigadorCreadoEvent() {
        super("investigadores-service");
    }

    public InvestigadorCreadoEvent(String correo, String nombre, String institucion) {
        super("investigadores-service");
        this.correo = correo;
        this.nombre = nombre;
        this.institucion = institucion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    @Override
    public String getTipo() {
        return "investigador.creado";
    }
}
