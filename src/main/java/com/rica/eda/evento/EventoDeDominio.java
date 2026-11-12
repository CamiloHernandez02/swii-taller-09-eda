package com.rica.eda.evento;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class EventoDeDominio implements Serializable {
    private String id;
    private LocalDateTime timestamp;
    private String fuente;

    protected EventoDeDominio(String fuente) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.fuente = fuente;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFuente() {
        return fuente;
    }

    public abstract String getTipo();
}
