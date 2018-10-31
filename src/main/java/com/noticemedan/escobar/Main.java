package com.noticemedan.escobar;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
	    if (args.length != 1) {
			System.out.println("Arguments: type");
            System.out.println("Types: source, sink");
			return;
		}

		String type = args[0];
        int port = 7007;
        String ip = "230.0.0.0";

		try {
			switch (type) {
				case "source":
					new Source(port, ip);
					break;
				case "sink":
					new Sink(port, ip);
					break;
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
