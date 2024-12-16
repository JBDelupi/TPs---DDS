# TPs---DDS
# Repositorio de Diseño de Sistemas K3002 - Grupo 9

¡Bienvenido al repositorio del Grupo 9 para el curso de Diseño de Sistemas (K3002)!

En este repositorio encontrarás los avances del Trabajo Práctico Anual (TP Anual) y otros proyectos relacionados con el curso. También podrás acceder a los ejercicios y trabajos prácticos grupales realizados a lo largo del año.

## Integrantes del Grupo

| Nombre    | Apellido  |
|-----------|-----------|
| Joaquín   | Coaricona |
| Juan      | Delupí    |
| Tobías    | Durén     |
| Nahuel    | Giménez   |
| Lucas     | Iturrioz  |
| Gianluca  | Petri     |
| Lucas     | Pérez     |


## Descripción del Sistema 📝

El sistema Decco es una plataforma integral diseñada para gestionar la distribución de viandas a personas vulnerables mediante heladeras ubicadas en diferentes lugares. Permite a los colaboradores contribuir con viandas, gestionar heladeras y generar reportes sobre su estado y las contribuciones realizadas.

## Tecnología Utilizada 💻

- **Java**: Lenguaje de programación principal.
- **Javalin**: Framework para construir aplicaciones web.
- **Handlebars**: Motor de plantillas para la generación de vistas.
- **Micrometer**: Biblioteca para la recolección de métricas.
- **RabbitMQ**: Sistema de mensajería para la comunicación entre servicios.
- **DataDog**: Plataforma de monitoreo y análisis de métricas.
- **SonarCloud**: Herramienta para el análisis de calidad del código.

## Arquitectura 🏗️

El sistema sigue el patrón de arquitectura MVC (Modelo-Vista-Controlador):

- **Modelo**: Representa la lógica de negocio y la gestión de datos.
- **Vista**: Se encarga de la presentación de la información al usuario.
- **Controlador**: Maneja la interacción del usuario y actualiza el modelo y la vista.

## Estructura de Carpetas 📂

- `src/main/java/Controller`: Contiene los controladores que manejan las solicitudes HTTP.
- `src/main/java/Models`: Incluye las clases de dominio y repositorios.
- `src/main/java/Service`: Contiene la lógica de negocio y servicios auxiliares.
- `src/main/resources/templates`: Almacena las plantillas Handlebars para las vistas.
- `src/main/resources/css`: Archivos de estilos CSS.
- `src/main/resources/js`: Archivos JavaScript para funcionalidades del cliente.

## Links Importantes 🔗

- **DeccoSalud**: [Swagger API](https://deccosalud.onrender.com/swagger)
- **DeccoHeladera**: [Sitio Web](https://deccoheladera.onrender.com/)
- **DeccoColaboraciones**: [Sitio Web](https://deccocolaboraciones.onrender.com/)
- **DataDoghq Metrics**: [Dashboard](https://p.us5.datadoghq.com/sb/e73aba5a-bba5-11ef-927e-563ef795b767-f8d30395c1d3e281ccf7a523204ff054?refresh_mode=sliding&from_ts=1734362328009&to_ts=1734365928009&live=true)
- **RabbitMQ**: [CloudAMQP](https://customer.cloudamqp.com/)
- **Postman**: [Join Team](https://app.getpostman.com/join-team?invite_code=5a6c56e7f156d563fa30dfeb50670e298b96190b1f1ecb4a4b52465ffd6e39b9&target_code=f8bb5bb2b3252211e0b915c644eb9a6e)
- **SonarCloud**: [Project Summary](https://sonarcloud.io/summary/new_code?id=Lucassiturrioz_TPs---DDS&branch=main)
- **LucidChart**: [Diagramas](https://lucid.app/lucidchart/b621f3db-1924-4aef-a05f-dd1faa61d7ef/edit?invitationId=inv_f57f88f7-211d-4963-ad91-229bb2736901&page=0_0#)
- **GitHub repo**: [Repositorio](https://github.com/Lucassiturrioz/DeccoSalud/tree/main)