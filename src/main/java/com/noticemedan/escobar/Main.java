package com.noticemedan.escobar;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {
	public static void main(String[] args) {
		String object = args[0];
		int port = Integer.parseInt(args[1]);
		String group = args[2];

		if(object.equals("broker")){
			createBroker(port, group);
		}
		else if(object.equals("source")){
			createSource();
		}
		else if(object.equals("sink")){
			createSink();
		}
		else {
			System.out.println("Please provide broker, source or sink as argument");
		}
	}

	private static void createSink() {
	}

	private static void createSource() {
	}

	private static void createBroker(int port, String groupName) {
		InetAddress group;
		MulticastSocket broker;
		try {
			group = InetAddress.getByName(groupName);
			broker = new MulticastSocket(port);
			broker.joinGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
