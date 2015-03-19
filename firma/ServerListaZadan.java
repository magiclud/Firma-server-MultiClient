package firma;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListaZadan implements Runnable{

    private int          serverPort   = 8080;
    private ServerSocket serverSocket = null;
    private boolean      isStopped    = false;
    private Thread       runningThread= null;
    private ExecutorService threadPool =
        Executors.newFixedThreadPool(10);

    public ServerListaZadan(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
//            	 PrintStream os = new PrintStream(
// 						clientSocket.getOutputStream());
// 				os.println("Server Lista Zadan teraz pracuje i oczekuje na pracownika/prezesa.");
// 				
                clientSocket = this.serverSocket.accept();
                
//                os.println("Server lista Zadan zaakceptował jakieś połaczenie i oczekuje na kolejnych pracownikow/prezesa.");
//				os.close();
               
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            this.threadPool.execute(
                new Pracownik(clientSocket,
                    "Server Lista zadan"));
        }
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}

