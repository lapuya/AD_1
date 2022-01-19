## Acceso a Datos - Actividad 1
Esta actividad consiste en crear un programa sencillo que trabaje con archivos binarios.

### Pre-requisitos
* Eclipse

### Requisitos
*Requerimiento 1*
_Esta práctica consiste en la implementación de un programa Java para la gestión del almacén de coches en un concesionario. Los coches tendrán los siguientes atributos, id, matricula, marca, modelo y color._

_El usuario interactuará con el programa a través del siguiente menú, que servirá como interfaz._
_* Añadir nuevo coche_
_* Borrar coche por id_
_* Consulta coche por id_
_* Listado de coches_
_* Terminar el programa_
_Nada más comenzar la ejecución del programa se debe verificar si existe el fichero coches.dat (fichero que contendrá objetos Coche). Si existe, debes leerlo para llenar una colección de tipo ArrayList con todos los objetos Coche existentes en el fichero. Si no existe el archivo coches.dat, no tendrás nada que hacer por el momento, pero sí debes dejar la colección ArrayList disponible, aunque esté vacía._

_Las opciones del menú 1 a 5 trabajarán sobre la colección de tipo ArrayList para añadir, borrar, consultar o listar, y no sobre el fichero coches.dat._

_Cuando el usuario decida terminar la ejecución del programa pulsando la opción 5, el programa deberá crear el fichero coches.dat, sobrescribiendo el anterior si existiera. Se escribirán en el fichero tantos coches como elementos tenga la colección ArrayList que has creado._

*Requerimiento 2*

_Se añadirá una opción al menú que será “Exportar coches a archivo de texto”, que creará un fichero donde guardará la información de los coches con un formato, por ejemplo, id-matricula-marca-modelo-color._

_*Requerimiento 3*_

_No se permite duplicar el id ni la matricula._



