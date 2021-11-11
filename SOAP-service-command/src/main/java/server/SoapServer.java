package server;

import service.SOAPclassImpl;

import javax.xml.ws.Endpoint;

public class SoapServer {
    private static final String url = "http://localhost:8081/teamService";

    public static void main(String[] args) {
        Endpoint.publish(url, new SOAPclassImpl());
    }
}

