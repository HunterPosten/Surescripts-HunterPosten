import java.io.*;
import java.net.*;

public class Listener
{
	/*
	Hunter Posten
	12/16/2016

	This class listens for any datagramPacket messages on the multicastServer
	and then extracts their payload into HelloGoodbye message.

	public static void main(String args[])
		this is the method that listens and then extracts the payload of datagramPacket.

	*/


	public static void main(String[] args) throws Exception
	{
		byte[] buffer;
		InetAddress group;
		HelloGoodbye message;
		MulticastSocket multicastSocket;
		DatagramPacket packet;
		int port;
		ObjectInputStream objectInputstream;

		group = InetAddress.getByName(Resources.getInstance().getMulticastPortNumber().toString());
		port = 8888;
		multicastSocket = new MulticastSocket(port);
		multicastSocket.joinGroup(group);

		buffer = new byte[1000];
		packet= new DatagramPacket(buffer, buffer.length);
		while(true)
		{
			multicastSocket.receive(packet);
			objectInputstream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
			message = (HelloGoodbye) objectInputstream.readObject();
			objectInputstream.close();
			System.out.println("Message received: " + message.toString() + " from " + packet.getAddress());
		}
	}
}