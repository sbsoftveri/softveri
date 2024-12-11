/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.Operation;
import transfer.ClientRequest;
import transfer.Receiver;
import transfer.Sender;
import transfer.ServerResponse;

/**
 *
 * @author Stefan
 */
public class ClientController {

    private static ClientController instance;
    private Socket s;
    private Receiver receiver;
    private Sender sender;
    private static Exception e;

    private ClientController() {

        try {

            s = new Socket("localhost", 9005);

        } catch (IOException ex) {

        }

        receiver = new Receiver(s);
        sender = new Sender(s);
    }



    public static ClientController getInstance() {

        if (instance == null) {
            instance = new ClientController();

        }

        return instance;
    }

    public Socket getS() {
        return s;
    }

    public void login(Administrator a) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.LOGIN, a);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();

        }
    }

    public int maxMinusCurrentlyLogged() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.MAX_MINUS_CURRENTY_LOGGED, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public void signOut(Administrator a) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.SIGN_OUT, a);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();

        }
    }

    

}
