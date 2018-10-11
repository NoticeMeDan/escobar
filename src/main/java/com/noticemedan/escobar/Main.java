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
		if (args.length != 3) {
			System.out.println("Arguments: type, port, ip");
			return;
		}

		String type = args[0];
		int port = Integer.parseInt(args[1]);
		String group = args[2];

		try {
			switch (type) {
				case "broker":
					new Broker(port, group).run();
					break;
				case "source":
					new Source(port, group).run();
					break;
				case "sink":
					new Sink(port, group).run();
					break;
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
