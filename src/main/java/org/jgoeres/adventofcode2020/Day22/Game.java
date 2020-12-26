package org.jgoeres.adventofcode2020.Day22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.jgoeres.adventofcode2020.Day22.Game.Player.PLAYER_1;
import static org.jgoeres.adventofcode2020.Day22.Game.Player.PLAYER_2;

public class Game {
    LinkedList<Integer> p1Deck;
    LinkedList<Integer> p2Deck;
    HashSet<String> history = new HashSet<>();

    static boolean DEBUG = false;

    int gameNum;
    int roundNum = 1;

    enum Player {
        PLAYER_1,
        PLAYER_2
    }

    public Game(int gameNum, LinkedList<Integer> p1Deck, LinkedList<Integer> p2Deck) {
        this.gameNum = (gameNum == 0 ? 1 : gameNum);    // Force gameNum to start at 1
        this.p1Deck = p1Deck;
        this.p2Deck = p2Deck;

        debugPrint("=== Game " + gameNum + " ===");
    }

    public Player playGame() {
        // Play out the whole game, and return the winning player
        Player winner;
        while ((winner = playRound()) == null) ;  // Play rounds until one returns a game winner
        return winner;
    }

    private Player playRound() {
        Player winner = null;   // null -> no game winner yet, keep going

        debugPrint("\n-- Round " + roundNum + " (Game " + gameNum + ") --");
        debugPrint("Player 1's deck: " + p1Deck.toString());
        debugPrint("Player 2's deck: " + p2Deck.toString());

        // Before either player deals a card, if there was a previous round in
        // this game that had exactly the same cards in the same order in the
        // same players' decks, the game instantly ends in a win for player 1.
        // Previous rounds from other games are not considered.
        // (This prevents infinite games of Recursive Combat,
        // which everyone agrees is a bad idea.)
        String state = getGameStateString();
        if (history.contains(state)) {
            // We've seen this state before! Player 1 wins!
            return PLAYER_1;
        } else {
            // New state, add it to the history
            history.add(state);
        }

        // Otherwise, this round's cards must be in a new configuration;
        // the players begin the round by each drawing the top card of
        // their deck as normal.
        Integer p1Card = p1Deck.poll();
        Integer p2Card = p2Deck.poll();

        debugPrint("Player 1 plays: " + p1Card);
        debugPrint("Player 2 plays: " + p2Card);

        // If both players have at least as many cards remaining in their deck
        // as the value of the card they just drew, the winner of the round is
        // determined by playing a new game of Recursive Combat.
        boolean p1Win;
        if (p1Deck.size() >= p1Card
                && p2Deck.size() >= p2Card) {
            // Play a new game of Recursive Combat
            debugPrint("Playing a sub-game to determine the winner...\n");
            Player roundWinner = playRecursiveGame(p1Card, p2Card);
            p1Win = (roundWinner == PLAYER_1);
        } else {
            // Otherwise, at least one player must not have enough cards left in
            // heir deck to recurse; the winner of the round is the player with
            // the higher-value card.
            p1Win = p1Card > p2Card;
        }
        if (p1Win) {
            // Player 1 wins the round
            p1Deck.add(p1Card); // P1's card on top
            p1Deck.add(p2Card);
            debugPrint("Player 1 wins round " + roundNum + " of game " + gameNum + "!");
        } else {
            // Player 2 wins the round
            p2Deck.add(p2Card); // P2's card on top
            p2Deck.add(p1Card);
            debugPrint("Player 2 wins round " + roundNum + " of game " + gameNum + "!");
        }
        roundNum++;
        if (p1Deck.isEmpty() || p2Deck.isEmpty()) {
            winner = (!p1Deck.isEmpty() ? PLAYER_1 : PLAYER_2);
            debugPrint("The winner of game " + gameNum + " is " + winner);
            debugPrint("\n...anyway, back to game " + (gameNum - 1));
        }
        return winner;  // returning 'null' means no winner, the game should continue
    }

    private Player playRecursiveGame(Integer p1Card, Integer p2Card) {
        // To play a sub-game of Recursive Combat, each player creates
        // a new deck by making a COPY of the next cards in their deck
        // (the quantity of cards copied is equal to the number on the
        // card they drew to trigger the sub-game).

        List<Integer> p1List = p1Deck.subList(0, p1Card);
        List<Integer> p2List = p2Deck.subList(0, p2Card);

        LinkedList<Integer> p1subDeck = new LinkedList<>(p1List);
        LinkedList<Integer> p2subDeck = new LinkedList<>(p2List);

        Game recursiveGame = new Game(gameNum + 1, p1subDeck, p2subDeck);
        Player winner = recursiveGame.playGame();
        return winner;
    }

    private String getGameStateString() {
        // Form the game state as
        // <p1's cards CSV>|<p2's cards CSV>
        String gameState = p1Deck.toString()
                + "|" + p2Deck.toString();
        return gameState;
    }

    private void debugPrint(String output) {
        if (DEBUG) {
            System.out.println(output);
        }
    }
}
