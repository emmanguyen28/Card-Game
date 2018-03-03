import objectdraw.*;
import java.awt.*;
/**
 * This is a program to implement a card game. This is a class to manage the entire
 * collection of cards
 * 
 * @HaNguyen (your name) 
 * @11/14/2017 (a version number or a date)
 */
public class CardCollection
{
    // declare instance variables
    private int index = 0;
    private Card[] cardCollection;

    // construct a card collection
    public CardCollection() {
        int cardNum = 36;
        cardCollection = new Card[cardNum];
    }

    // a method to add a card to the collection
    public void addCard(Card card) {
        for (index = 0; index < cardCollection.length; index++) {
            if (cardCollection[index] == null) {
                cardCollection[index] = card;
                break;
            }
        }
    }

    // a method to find a card at a particular location
    public Card getCardAt(Location point) {
        for (index = 0; index < cardCollection.length; index++) {
            if (cardCollection[index] != null) { 
                if (cardCollection[index].contains(point)) {
                    return cardCollection[index];
                }
            }
        }
        return null;
    }

    // a method to remove a particular card from the collection
    public void removeCard(Card card) {
        for (index = 0; index < cardCollection.length; index++) {
            if (cardCollection[index] == card) {
                cardCollection[index] = null;
            }
        }
    }

    // a method to turn all of the cards face up
    public void turnAllCards() {
        for (index = 0; index < cardCollection.length; index++) {
            if (cardCollection[index] != null) {
                cardCollection[index].showSymbol(); 
            }
        }
    }

    // a method to remove all the cards from the canvas and from the card collection (for extra credit)
    public void RemoveAllCards() {
        for (index = 0; index < cardCollection.length; index++) {
            if (cardCollection[index] != null) {
                cardCollection[index].removeFromCanvas();
                cardCollection[index] = null;
            }
        }
    }
}
