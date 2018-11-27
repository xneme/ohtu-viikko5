package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return drawAsString();
        }

        if (player1Score < 4 && player2Score < 4) {
            return singleScoreAsString(player1Score) + "-" + singleScoreAsString(player2Score);
        }

        return highScoreAsString();

    }

    private String drawAsString() {
        if (player1Score < 4) {
            return singleScoreAsString(player1Score) + "-All";
        }

        return singleScoreAsString(player1Score);
    }

    private String highScoreAsString() {

        if (player1Score > player2Score) {
            return leadingAsString(player1Name);
        }

        return leadingAsString(player2Name);

    }

    private String leadingAsString(String leadingPlayer) {
        int scoreDifference = player1Score - player2Score;

        if (scoreDifference >= 2 || scoreDifference <= -2) {
            return "Win for " + leadingPlayer;
        }

        return "Advantage " + leadingPlayer;
    }

    private String singleScoreAsString(int score) {
        if (score == 0) {
            return "Love";
        }
        if (score == 1) {
            return "Fifteen";
        }
        if (score == 2) {
            return "Thirty";
        }
        if (score == 3) {
            return "Forty";
        }
        return "Deuce";
    }
}
