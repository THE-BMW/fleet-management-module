public class Election {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            for (int i = 0; i < 5; i++) { // 5 electorates
                Socket socket = serverSocket.accept();
                String vote = (i % 2 == 0) ? "A" : "B"; // alternate votes for simplicity
                Electorate electorate = new Electorate(socket, vote);
                new Thread(electorate).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}