package com.noticemedan.escobar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String object = args[0];
		int port = Integer.parseInt(args[1]);
		String group = args[2];
		if(object.equals("broker")){
			createBroker(port, group);
		}
		else if(object.equals("source")){
			createSource(port, group);
		}
		else if(object.equals("sink")){
			createSink(port, group);
		}
		else {
			System.out.println("Please provide broker, source or sink as argument");
		}
	}

	private static void createSink(int port, String groupName) {
		InetAddress group;
		MulticastSocket sink;
		try {
			group = InetAddress.getByName(groupName);
			sink = new MulticastSocket(port);
			sink.joinGroup(group);
			//ByteBuffer buffer = ByteBuffer.allocate(1024);
			byte[] buf = new byte[1000];
			DatagramPacket dp;
			while(true) {
				dp = new DatagramPacket(buf, buf.length);
				sink.receive(dp);
				byte[] data = dp.getData();
				String msg = new String(data,0, data.length).trim();
				System.out.println(msg);
				buf = new byte[1000];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createSource(int port, String groupName) {
		InetAddress group;
		MulticastSocket source;
		try {
			group = InetAddress.getByName(groupName);
			source = new MulticastSocket(port);
			source.joinGroup(group);

			Scanner sc = new Scanner(System.in);
			byte[] buf = new byte[1000];
			while(true){
				byte[] msg = sc.nextLine().getBytes();
				source.send(new DatagramPacket(msg, msg.length, group, port));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void createBroker(int port, String groupName) {
		InetAddress group;
		MulticastSocket broker;
		try {
			group = InetAddress.getByName(groupName);
			broker = new MulticastSocket(port);
			broker.joinGroup(group);
			while(true); // This is fine :)
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
