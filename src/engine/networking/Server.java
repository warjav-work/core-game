package engine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import engine.utils.Debug;

public class Server {
	private DatagramSocket socket;
	private int port;
	private boolean onLine;

	private ArrayList<ClientObject> clients = new ArrayList<>();
	private int clientID = 999;
	private String lastMessage;

	public Server(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
			onLine = true;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void receive() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (onLine) {
					try {
						Debug.Log("SERVER>> Esperando Mensaje");
						byte[] rawData = new byte[1024];
						DatagramPacket packet = new DatagramPacket(rawData, rawData.length);
						socket.receive(packet);

						String message = new String(rawData);
						message = message.substring(0, message.indexOf("\\e"));

						if (!parseComand(message, packet)) {
							lastMessage = message;
						}
						Debug.Log("SERVER>> Mensaje enviado " + message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();

	}

	public void send(String message, String ip, int port) {
		try {
			message += message + "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
			socket.send(packet);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateClients(String message) {
		for (int i = 0; i < clients.size(); i++) {
			ClientObject client = clients.get(i);
			send(message, client.getAddress(), client.getPort());
		}
	}

	public boolean parseComand(String message, DatagramPacket packet) {
		if (message.startsWith("\\c")) {
			// Cliente Conectado al Servidor
			ClientObject client = new ClientObject(packet.getAddress().toString(), packet.getPort(), clientID + 1);
			clients.add(client);
			send("\\cid:" + client.getId(), client.getAddress(), client.getPort());
			clientID++;
			return true;
		} else if (message.startsWith("\\d:")) {
			int id = Integer.parseInt(message.substring(3));
			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getId() == id) {
					clients.remove(clients.get(i));
					return true;
				}
			}
			Debug.LogError("SERVIDOR>> Error, Cliente con ID: " + id + " no encontrado!");
			return true;
		}
		return false;
	}

	public boolean isOnLine() {
		return onLine;
	}

	public ArrayList<ClientObject> getClients() {
		return clients;
	}

	public String getMessage() {
		String message = lastMessage;
		lastMessage = null;
		return message;
	}

	public int getPort() {
		return port;
	}

}
