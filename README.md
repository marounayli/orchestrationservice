## ``Orchestration Service Using Camunda``

The business process management file (BPMN) is stored under src/main/resources under the name dummy2.bpmn

To open and view the file please download the camunda modeler found at https://camunda.com/download/modeler/

Under api responses and api requests packages you will find all the classes needed to communicate with the microservices in term of attributes.
The API orchestration is handled already.

Do not modify the following variables:

PaymentResponse.approved
ShipmentResponse.initiated
StockResponse.available 

These variables are used to manipulate the flow of the process.

In order to be able to run this project you need :

Eclipse/IntelliJ
mysql server running on the local machine to support Camunda's database
The following postman collection contains the request for an order :  https://www.getpostman.com/collections/c47c9fc74aaa68fc8923




