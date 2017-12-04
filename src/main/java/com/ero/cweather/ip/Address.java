package com.ero.cweather.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Address {
    private static String address;

    private Address() {
        try {
            URL url = new URL("http://checkip.amazonaws.com/");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            address = br.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static String getIPAddress() {
        if (address == null) new Address();
        return address;
    }
}
