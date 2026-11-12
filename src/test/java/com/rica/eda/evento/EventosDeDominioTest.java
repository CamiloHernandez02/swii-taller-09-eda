package com.rica.eda.evento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventosDeDominioTest {
    @Test
    public void testInvestigadorCreadoEvent() {
        InvestigadorCreadoEvent evento = new InvestigadorCreadoEvent(
            "juan@uptc.edu.co", "Juan Pérez", "UPTC"
        );

        assertEquals("juan@uptc.edu.co", evento.getCorreo());
        assertEquals("Juan Pérez", evento.getNombre());
        assertEquals("UPTC", evento.getInstitucion());
        assertEquals("investigador.creado", evento.getTipo());
        assertEquals("investigadores-service", evento.getFuente());
        assertNotNull(evento.getId());
        assertNotNull(evento.getTimestamp());
    }

    @Test
    public void testPublicacionCreladaEvent() {
        PublicacionCreladaEvent evento = new PublicacionCreladaEvent(
            "pub-1", "juan@uptc.edu.co", "CQRS Patterns", 2025
        );

        assertEquals("pub-1", evento.getId());
        assertEquals("juan@uptc.edu.co", evento.getCorreoAutor());
        assertEquals("CQRS Patterns", evento.getTitulo());
        assertEquals(2025, evento.getAnio());
        assertEquals("publicacion.creada", evento.getTipo());
        assertEquals("publicaciones-service", evento.getFuente());
    }

    @Test
    public void testAltaInvestigadorEvent() {
        AltaInvestigadorEvent evento = new AltaInvestigadorEvent("juan@uptc.edu.co");

        assertEquals("juan@uptc.edu.co", evento.getCorreo());
        assertEquals("investigador.alta", evento.getTipo());
        assertEquals("investigadores-service", evento.getFuente());
    }
}
