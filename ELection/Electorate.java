public class Electorate implements Runnable {
    private String vote;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // constructor and other methods will go here
}
public Electorate(Socket socket, String vote) {
    this.socket = socket;
    this.vote = vote;
    try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void run() {
    try {
        // send vote
        out.println(vote);

        // receive votes
        int votesForA = 0, votesForB = 0;
        for (int i = 0; i < 4; i++) { // 4 other electorates
            String receivedVote = in.readLine();
            if (receivedVote.equals("A")) votesForA++;
            else if (receivedVote.equals("B")) votesForB++;
        }

        // determine and display the winner
        if (votesForA > votesForB) System.out.println("A is the winner");
        else if (votesForB > votesForA) System.out.println("B is the winner");
        else System.out.println("It's a tie");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

