package com.rica.eda.publicador;

import com.rica.eda.evento.EventoDeDominio;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublicadorEventos {
    private final KafkaTemplate<String, EventoDeDominio> kafkaTemplate;

    public PublicadorEventos(KafkaTemplate<String, EventoDeDominio> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(EventoDeDominio evento) {
        String topico = "eventos." + evento.getTipo();
        kafkaTemplate.send(topico, evento.getId(), evento);
    }
}
