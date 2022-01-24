## Acceso a Datos - Actividad 1
Esta actividad consiste en crear un programa sencillo que trabaje con archivos binarios.

### Pre-requisitos
* Eclipse

### Requisitos

_*Requerimiento 1*_

_Esta práctica consiste en la implementación de un programa Java para la gestión del almacén de coches en un concesionario. Los coches tendrán los siguientes atributos, id, matricula, marca, modelo y color._

_El usuario interactuará con el programa a través del siguiente menú, que servirá como interfaz: añadir nuevo coche, borrar coche por id, consulta coche por id, listado de coches y terminar el programa_
_Nada más comenzar la ejecución del programa se debe verificar si existe el fichero coches.dat (fichero que contendrá objetos Coche). Si existe, debes leerlo para llenar una colección de tipo ArrayList con todos los objetos Coche existentes en el fichero. Si no existe el archivo coches.dat, no tendrás nada que hacer por el momento, pero sí debes dejar la colección ArrayList disponible, aunque esté vacía._

_Las opciones del menú 1 a 5 trabajarán sobre la colección de tipo ArrayList para añadir, borrar, consultar o listar, y no sobre el fichero coches.dat._

_Cuando el usuario decida terminar la ejecución del programa pulsando la opción 5, el programa deberá crear el fichero coches.dat, sobrescribiendo el anterior si existiera. Se escribirán en el fichero tantos coches como elementos tenga la colección ArrayList que has creado._

*Requerimiento 2*

_Se añadirá una opción al menú que será “Exportar coches a archivo de texto”, que creará un fichero donde guardará la información de los coches con un formato, por ejemplo, id-matricula-marca-modelo-color._

_*Requerimiento 3*_

_No se permite duplicar el id ni la matricula._


### Ejecución
Vamos a empezar a probar el programa. Partiremos de un proyecto sin los ficheros:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/Sin_archivo.png)

Comprobamos que no hay coches:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/SIN_COCHES.png)

Después, daremos de alta un par de coches y consultamos
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/coches_consulta.png)

Probamos a buscar un coche que existe: 
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/coche_encontrado.png)

Ahora, uno que no existe:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/no_coche.png)

*Requerimiento 2*
Exportamos:

![alt text](https://github.com/lapuya/AD_1/blob/master/resources/coche_exportado.png)

Y comprobamos que se ha exportado correctamente:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/coches_txt.png)

*Requerimiento 3*
Vamos a probar que no se pueda añadir por id ni matricula:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/id_repetido.png)
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/matricula_repetida.png)

Para la persistencia, saldremos del programa y comprobaremos que los coches siguen:
![alt text](https://github.com/lapuya/AD_1/blob/master/resources/persistencia.png)
