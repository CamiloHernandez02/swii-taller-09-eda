# Notas de Implementación - Taller Lección 9: EDA

## Arquitectura Orientada a Eventos

Evolución de taller 8: 

1. **Event Sourcing**: Guardar todos los eventos (además de proyecciones)
2. **Proyecciones múltiples**: Mismo evento alimenta varios modelos de lectura
3. **Sagas de negocio**: Transacciones distribuidas coordinadas por eventos

## Componentes

- Event Store (tabla `events`)
- Event Bus (RabbitMQ)
- Proyectores (escuchadores)
- Sagas orchestrator

## Decisiones

1. **Event Sourcing**: Aplicar a al menos 1 agregado (ej: Investigador)
2. **Sagas**: Orquestadas (controlador central) vs. Coreografía (eventos)
3. **Reconstrucción**: Desde Event Store

## Entrega

- Branch: `taller-eda`
- Cambios: sobre `rica-microservicios`
