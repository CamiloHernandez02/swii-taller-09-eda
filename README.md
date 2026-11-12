# Taller Lección 9: Arquitectura Orientada a Eventos (EDA)

Ingeniería de Software II (UPTC). Evolución de rica-microservicios hacia una arquitectura completamente orientada a eventos con Event Sourcing.

## Objetivo

Implementar flujos de negocio más complejos usando eventos como primer ciudadano: publish-subscribe, event sourcing, y manejo de sagas de negocio.

## Componentes esperados

- Event Store para persistencia de eventos
- Proyecciones múltiples basadas en eventos
- Sagas de negocio orchestradas por eventos
- Dead Letter Queues para reintentos

## Requisitos

- Implementar Event Sourcing en al menos un agregado
- Crear proyecciones derivadas de eventos
- Manejar eventos que abarquen múltiples servicios
- Tolerancia a fallos y reconstrucción desde eventos

## Rama: `taller-eda`
