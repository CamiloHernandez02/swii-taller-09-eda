package com.rica.eda.evento;

public class AltaInvestigadorEvent extends EventoDeDominio {
    private String correo;

    public AltaInvestigadorEvent() {
        super("investigadores-service");
    }

    public AltaInvestigadorEvent(String correo) {
        super("investigadores-service");
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String getTipo() {
        return "investigador.alta";
    }
}
