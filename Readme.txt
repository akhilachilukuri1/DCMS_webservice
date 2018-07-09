DCMS - Distributed Systems Third Assignment to run the code, follow the below steps

CREATION OF WSDL AND STUBS:

PROCEDURE TO USE WSGEN COMMAND TO GENERATE WSDL :

change the path to the build/classes folder and run the below command. 
WSDL and XSD files are generated in the current working directory.
Paste the WSDL and XSD in the project folder
WSGEN COMMAND:
wsgen -verbose -cp . Server.DcmsServerImpl -wsdl

PROCEDURE TO GENERATE CLIENT SIDE STUBS USING WSIMPORT COMMAND:

change the path to the project directory which has the WSDL file in it.
run the below command.It will generate the ".java" and ".class" files in the ClientStub folder.paste the ".class" files in the build/classes/ClientStub folder
WSIMPORT COMMAND:
wsimport -keep -d . -p ClientStub DcmsService.wsdl

Open DCMS/src/com/Server/DcmsServer:

To host in different server machines replace the localhost with the machine IPs as shown below
Endpoint endpointMtl = Endpoint.publish("http://localhost:3333/MTL", mtlServer);
Endpoint endpointDdo = Endpoint.publish("http://localhost:4444/DDO", ddoServer);
Endpoint endpointLvl = Endpoint.publish("http://localhost:5555/LVL", lvlServer);
For Example
Endpoint endpointMtl = Endpoint.publish("http://132.205.4.62:3333/MTL", mtlServer);
Endpoint endpointDdo = Endpoint.publish("http://132.205.4.63:4444/DDO", ddoServer);
Endpoint endpointLvl = Endpoint.publish("http://132.205.4.64:5555/LVL", lvlServer);

Open DCMS/src/com/Client/ClientImp:

Configure the wsdl IPs in ClientImp file.

Open DCMS/src/Client/DcmsClient.java and run it.
Open DCMS/src/Server/DcmsServer.java and run it.

UDP Server ports configured to 9999,1111 and 7777 starts listening(Configurable in DCMS/src/com/Conf/Constants.java).
Client and Server Logs are stored in DCMS/Logs/ (Configurable in DCMS/src/com/Conf/Constants.java).
Enter the inputs following the instructions on the client side.