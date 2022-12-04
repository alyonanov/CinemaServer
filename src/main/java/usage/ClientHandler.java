package usage;


import com.fasterxml.jackson.databind.ObjectMapper;
import command.Command;
import command.exception.CommandException;
import command.works.CommandWorks;
import main.Runner;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean continueRunning = true;
        Runner.incrementConnectionsNumber();
        Runner.incrementUsersOnline();
        while (continueRunning) {
            try {
                ClientRequest request = getData();
                String action = request.getCommandName();
                CommandWorks works = CommandWorks.getInstance();
                Command command = works.createCommand(action, request, new ServerResponse());
                try {
                    ServerResponse response = command.execute();
                    sendData(response);
                } catch (CommandException e) {
                    String[] messages = e.getMessage().split(": ");
                    sendData(new ServerResponse(null, true, messages[messages.length - 1]));
                }
            } catch (SocketException e) {
                continueRunning = false;
                Runner.decrementUsersOnline();
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendData(ServerResponse response) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String outputJson = mapper.writeValueAsString(response);
        outputStream.writeObject(outputJson);
    }

    private ClientRequest getData() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        String inputJson = (String) inputStream.readObject();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputJson, ClientRequest.class);
    }
}
