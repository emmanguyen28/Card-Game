import objectdraw.*;
import java.awt.*;
/**
 * This is a program to implement a card game. This is a class to construct a single 
 * card and define methods to be used with it
 * 
 * @HaNguyen (your name) 
 * @11/14/2017 (a version number or a date)
 */
public class Card
{
    //declare constants
    private static final int CARD_WIDTH = 50;
    private static final int CARD_HEIGHT = 50;
    private static final int WHITE_SPACE = 7;
    private static final int MARGIN = 30;

    //declare instance variables
    private FilledRect card;
    private Text cardSymbol;

    //construct a card
    public Card(char symbol, int row, int col, DrawingCanvas canvas) {
        double left = MARGIN + row*(CARD_WIDTH + WHITE_SPACE);
        double top = MARGIN/2 + col*(CARD_HEIGHT + WHITE_SPACE);
        card = new FilledRect(left, top, CARD_WIDTH, CARD_HEIGHT, canvas);
        card.setColor(Color.WHITE);
        cardSymbol = new Text(symbol, left, top, canvas);
        cardSymbol.setFontSize(30);
        double textLeft = left + CARD_WIDTH/2 - (cardSymbol.getWidth())/2;
        double textTop = top + CARD_HEIGHT/2 - (cardSymbol.getHeight())/2;
        cardSymbol.moveTo(textLeft, textTop);
        cardSymbol.hide();
    }

    //a method to check if a card contains a certain point on the canvas
    public boolean contains(Location point) {
        if (card != null) {
            return card.contains(point);
        } else {
            return false; }
    }

    //a method to get the symbol that the card holds
    public String getSymbol() {
        return cardSymbol.getText();
    }

    //a method to show the symbol that the card holds
    public void showSymbol() {
        cardSymbol.show();
    }

    //a method to hide the symbol that the card holds
    public void hideSymbol() {
        cardSymbol.hide();
    }

    //a method to remove the card from canvas
    public void removeFromCanvas() {
        if (card != null) {
            card.removeFromCanvas();
            cardSymbol.removeFromCanvas();
            card = null;
        }
    }
}
