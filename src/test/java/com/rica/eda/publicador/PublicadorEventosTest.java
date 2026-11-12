package com.rica.eda.publicador;

import com.rica.eda.evento.InvestigadorCreadoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublicadorEventosTest {
    @Mock
    private KafkaTemplate<String, com.rica.eda.evento.EventoDeDominio> kafkaTemplate;

    @InjectMocks
    private PublicadorEventos publicador;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublicarEvento() {
        InvestigadorCreadoEvent evento = new InvestigadorCreadoEvent(
            "juan@uptc.edu.co", "Juan", "UPTC"
        );

        publicador.publicar(evento);

        ArgumentCaptor<String> topicoCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<com.rica.eda.evento.EventoDeDominio> eventoCaptor = 
            ArgumentCaptor.forClass(com.rica.eda.evento.EventoDeDominio.class);

        verify(kafkaTemplate).send(topicoCaptor.capture(), keyCaptor.capture(), eventoCaptor.capture());

        assertEquals("eventos.investigador.creado", topicoCaptor.getValue());
        assertEquals(evento.getId(), keyCaptor.getValue());
        assertEquals(evento, eventoCaptor.getValue());
    }
}
