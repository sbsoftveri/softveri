/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Administrator;

import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import operation.Operation;
import transfer.ClientRequest;
import transfer.Receiver;
import transfer.Sender;
import transfer.ServerResponse;

/**
 *
 * @author Stefan
 */
public class ClientThread extends Thread {

    private Socket s;
    private Receiver receiver;
    private Sender sender;
    int maxBrojKlijenata;
    ServerThread serverskiSoket;

    public ClientThread(Socket socket,int maxBrojKlijenata,ServerThread serverskiSoket) {

        s = socket;
        receiver = new Receiver(s);
        sender = new Sender(s);
        this.maxBrojKlijenata=maxBrojKlijenata;
        this.serverskiSoket=serverskiSoket;
    }

    @Override
    public void run() {

        try {

            while (!s.isClosed()) {
                ServerResponse sr = new ServerResponse();
                ClientRequest cr = (ClientRequest) receiver.receiveMessage();
                try {

                    switch (cr.getOperation()) {

                        case Operation.LOGIN:
                            Administrator a=ServerController.getInstance().login((Administrator)cr.getParametar());
                            sr.setOdgovor(a);
                            break;
                        case Operation.MAX_MINUS_CURRENTY_LOGGED:
                            int brojTrenutnoUlog=ServerController.getInstance().getTrenutnoUlogovani().size();
                            sr.setOdgovor(maxBrojKlijenata-brojTrenutnoUlog);
                            break;
                        case Operation.SIGN_OUT:
                            ServerController.getInstance().signOutAdministrator((Administrator)cr.getParametar());
                            break;
                        
                      
                    }

                } catch (Exception ex) {
                    sr.setException(ex);
                }
                sender.sendMessage(sr);
            }

        }catch(SocketException se){
            System.out.println(serverskiSoket.getClientThreads().size());
            serverskiSoket.getClientThreads().remove(this);
            System.out.println(serverskiSoket.getClientThreads().size());
        }
        
        
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Socket getS() {
        return s;
    }
    
    
    

}
