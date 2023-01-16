# pruebaMailKafka

Desarrollar una aplicación JAVA teniendo en cuenta lo siguiente:

1. Crear un productor que vaya almacenando mensajes simulando un gestor de correo.
 * Se ha creado un MessageProducer donde se crea el producer y tambien envia el mensaje, se ha configurado para que envie un mensaje cada 10sg.
 * Tenemos un MyController donde creamos el mensaje que se va a enviar, generando aleatoreamente los valores que se necesitan.
 * Existe un archivo application-docker.properties el cual contiene toda la información de las propiedades del producer.

2. Crear tres consumidores que vayan leyendo mensajes utilizando threads.
 * Se ha creado un MessageConsumer en donde se crea el Consumer y se crea el thread del consumidor.
 * Existe un archivo consumer.properties el cual tiene la configuración de el consumer.
 * Se han creado 3 instancias de MessageConsumer.

3. Cada mensaje debe ser un JSON con la siguiente información:
 * Fecha actual: Fecha en la que el mensaje ha sido producido.
 * Email: xxx@test.com (siendo xxx cualquier alfanumérico generado
aleatoriamente)
 * Mensaje: Hola, tu código de acceso es XXXX. (siendo XXXX cualquier
numérico generado aleatoriamente)

4. Cada vez que un consumidor gestione un mensaje, debe tener en cuenta que no se
vuelva a gestionar (ni por el mismo consumidor ni por ningún otro)
 * Si el consumer se encuentra en el mismo groupid, existe la propiedad enable.auto.commit=true la cual nos ayuda a no leer el mensaje varias veces.
 * Si estan en diferente groupid, tocaria crear otra validación ya que caad grupo consumiria el mensaje.
 
5. Cada vez que el consumidor gestione el mensaje debe guardarse en un log la
siguiente información:
 * Se ha utilizado log4j, por lo cual tambien se ha creado un archivo de propiedades log4j2.properties.
  * Fecha actual: Fecha en la que el mensaje ha sido consumido.
  * Consumidor: El consumidor que ha consumido el mensaje.
  * Email
  * Mensaje
  
  
# Necesario

● Montar todo en un sistema docker para que cuando se descargue el proyecto sea
posible hacer docker-compose up y se levante la app y todo funcione
correctamente.

 * Para ejecutarlo debemos estar en la raiz del proyecto con el siguiente comando docker-compose up.
 * Cuando se hayan creado los contenedores, se puede ver el log tambien de la siguiente manera:
 
  * docker exec -it kafka-broker bash
  * kafka-console-consumer --bootstrap-server kafka-broker:9092 --topic pruebaEmail --from-beginning
  
* El archivo Dockerfile, esta configurado para levantar Java 17

