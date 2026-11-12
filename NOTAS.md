# Notas Taller 9: Event Driven Architecture

## Resumen Ejecutivo
Implementación de arquitectura orientada a eventos con Kafka, permitiendo comunicación asíncrona entre microservicios. El proyecto incluye domain events, event publisher/consumer, event store y casos de uso reales.

## Componentes Principales

### 1. DomainEvent (Evento base)
- Clase abstracta para todos los eventos de dominio
- Propiedades: eventId, occurredAt, eventType, aggregateId
- Serializable para persistencia y transmisión

### 2. Event Types
- **OrderCreatedEvent**: Orden creada en el sistema
- **OrderConfirmedEvent**: Orden confirmada por el cliente
- **OrderShippedEvent**: Orden enviada

### 3. EventPublisher
- Publica eventos a Kafka
- Maneja serialización JSON
- Implementa retry logic automático

### 4. EventStore
- Almacena eventos en memoria (extensible a BD)
- Recuperación de eventos por aggregateId
- Soporte para paginación

### 5. Event Handlers
- OrderEventHandler: Procesa eventos de órdenes
- NotificationEventHandler: Envía notificaciones
- Patrón: @KafkaListener para consumir

### 6. KafkaConfig
- Topics creados automáticamente
- Configuración de producer/consumer
- Health checks

## Flujo de Ejemplo

```
1. OrderService crea Order
2. Emite OrderCreatedEvent via EventPublisher
3. OrderEventHandler escucha y procesa
4. NotificationEventHandler escucha y notifica
5. Todos los servicios tienen visibilidad del evento
```

## Pruebas Incluidas

### EventPublisherTest
- Verifica publicación a Kafka
- Valida generación de eventId
- Mock de KafkaTemplate

### EventStoreTest
- Almacenamiento de eventos
- Recuperación por aggregateId
- Eventual consistency

## Instalación y Ejecución

```bash
# Compilar
mvn clean package

# Tests
mvn test

# Ejecutar (requiere Kafka en localhost:9092)
mvn spring-boot:run
```

## Dependencias Clave
- spring-kafka: Cliente Kafka
- spring-boot-starter-data-jpa: Persistencia
- testcontainers-kafka: Tests con Kafka real
- jackson: Serialización JSON

## Patrones Utilizados

### 1. Domain-Driven Design
- Eventos representan hechos del dominio
- Agregados como OrderCreatedEvent

### 2. Event Sourcing (Partial)
- EventStore almacena estado como eventos
- Reconstrucción desde eventos

### 3. CQRS (Potential)
- Lectura desde cache/proyecciones
- Escritura vía eventos

## Consideraciones de Producción

1. **Persistencia**: Implementar con PostgreSQL/MongoDB
2. **Dead Letter Queues**: Manejar eventos fallidos
3. **Reintento**: Exponential backoff
4. **Idempotencia**: Procesar duplicados sin efecto
5. **Correlation IDs**: Rastrear eventos entre servicios
6. **Versionado**: Evolucionar eventos sin romper compatibilidad
7. **Monitoreo**: Alertas en lag de consumer

## Mejoras Futuras

- [ ] Implementar Saga pattern para transacciones
- [ ] Event versioning y schema evolution
- [ ] Dead letter queues automáticas
- [ ] Circuit breaker para fallidos
- [ ] Proyecciones para lectura rápida
- [ ] Snapshots para eventos antiguos
