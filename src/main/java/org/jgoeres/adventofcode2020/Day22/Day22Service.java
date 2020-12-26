package org.jgoeres.adventofcode2020.Day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

import static org.jgoeres.adventofcode2020.common.Constants.EMPTY;

public class Day22Service {
    private final String DEFAULT_INPUTS_PATH = "data/day22/input.txt";

    private String inputFile;

    public Day22Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day22Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    LinkedList<Integer> p1Deck = new LinkedList<>();
    LinkedList<Integer> p2Deck = new LinkedList<>();


    public long doPartA() {
        long result = 0;
        /**
         * the game consists of a series of rounds: both players draw their top card,
         * and the player with the higher-valued card wins the round. The winner keeps
         * both cards, placing them on the bottom of their own deck so that the winner's
         * card is above the other card. If this causes a player to have all of the
         * cards, they win, and the game ends.
         *
         * Once the game ends, you can calculate the winning player's score.
         * The bottom card in their deck is worth the value of the card multiplied by 1,
         * the second-from-the-bottom card is worth the value of the card multiplied by 2,
         * and so on.
         *
         * What is the winning player's score?
         **/

        while (!playRound()) ;  // play until we stop

        // We're done! Figure out who won
        Queue<Integer> winnerDeck = (!p1Deck.isEmpty() ? p1Deck : p2Deck);
        result = countScore(winnerDeck);
        return result;
    }

    public long doPartB() {
        long result = 0;
        /**
         * Recursive Combat:
         *
         *
         * **/

        Game game = new Game(0, p1Deck, p2Deck);
        game.playGame();
        // We're done! Figure out who won
        Queue<Integer> winnerDeck = (!p1Deck.isEmpty() ? p1Deck : p2Deck);
        result = countScore(winnerDeck);
        return result;
    }

    private boolean playRound() {
        /**
         * Play a single round of the game. Both players draw their top card,
         * and the player with the higher-valued card wins the round. The winner keeps
         * both cards, placing them on the bottom of their own deck so that the winner's
         * card is above the other card. If this causes a player to have all of the
         * cards, they win, and the game ends.
         **/
        // Both players draw their top card
        Integer p1Card = p1Deck.poll();
        Integer p2Card = p2Deck.poll();

        // Compare the cards to find the winning deck
        boolean p1Win = p1Card > p2Card;
        if (p1Win) {
            // Player 1 wins the round
            p1Deck.add(p1Card); // P1's card on top
            p1Deck.add(p2Card);
        } else {
            // Player 2 wins the round
            p2Deck.add(p2Card); // P2's card on top
            p2Deck.add(p1Card);
        }
        // Game ends when someone's queue is empty
        boolean done = p1Deck.isEmpty() || p2Deck.isEmpty();
        return done;
    }
    private long countScore(Queue<Integer> deck) {
        /**
         * The bottom card in their deck is worth the value of the card multiplied by 1,
         * the second-from-the-bottom card is worth the value of the card multiplied by 2,
         * and so on.
         */
        long count = 0;
        int factor = deck.size();
        while (!deck.isEmpty()) {
            // Pull the top card
            Integer card = deck.poll();
            long cardScore = card * factor;
            factor--;
            count += cardScore;
        }
        return count;
    }

    public void reset() {
        // Reset the decks to the initial configuration
        p1Deck.clear();
        p2Deck.clear();
        loadInputs(inputFile);
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputFile = pathToFile;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            /**
             * Example:
             *  Player 1:
             *  9
             *  2
             *  6
             *  3
             *  1
             *
             *  Player 2:
             *  5
             *  8
             *  4
             *  7
             *  10
             **/
            // Start with player 1
            Queue<Integer> deck = p1Deck;
            while ((line = br.readLine()) != null) {
                // process the line.
                if (line.contains("Player")) continue;  // Skip headings
                else if (line.equals(EMPTY)) deck = p2Deck;  // Switch to P2 when we hit the blank line
                else {
                    // Add the card to the current deck
                    Integer card = Integer.parseInt(line);
                    deck.add(card);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
