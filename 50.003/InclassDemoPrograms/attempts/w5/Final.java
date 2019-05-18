package w5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Final {

    private static volatile boolean found = false;
    private static final int NUM_CLIENTS = 10;
    private static String hostIP = "localhost";
    private static int portNumber = 4321;


    private static List<PrintWriter> outChannels = new ArrayList<>();
    private static List<ClientSocket> clientSocketList = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        //http://en.wikipedia.org/wiki/Fermat_number
        BigInteger n = new BigInteger("4294967297");
//        BigInteger n = new BigInteger("239839672845043");
//		BigInteger n = new BigInteger("1127451830576035879");
		/*BigInteger n = new BigInteger("160731047637009729259688920385507056726966793490579598495689711866432421212774967" +
		"02989534032719790175609601429913262345458317707205045275551070134067328238564789969" +
		"4083881316194642417451570483466327782135730575564856185546487053034404560063433614723836456790266457438831626375556854133866958349817172727462462516466898479574402841" +
		"07170390913806245656762456578425410156837840724227320766089203686970819068803335160" +
		"15394016215765079648415972059527224877506709045229323287315306407064573821626447385" +
		"38813247139315456213401586618820517823576427094125197001270350087878270889717445401" +
		"14579223167409894841688886825014359202697385397378512021707795176654693957752089724" +
		"53921865472795724941776802915065785089627079348791249148808855007264396250330219367" +
		"28949277390185399024276547035995915648938170415663757378637207011391538009596833354" +
		"10773715627303749472785830202866336629694392500864734876927203553226504804970982727" +
		"51793812528986759655285106192583767791710305564828845357288129162166254301870395336" +
		"68677528079544176897647303445153643525354817413650848544778690688201005274443717680" +
		"593899");*/

        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("(... expecting connection ...)");

        //create 10 clients
        for (int i = 0; i < NUM_CLIENTS; i++) {
            ClientSocket client = new ClientSocket(hostIP, portNumber,i);
            clientSocketList.add(client);
            Socket newSocket = serverSocket.accept();
            System.out.println("(... connection " + i + " established ...)");
            outChannels.add(new PrintWriter(newSocket.getOutputStream(), true));
        }

        System.out.println("Starting all clients");

        for (int i = 0; i < NUM_CLIENTS; i++) {
            outChannels.get(i).println(n.toString());
            outChannels.get(i).println(i + 2);
            outChannels.get(i).println(clientSocketList.size());
            outChannels.get(i).flush();
            clientSocketList.get(i).start();
        }

        System.out.println("Waiting for the result");
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BigInteger result = new BigInteger(in.readLine());


        while (!found){     // wait for other threads to find, do nothing. Busy Waiting
        }


        // interrupt all other threads
        for (int i = 0; i < clientSocketList.size(); i++) {
            if (clientSocketList.get(i).getResult() == null) {
                clientSocketList.get(i).interrupt();
            }
        }

        BigInteger theother = n.divide(result);
        System.out.println("Factors are: " + result + ", " + theother);

        serverSocket.close();
    }

    static class ClientSocket extends Thread {

        private int threadId;
        private String hostIP;
        private int portNumber;
        public Socket echoSocket;
        private SocketAddress socketAddress;
        private BigInteger result = null;


        ClientSocket(String hostIP, int portNumber, int threadId) throws IOException {
            this.hostIP = hostIP;
            this.threadId = threadId;
            this.portNumber = portNumber;
            this.echoSocket = new Socket();
            this.socketAddress = new InetSocketAddress(hostIP, portNumber);
            echoSocket.connect(socketAddress, 100);

        }

        public void run() {
            try {
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BigInteger n = new BigInteger(in.readLine());
                BigInteger init = new BigInteger(in.readLine());
                in.close();
                echoSocket.close();

//                System.out.println(this.threadId + " has started factoring");
                BigInteger one = factor(n, init);

                echoSocket = new Socket(hostIP, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                out.println(one.toString());
                out.flush();
                echoSocket.close();
                out.close();
            } catch (Exception e) {
                System.out.println("Thread " + threadId + " exiting");
            }

        }

        public BigInteger factor(BigInteger n, BigInteger init) {
            BigInteger zero = new BigInteger("0");

            while (init.compareTo(n) < 0) {
                if (isInterrupted()) {
                    break;
                }
                if (n.remainder(init).compareTo(zero) == 0) {
                    System.out.println("Thread " + threadId +" found factor " + init);
                    found = true;
                    result = init;
                    return init;
                }
                // stepSize = NUM_CLIENTS (=10)
                init = init.add(new BigInteger(String.valueOf(NUM_CLIENTS)));
            }

            assert (false);
            return null;
        }

        public BigInteger getResult() {
            return result;
        }




    }
}
