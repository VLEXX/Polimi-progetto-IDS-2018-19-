package View;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Client {

    private ClientStrategy clientstrategy;

    public Client(){
        clientstrategy = null;
    }

    public void setClientstrategy(ClientStrategy clientstrategy) {
        this.clientstrategy = clientstrategy;
    }

    public static void main(String[] args){
        System.out.println("'Socket' or 'RMI'?");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Client client = new Client();
        while(true) {
            if (s.equals("socket") || s.equals("Socket") || s.equals("SOCKET")) {
                client.clientstrategy = new ClientWithSocket("127.0.0.1", 5858);
                try {
                    client.clientstrategy.startClient();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            }
            if (s.equals("rmi") || s.equals("RMI")) {
                client.clientstrategy = new ClientWithRMI();
                try {
                    client.clientstrategy.startClient();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("WRONG INPUT");
                System.out.println("Please choose between 'Socket' or 'RMI'...");
                s = in.nextLine();
            }
        }
    }
}
