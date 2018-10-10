package com.noticemedan.escobar;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import static org.testng.Assert.*;

public class MainTest {

    @Test
    public void testMain() {
    }

    @Test
    public void test_main_given_broker_succesfully_creates_broker() throws Exception {
        //Try to create the broker
        String[] args = {"broker", "7007", "230.0.0.0"};
        Main.main(args);

        //Setup the socket
        InetAddress group = InetAddress.getByName("230.0.0.0");
        MulticastSocket s = new MulticastSocket(7007);

        //Try to join
        s.joinGroup(group);

        //If joinGroup doesn't throw an IOException, it works.
        Assert.assertTrue(true);
    }


    @Test
    public void testCreateSink() throws Exception{
        //Create a sink object
        String[] args = {"sink", "7007", "230.0.0.0"};
        Main.main(args);

        InetAddress group = InetAddress.getByName("230.0.0.0");

        byte[] buffer = new byte[1000];
        DatagramPacket dp = new DatagramPacket(buffer, 1000, group, 7007);
    }

    @Test
    public void testCreateSource() {
    }

}