# Compilador TS--
Compilador de un lenguaje basado en TypeScript.
Desarrollado en Java utilizando la herramienta de ANTLR.

## Descripción
El lenguaje TS-- es una adaptación de funcionalidades básicas del lenguaje TypeScript.

Mediante el uso de ANTLR, se realiza el análisis léxico y sintáctico.

[!NOTE]
> Las estructuras que reconocen el lenguaje se especifican en el archivo `.g4`

El análisis semántico y generación de código se realiza mediante la implementación de Visitors.

La generación de código tambien de realiza mediante la implementación de Visitors y el código generado es código MAPL (lenguaje máquina de la asignatura DLP).
[!NOTE]
> La descripción del lenguaj MAPL se encuentra en el fichero `description_mapl.txt`, en la raíz del proyecto.

## Sintaxis básica reconocida
El lenguaje definido reconoce las siguientes sentencias:
* Asignación
* If-Else
* While (básico sin `break`).
...
