package com.rica.eda.suscriptor;

import com.rica.eda.evento.InvestigadorCreadoEvent;
import com.rica.eda.evento.PublicacionCreladaEvent;
import com.rica.eda.evento.AltaInvestigadorEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.rebalance.ConsumerSeekToCurrentErrorHandler;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SuscriptorEventos {
    private static final Logger logger = LoggerFactory.getLogger(SuscriptorEventos.class);

    @KafkaListener(topics = "eventos.investigador.creado", groupId = "eda-group")
    public void alInvestigadorCreado(InvestigadorCreadoEvent evento) {
        logger.info("📬 Evento recibido: Investigador creado - {} ({})", 
            evento.getNombre(), evento.getCorreo());
    }

    @KafkaListener(topics = "eventos.publicacion.creada", groupId = "eda-group")
    public void alPublicacionCreada(PublicacionCreladaEvent evento) {
        logger.info("📬 Evento recibido: Publicación creada - {} por {}", 
            evento.getTitulo(), evento.getCorreoAutor());
    }

    @KafkaListener(topics = "eventos.investigador.alta", groupId = "eda-group")
    public void alAltaInvestigador(AltaInvestigadorEvent evento) {
        logger.info("📬 Evento recibido: Alta investigador - {}", evento.getCorreo());
    }

    // Suscriptor idempotente - guarda el ID del evento procesado
    @KafkaListener(topics = "eventos.investigador.creado", groupId = "eda-group-idempotente")
    public void alInvestigadorCreadoIdempotente(InvestigadorCreadoEvent evento) {
        logger.info("📬 Procesando con idempotencia: {}", evento.getId());
        // En producción, verificar si ya procesamos este evento por ID
    }
}
