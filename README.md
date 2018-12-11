### Escuela Colombiana de Ingenier�a
### Arquitecturas de Software (ARSW)
### Laboratorio Bases de Datos No-SQL
[![Heroku](https://wmpics.pics/di-D9YP.png)](https://microservices-mongo-market.herokuapp.com/)

Su compa��a lo ha seleccionado para construir una aplicaci�n que demuestre una simple arquitectura de Microservicios desplegada en Heroku con bases de datos NoSql, para uno de los clientes m�s importantes.
As�, han decidido que usted debe construir una aplicaci�n para consultar el mercado de valores de las acciones negociadas en Bolsa.  La aplicaci�n recibir� el identificador de una acci�n, por ejemplo �MSFT� para Microsoft  y deber� mostrar el hist�rico de la valoraci�n intra-d�a, diaria, semanal o mensual. Para esto utilice el API gratuito de https://www.alphavantage.co/documentation (Puede crear una cuenta para obtener la llave para realizar consultas). Se le pide que su implementaci�n sea de alta disponibilidad, as� que usted debe construir una base de datos de respaldo que se cargu� en l�nea y que permita almacenar versiones de las respuestas dadas por el servicio externo. El sistema buscar� primero una respuesta en la base de datos de respaldo y si no tiene respuesta ir� al servicio externo. Utilice mongoDB para esta base de respaldo. Puede desplegar una instancia de MongoDB en mlab.com.
La arquitectura debe tener las siguientes caracter�sticas:
1. El cliente Web debe ser un cliente as�ncrono que use servicios REST desplegados en Heroku y use Json como formato para los mensajes.
2. El servidor de Heroku servir� como un gateway para encapsular llamadas a otros servicios Web externos.
3. La aplicaci�n debe ser multiusuario.
4. Todos los protocolos de comunicaci�n ser�n sobre HTTP.
5. Los formatos de los mensajes de intercambio ser�n siempre JSON.
6. La interfaz gr�fica del cliente debe ser los m�s limpia y agradable posible y debe utilizar Bootstrap. Para invocar m�todos REST desde el cliente usted puede utilizar la tecnolog�a que desee.
7. Debe construir un cliente Java que permita probar las funciones tanto el servidor fachada. El cliente utiliza simples conexiones http para conectarse a los servicios. Este cliente debe hacer pruebas de concurrencia en su servidor Spring.
8. Debe utilizar maven para gestionar el ciclo de vida, git y github para almacenar al c�digo fuente y Heroku como plataforma de producci�n.
9. La base de datos debe ser MongoDB.
10. Documente su arquitectura en el README.md

### Documentaci�n de la arquitectura
![](img/diagram.jpg)

La arquitectura consiste en un REST API usando el framework de Springboot, que mediante anotaciones, hace las conexiones necesarias en esta MVC (model-view-controller) para que al ingresarle un dato a la vista (que usa bootstrap), esta llame a el controlador (el cual es un JavaScript que maneja mensajes JSON implementado en axios) para que se comunique con el modelo quien es que hace la consulta como tal (y el llamado al API externo de AlphaVantage).
Se hace uso de MongoDB para persistir datos No-SQL (los JSON).