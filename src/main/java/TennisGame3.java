
public class TennisGame3 implements TennisGame {

    private int player1Points;
    private int player2Points;
    private String player1Name;
    private String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        return getStateForPoints().getStateScore();
    }

    private State getStateForPoints() {
        if (player1Points < 4 && player2Points < 4 && !(player1Points + player2Points == 6))
            return getStateForEarlyGame();
        if (player1Points == player2Points)
            return getStateForDeuce();
        if ((player1Points - player2Points) * (player1Points - player2Points) == 1)
            return getStateForAdvantage();
        return getStateForWin();
    }

    private String getLeadingPlayerName() {
        return player1Points > player2Points ? player1Name : player2Name;
    }

    private interface State {
        String getStateScore();
    }

    private State getStateForEarlyGame() {
        return () -> {
            String[] pointNames = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            String player1PointName = pointNames[player1Points];
            if (player1Points == player2Points)
                return player1PointName + "-All";
            String player2PointName = pointNames[player2Points];
            return player1PointName + "-" + player2PointName;
        };
    }

    private State getStateForDeuce() {
        return () -> "Deuce";
    }

    private State getStateForAdvantage() {
        return () -> "Advantage " + getLeadingPlayerName();
    }

    private State getStateForWin() {
        return () -> "Win for " + getLeadingPlayerName();
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            this.player1Points += 1;
        else
            this.player2Points += 1;

    }

}
