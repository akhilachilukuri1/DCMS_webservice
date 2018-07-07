package Server;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.util.HashMap;

import Conf.Constants;
import Conf.ServerCenterLocation;

/*
 * DcmsServer class is the class that hosts the webservice of all three servers
 * It creates and initialises the log instances and instantiates the implementation
 * for all three servers, endpoints for all three server are published as 3 webservices
 */
public class DcmsServer {
	static HashMap<String, DcmsServerImpl> locationMap;
	/*
	 * Initiates the necessary log files and folders
	 */
	private static void init() {
		new File(Constants.LOG_DIR + ServerCenterLocation.MTL.toString()).mkdir();
		new File(Constants.LOG_DIR + ServerCenterLocation.LVL.toString()).mkdir();
		new File(Constants.LOG_DIR + ServerCenterLocation.DDO.toString()).mkdir();
		new File(Constants.LOG_DIR + "ServerGlobal").mkdir();
	}
	/*
	 * Server's main method, from where the execution of the server class starts
	 * It starts and initiates the webservices of all three servers
	 * MTL,LVL and DDO
	 */
	public static void main(String args[]) {
		init();
		
		DcmsServerImpl mtlServer = new DcmsServerImpl(ServerCenterLocation.MTL);
		DcmsServerImpl lvlServer = new DcmsServerImpl(ServerCenterLocation.LVL);
		DcmsServerImpl ddoServer = new DcmsServerImpl(ServerCenterLocation.DDO);

		/*
		 * Maps to store the respective server instances of all three locations
		 * to use the instances wherever needed
		 */
		locationMap = new HashMap<>();
		locationMap.put("MTL", mtlServer);
		locationMap.put("LVL", lvlServer);
		locationMap.put("DDO", ddoServer);
		
		Endpoint endpointMtl = Endpoint.publish("http://localhost:3333/MTL", mtlServer);
		Endpoint endpointDdo = Endpoint.publish("http://localhost:4444/DDO", ddoServer);
		Endpoint endpointLvl = Endpoint.publish("http://localhost:5555/LVL", lvlServer);

		if (endpointMtl.isPublished())
			System.out.println("MTL Web Service is published!");
		if (endpointDdo.isPublished())
			System.out.println("DDO Web Service is published!");
		if (endpointLvl.isPublished())
			System.out.println("LVL Web Service is published!");

	}
}
