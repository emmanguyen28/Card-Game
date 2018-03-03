import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * This is a program to implement a card game. This class is responsible for defining
 * the begin methods and the event handlers
 * 
 * @HaNguyen (your name) 
 * @11/14/2017 (a version number or a date)
 */
public class Concentration_EC extends WindowController implements ActionListener
{
    //declare constants
    private static final int NUM_OF_CARDS = 36;
    private static final int NUM_OF_ROW = 6;
    private static final int NUM_OF_COL = 6;
    private static final int CANVAS_POSITION = 0;
    private static final int CANVAS_SIZE = 400;

    //declare instance variables
    private JButton cheat;
    private JButton newGame;
    private CardCollection myCollection;
    private Card card;
    private Card firstCard;
    private Card secondCard;

    public void begin() {
        //set color for the canvas
        new FilledRect(CANVAS_POSITION, CANVAS_POSITION, CANVAS_SIZE, CANVAS_SIZE, canvas).setColor(Color.PINK);

        //create the cheat and New Game button
        newGame = new JButton("New game");

        cheat = new JButton("Cheat");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(newGame);
        buttonPanel.add(cheat);

        add(buttonPanel,BorderLayout.NORTH);

        cheat.addActionListener(this);
        newGame.addActionListener(this);

        //create a new card collection
        myCollection = new CardCollection();

        //initialize and shuffle the symbols that the cards hold
        char[] symbolArray = new char[NUM_OF_CARDS];
        ShuffleCard(symbolArray);

        //draw the cards and add the cards to the card collection
        DrawCards(symbolArray);
    }

    // actions to be performed as the user clicks on the buttons
    public void actionPerformed(ActionEvent event) {
        //as the user clicks on the cheat button, all the cards are turned face up
        if (event.getSource() == cheat) {
            myCollection.turnAllCards(); }

        /* as the user clicks on the new game button, the game is reinitialized 
         * with a new set of cards in a different random order */
        else if (event.getSource() == newGame) {
            myCollection.RemoveAllCards();
            myCollection = new CardCollection();
            char[] symbolArray = new char[NUM_OF_CARDS];
            ShuffleCard(symbolArray);
            DrawCards(symbolArray);
        }
    }

    //actions to be performed as the user clicks the mouse
    public void onMouseClick(Location point) {
        //determine which card the user clicked on 
        card = myCollection.getCardAt(point);

        /* set values for a pair of card and hide symbols/remove cards according to
        whether their symbols match or not */
        if (firstCard == null && card != null) {
            firstCard = card;
            firstCard.showSymbol();
        } else if (secondCard == null && card != null) {
            if (card != firstCard) {
                secondCard = card;
                secondCard.showSymbol();
            } 
        } else {
            HideOrRemoveCards();
        }
    }

    // a method to initial values and shuffle symbols that the cards hold
    public void ShuffleCard(char[] symbolArray) {
        //initialize values
        char initialSymbol = '\u03B1';
        for (int i = 0; i < symbolArray.length; i++) {
            symbolArray[i] = initialSymbol;
            if (i % 2 != 0) {
                initialSymbol++;
            }
        }

        //repeatedly swap 2 randomly-chosen array entries
        RandomIntGenerator symbolShuffle = new RandomIntGenerator(0,35);
        for (int j = 0; j < 100; j++) {
            int firstIndex = symbolShuffle.nextValue();
            int secondIndex = symbolShuffle.nextValue();
            char tempValue = symbolArray[firstIndex];
            symbolArray[firstIndex] = symbolArray[secondIndex];
            symbolArray[secondIndex] = tempValue;
        } 
    }

    /* a method to hide symbols/remove cards completely according to whether their symbols
     * match or not */
    public void HideOrRemoveCards() {
        if (firstCard != null && secondCard != null) { 
            if (!firstCard.getSymbol().equals(secondCard.getSymbol())) {
                firstCard.hideSymbol();
                secondCard.hideSymbol();
                firstCard = null;
                secondCard = null; 
            } else {
                firstCard.removeFromCanvas();
                secondCard.removeFromCanvas();
                myCollection.removeCard(firstCard);
                myCollection.removeCard(secondCard);
                firstCard = null;
                secondCard = null; 
            }
        }
    }

    //a method to draw the cards and add them to the card collection 
    public void DrawCards(char[] symbolArray) {
        int row = 0;
        int symbolIndex = 0;
        while (row < NUM_OF_ROW) {
            int col = 0;
            while (col < NUM_OF_COL) {
                card = new Card(symbolArray[symbolIndex], row, col, canvas);
                symbolIndex++;
                myCollection.addCard(card);
                col++;
            }
            row++;
        }     
    }
}

