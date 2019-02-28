package engine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	private DatagramSocket socket;
	private InetAddress serverAddress;
	private int port;

	private int clientID;

	private String lastMessage;

	public Client(String address, int port) {

		try {
			this.serverAddress = InetAddress.getByName(address);
			this.port = port;
			this.socket = new DatagramSocket();
			receive();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void receive() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] rawData = new byte[1024];
					DatagramPacket packet = new DatagramPacket(rawData, rawData.length);
					socket.receive(packet);

					String message = new String(rawData);
					message = message.substring(0, message.indexOf("\\e"));

					if (!parseComand(message)) {
						lastMessage = message;
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();

	}

	public void send(String message) {
		try {
			message += message + "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
			socket.send(packet);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean parseComand(String message) {
		if (message.startsWith("\\cid:")) {
			this.clientID = Integer.parseInt(message.substring(5));
			return true;
		}

		return false;
	}

	public int getClientID() {
		return clientID;
	}

	public String getMessage() {
		String message = lastMessage;
		lastMessage = null;
		return message;
	}
}
