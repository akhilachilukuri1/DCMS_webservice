package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Conf.ServerCenterLocation;
import Models.Record;
import Models.Student;
import Models.Teacher;

public class UDPRequestServer extends Thread {
	DatagramSocket serverSocket;
	ServerCenterLocation location;
	private DatagramPacket receivePacket;
	private DcmsServerImpl server;
	private Logger loggerInstance;

	/**
	 * UDPRequestServer forwards the request received to the respective server port
	 * 
	 * @param pkt
	 *            is the datagram packet
	 * @param serverImp
	 *            is the server Impl object
	 */

	public UDPRequestServer(DatagramPacket pkt, DcmsServerImpl serverImp) {
		receivePacket = pkt;
		server = serverImp;
		try {
			serverSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Forwards the request made by the UDP Server
	 */

	@Override
	public void run() {
		byte[] responseData;
		try {
			String inputPkt = new String(receivePacket.getData()).trim();
			String[] pktSplit = new String[2];
			if (inputPkt.contains("#")) {
				pktSplit = inputPkt.split("#");
				inputPkt = pktSplit[0];
			}
			switch (inputPkt) {
			case "TRANSFER_RECORD":
				System.out.println("Transferring :: " + pktSplit[1]);
				responseData = transferRecord(pktSplit[1]).getBytes();
				serverSocket.send(new DatagramPacket(responseData,
						responseData.length, receivePacket.getAddress(),
						receivePacket.getPort()));
				break;
			case "GET_RECORD_COUNT":
				responseData = Integer.toString(getRecCount()).getBytes();
				serverSocket.send(new DatagramPacket(responseData,
						responseData.length, receivePacket.getAddress(),
						receivePacket.getPort()));
				break;
			default:
				System.out.println("Invalid UDP request type");
			}

			loggerInstance.log(Level.INFO,
					"Received " + inputPkt + " from " + location);
		} catch (Exception e) {
			// System.out.println(
			// "Exception in UDP Request server Thread :: " + e.getMessage());
		}
	}
	/**
	 * Private method which actually performs the transfer record functionality
	 * @param recordToBeAdded - record to be added to the new location's hashmap
	 * @return returns a success or failure message
	 */

	private String transferRecord(String recordToBeAdded) {
		String temp[] = recordToBeAdded.split(",");
		String managerID = temp[0];
		String recordID = temp[1];
		if (recordID.contains("TR")) {
			String firstName = temp[2];
			String lastName = temp[3];
			String address = temp[4];
			String phone = temp[5];
			String specialization = temp[6];
			String location = temp[7];
			String key = lastName.substring(0, 1);
			Teacher teacherObj = new Teacher(managerID, recordID, firstName,
					lastName, address, phone, specialization, location);
			String message = server.addRecordToHashMap(key, teacherObj, null);
			// System.out.println(message + " " + server.recordsMap.get(key));
			return message + " " + server.recordsMap.get(key);
		} else {
			String firstName = temp[2];
			String lastName = temp[3];
			String CoursesRegistered = temp[4];
			List<String> courseList = server.putCoursesinList(CoursesRegistered);
			String status = temp[3];
			String statusDate = temp[5];
			Student studentObj = new Student(managerID, recordID, firstName,
					lastName, courseList, status, statusDate);
			String key = lastName.substring(0, 1);
			String message = server.addRecordToHashMap(key, null, studentObj);
			// System.out.println(message + " " + server.recordsMap.get(key));
			return message + " " + server.recordsMap.get(key);
		}
	}

	/**
	 * Returns the number of entires made in the hasahmap
	 *
	 */

	private int getRecCount() {
		int count = 0;
		for (Map.Entry<String, List<Record>> entry : server.recordsMap.entrySet()) {
			List<Record> list = entry.getValue();
			count += list.size();
		}
		return count;
	}
}
