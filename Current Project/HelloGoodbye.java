import java.io.*;
import java.net.*;

public abstract class HelloGoodbye implements Serializable, Runnable
{
	transient private MulticastSocket multicastSocket;
	private InetAddress multicastGroup;
	private int multicastPort;
	private String userName;

	public HelloGoodbye(String userName, InetAddress multicastGroup, int multicastPort) throws IOException
	{
		System.out.println("Hello goodbye constructor");
		System.out.println("username = " + userName);
		System.out.println("multiGroup = " + multicastGroup);
		System.out.println("multiport = " + multicastPort);
		if(userName == null)
		{
			throw new IllegalArgumentException("In HelloGoodbye constructor String userName parameter cannot be inputted as null");
		}
/*
		if(!multicastGroup.isReachable(5000))
		{
			throw new IllegalArgumentException("in HelloGoodbye constructor inputed parameter: InetAddress multicastGroup is not reachabled inputted parameter value was " + multicastGroup.toString());
		}
*/
		if(multicastPort < 0 || multicastPort > 65535)
		{
			throw new IllegalArgumentException("in Hello Goodbye constructor inputed multicastPort parameter: " + multicastPort + " is outside of the allowed range of 0 to 65535");
		}

		this.userName = userName;
		this.multicastGroup = multicastGroup;
		this.multicastPort = multicastPort;
		this.multicastSocket = new MulticastSocket(multicastPort);


	}

	public String getUserName()
	{
		return this.userName;
	}

	public void sendMe()throws IOException//sends HelloGoodbye message
	{
		byte[] 					buffer;
		ByteArrayOutputStream 	byteArrayoutputStream;
		ObjectOutputStream 		objectOutputStream;
		DatagramPacket			packet;

		byteArrayoutputStream = new ByteArrayOutputStream();
		objectOutputStream = new ObjectOutputStream(byteArrayoutputStream);

		objectOutputStream.writeObject(this);
		buffer = byteArrayoutputStream.toByteArray();
		objectOutputStream.close();

		packet = new DatagramPacket(buffer, buffer.length, this.multicastGroup, this.multicastPort);
		this.multicastSocket.send(packet);
	}
	public String toString()
	{
		return this.getClass().getName() + " " + this.getUserName();
	}


/*
	public static void main(String[] args) throws Exception
	{
		HelloGoodbye helloGoodbye;
		helloGoodbye = new HelloGoodbye("Rick Harrison",InetAddress.getByName("228.5.6.7"), 8888);
		System.out.println(helloGoodbye.toString());
	}
*/
}//class