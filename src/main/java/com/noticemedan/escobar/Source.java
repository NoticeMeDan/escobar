package com.noticemedan.escobar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Source implements MultiCastProcess {
    private int port;
    private String group;

    public Source(int port, String group) {
        this.port = port;
        this.group = group;
    }

    public void run() throws IOException {
        InetAddress group = InetAddress.getByName(this.group);
        MulticastSocket source = new MulticastSocket(this.port);
        source.joinGroup(group);

        Scanner sc = new Scanner(System.in);
        while (true) {
            byte[] msg = sc.nextLine().getBytes();
            source.send(new DatagramPacket(msg, msg.length, group, port));
        }
    }
}
