package com.noticemedan.escobar;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Broker implements MultiCastProcess {

    private int port;
    private String group;

    public Broker(int port, String group) {
        this.port = port;
        this.group = group;
    }

    public void run() throws IOException {
        InetAddress group = InetAddress.getByName(this.group);
        MulticastSocket broker = new MulticastSocket(this.port);
        broker.joinGroup(group);

        while(true); //Keep the broker alive.
    }
}
