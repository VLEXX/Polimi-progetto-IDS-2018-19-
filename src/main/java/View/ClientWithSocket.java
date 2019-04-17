package View;

import Model.DataPacket;
import Model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientWithSocket implements ClientStrategy{
        private String ip;
        private int port;

        public ClientWithSocket(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public void startClient() throws IOException, ClassNotFoundException {

            Socket socket = new Socket(ip, port);
            System.out.println("Connection established");

            ClientManager clientManager = new ClientManager();

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            Scanner stdin = new Scanner(System.in);
            Scanner inMessage = new Scanner(socket.getInputStream());

            clientManager.manageChoice(inMessage, stdin, objectOutputStream, objectInputStream);

            socket.close();
        }
}
