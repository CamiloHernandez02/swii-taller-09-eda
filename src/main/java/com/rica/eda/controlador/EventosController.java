package com.rica.eda.controlador;

import com.rica.eda.evento.InvestigadorCreadoEvent;
import com.rica.eda.evento.PublicacionCreladaEvent;
import com.rica.eda.evento.AltaInvestigadorEvent;
import com.rica.eda.publicador.PublicadorEventos;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {
    private final PublicadorEventos publicador;

    public EventosController(PublicadorEventos publicador) {
        this.publicador = publicador;
    }

    @PostMapping("/investigador-creado")
    public void crearInvestigador(
        @RequestParam String correo,
        @RequestParam String nombre,
        @RequestParam String institucion) {
        InvestigadorCreadoEvent evento = new InvestigadorCreadoEvent(correo, nombre, institucion);
        publicador.publicar(evento);
    }

    @PostMapping("/publicacion-creada")
    public void crearPublicacion(
        @RequestParam String id,
        @RequestParam String correoAutor,
        @RequestParam String titulo,
        @RequestParam Integer anio) {
        PublicacionCreladaEvent evento = new PublicacionCreladaEvent(id, correoAutor, titulo, anio);
        publicador.publicar(evento);
    }

    @PostMapping("/alta-investigador")
    public void altaInvestigador(@RequestParam String correo) {
        AltaInvestigadorEvent evento = new AltaInvestigadorEvent(correo);
        publicador.publicar(evento);
    }
}
