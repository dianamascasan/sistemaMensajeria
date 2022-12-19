Implementación de un sistema de mensajería instantánea en P2P a través de Java RMI.
La aplicación cuenta con un servidor central al que se conectarán los clientes para conocer qué clientes "amigos" están conectados. De la misma manera, dichos "amigos" serán notificados de la nueva conexión. En el momento de desconexión de un cliente, todos sus clientes "amigos" serán notificados.
Aunque esta información de clientes conectados será obtenida a través del servidor, la comunicación en los clientes será en modo P2P, es decir, será directa entre cliente y cliente, no pasará por el servidor.

El concepto de clientes "amigos" con los que se podrá chatear se implementará mediante un registro de usuarios, cuyos datos se almacenarán en una base de datos PostgreSQL. Tras este registro, los usuarios podrán buscar a sus "amigos", utilizando su identificador de usuario, con el objetivo de solicitar una amistad. Si este usuario que ha recibido la petición de amistad acepta, se añadirán automáticamente cada uno de los clientes a las respectivas listas de "amigos" para poder chatear.

La implementación cuenta con una interfaz gráfica de usuario para los clientes. A través de esta podrán realizar todo lo planteado: registrarse e iniciar sesión, ver "amigos" conectados, buscar a "amigos" que no tiene en su lista y solicitar y aceptar amistades.
