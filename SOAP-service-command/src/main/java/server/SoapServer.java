package server;

import service.SOAPclassComand;

import javax.xml.ws.Endpoint;

public class SoapServer {
    private static final String url = "http://localhost:8080/soap";
    public static void main(String[] args) {
        Endpoint.publish(url, new SOAPclassComand());
    }
}

