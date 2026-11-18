# Proyecto Pokémon El despertar del Héroe - Simulador de Combates y Ranking

Aplicación desarrollada en **Java** que simula combates Pokémon por turnos y gestiona un sistema de clasificación persistente mediante tecnologías **XML, DTD, HTML y XSLT**.

---

## Características principales

* **Gestión de Pokémon y Ataques:** Definición modular de criaturas, estadísticas, puntos de vida y movimientos de combate.
* **Catálogo y Equipos:** Selección y configuración de equipos Pokémon para el jugador y la máquina.
* **Sistema de Combate por Turnos:** Mecánicas de ataque, esquivas limitadas, cambio de Pokémon activo, barras de vida y comprobación de victoria/derrota.
* **Sistema de Ranking:** Almacenamiento automático de puntuaciones en formato XML validado con DTD y visualización estructurada en HTML mediante hojas de estilo XSLT y CSS.

---

## Estructura del Proyecto

El código fuente está organizado de forma modular:

### Código Fuente (Java)
* `ProyectoPokemon.java`: Clase principal (`main`) que inicia la partida y coordina el flujo del juego.
* `CombatePokemon.java`: Lógica completa de las batallas por turnos, acciones del jugador y del rival.
* `EquipoPokemon.java`: Gestión de los Pokémon asignados a cada entrenador.
* `CatalogoPokemon.java`: Listado y generación de los Pokémon disponibles en el juego.
* `Pokemon.java`: Clase modelo con los atributos y métodos propios de cada criatura.
* `AtaquePokemon.java`: Clase modelo que define los ataques y su daño.

### Datos y Presentación (XML / Web)
* `RankingPokemon.xml`: Archivo de datos donde se registran los jugadores y sus puntuaciones.
* `RankingPokemon.dtd`: Definición de tipo de documento que valida la estructura del XML.
* `Ranking.xsl`: Hoja de transformación XSLT que ordena los jugadores por puntuación y genera la vista HTML.
* `EstiloRanking.css`: Estilos visuales para la tabla de clasificación.

---

## Requisitos y Ejecución

### Requisitos previos
* **Java Development Kit (JDK)** 17 o superior.
* Un entorno de desarrollo (VS Code, IntelliJ IDEA, Eclipse) o terminal con `javac` y `java` configurados.

### Cómo ejecutar el proyecto
1. Clona este repositorio en tu equipo:
   ```bash
   git clone https://github.com/rmartinezm02/DAM_DESPERTAR_HEROE_MartinezMontero_Raul.git


### Autor
Raúl Martínez Montero - Desarrollo y arquitectura del proyecto.