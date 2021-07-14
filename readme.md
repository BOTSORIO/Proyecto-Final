# Proyecto Final de Programacion Avanzada
Este proyeto representa el codigo que desarrollamos para dar solucion al proyecto final del espacio academico Programacion Avanzada.

## Los integrantes del grupo son:

Sebastian Quintero Osorio 
Braian Camilo Piedrahita 
Melissa Ortiz Perez
Fernando José Murcia

uniLocal es una plataforma que incentiva el comercio y la gastronomía regional, donde se busca apoyar a los pequeños comerciantes. En dicha plataforma los usuarios podrán buscar lugares como: restaurantes, cafeterías, comidas rápidas, museos y hoteles que sean de interés y que se encuentren a una distancia razonable del usuario. Para esto, se debe hacer uso de un mapa en donde se deben mostrar todos los lugares y se debe contar con un buscador para poder filtrar lugares según lo que el usuario esté buscando. Cada lugar debe tener imágenes (al menos una), una descripción, un nombre, horario, teléfonos, categoría, comentarios y calificaciones. Solo los usuarios registrados pueden comentar y calificar lugares, del usuario se requiere su nombre, nickname, email, contraseña y ciudad de residencia para crear una cuenta.

Los lugares que aparecen en la plataforma son creados por los mismos usuarios, un usuario debe tener una cuenta activa para poder crear un lugar, al momento de crear el lugar se debe mostrar un mapa para que el usuario lo ubique. La plataforma debe contar con uno o varios moderadores que se encarguen de verificar que los lugares publicados sean reales y que no incumplan las normas de uso.

Una funcionalidad importante que debe tener la plataforma es que debe mostrar la ruta entre el usuario y el lugar al que quiera ir, se debe mostrar además, el tiempo aproximado que se demora para llegar al lugar y la distancia en kilómetros.

## Funcionalidades por rol:

NOTA: Todos los roles deben poder iniciar sesión desde la misma plataforma.

### Administrador

Crear moderadores y eliminarlos.

Obtener al menos tres reportes definidos por cada grupo de trabajo. Estos reportes deben crearse por medio de gráficos (barras, pastel, etc).

### Moderador

Autorizar o denegar lugares.

Debe quedar registro de qué lugar fue autorizado o denegado por un moderador.

La pantalla inicial del moderador debe tener una lista de lugares que están pendientes de ser autorizados y los lugares que han sido autorizados por él.

### Usuario

Registrarse.

Recuperar contraseña por email.

Ver en un mapa todos los lugares que han sido publicados. Los lugares deben aparecer en función de la ubicación del usuario.

Crear lugares.

Comentar y calificar por medio de estrellas los lugares.

Guardar lugares como favoritos.

Buscar lugares por nombre, tipo, distancia.

Solicitar ruta entre su ubicación y la ubicación del lugar. Esta ruta debe mostrar distancia y tiempo de viaje.

Si el usuario ha creado lugares, debe tener una página donde pueda ver la lista de lugares que ha creado y responder los comentarios que le han hecho.

## Para tener en cuenta: 

Para la parte de los mapas se recomienda usar mapbox (https://www.mapbox.com/). En futuras clases les explicaré cómo implementarlo en el proyecto.

Se debe mostrar si el lugar está abierto o no en función de la hora actual y del horario definido para dicho lugar.

El proyecto debe estar en un repositorio ya sea gitlab o github. Todos los integrantes del grupo deben contribuir en el desarrollo del proyecto. 

Cuando un cliente hace un comentario en un lugar, se debe enviar un email (al usuario que creó el lugar) con lo que escribió la persona. De igual forma debe pasar cuando le respondan un comentario al usuario.

Crear una API REST propia que tenga un formato JSON que permita obtener cierta información y ejecutar algunas acciones que más adelante serán reveladas. 

Se debe poder compartir lugares en Facebook y twitter.


## Diagrama Entidad-Relación
