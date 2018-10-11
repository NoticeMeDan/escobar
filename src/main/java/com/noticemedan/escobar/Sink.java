package com.noticemedan.escobar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sink implements MultiCastProcess {

    private int port;
    private String group;

    public Sink(int port, String group){
        this.port = port;
        this.group = group;
    }

    public void run() throws IOException {
        InetAddress group = InetAddress.getByName(this.group);
        MulticastSocket sink = new MulticastSocket(port);
        sink.joinGroup(group);

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
    }
}
