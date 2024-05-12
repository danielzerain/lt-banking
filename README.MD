
<h1 align="center">Practica Final de Microservicios con Java y Spring Boot</h1>
<p>Todo el proyecto esta desarrollado con Spring Boot 3.2.5 con Java JDK 21</p>


Para poner en funcionamiento debera ingresar a cada proyecto y correr el siguiente comando:

<p align="center"> .\gradlew clean build</p>

O pueden ejecutar el mismo comando en la raiz del proyecto para la compilacion

Una vez compilados todos los servicios, nos situamos en la raiz del proyecto y ejecutamos el siguiente comando

<p align="center">  docker compose up -d --build </p>

Se tienen servicios de health check para que se los servicios que tienen dependencia esperen a que todos los servicios requeridos esten en funcionamiento

Una vez que se levanten todos los proyectos se debera mostrar de la siguiente forma:

[+] Running 10/10
- ✔ Container zipkin             Running                                                                                                                                                  0.0s
- ✔ Container db-mysql           Running                                                                                                                                                  0.0s
- ✔ Container grafana            Running                                                                                                                                                  0.0s
- ✔ Container prometheus         Running                                                                                                                                                  0.0s
- ✔ Container config-service     Healthy                                                                                                                                                  0.0s
- ✔ Container discovery-service  Healthy                                                                                                                                                  0.0s
- ✔ Container account-service    Started                                                                                                                                                  3.6s
- ✔ Container card-service       Started                                                                                                                                                  3.8s
- ✔ Container user-service       Started                                                                                                                                                  3.6s
- ✔ Container gateway-service    Running 


<h2>Servicios</h2>
Se tiene disponible en el directorio postman_collection las peticiones a los servicios

La documentacion se encuentra disponible en los siguientes links:
<p>
Usuarios:
http://localhost:8005/swagger-ui/index.html
</p>
<p>
Cuentas:
http://localhost:8006/swagger-ui/index.html
</p>
<p>
Tarjetas:
http://localhost:8007/swagger-ui/index.html
</p>

<h2>Documentacion</h2>
<p>La documentacion se encuentra en la carpeta documentacion de este mismo repositorio </p>