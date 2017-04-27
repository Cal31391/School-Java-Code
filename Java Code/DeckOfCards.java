

import java.util.Arrays;


public class DeckOfCards {

    public static void main(String[] args) {

        //create beginning card, which will be 17 because rules
        int beginCard = 17;

        //create an array for the deck of cards
        int deckOfCards[] = new int[52];
        int secondCard;

        //use loop to populate array with card values
        for (int i = 0; i < deckOfCards.length; i++) {
            if (i < 9) {
                deckOfCards[i] = i + 2;
            } else if (9 <= i && i < 18) {
                deckOfCards[i] = i - 7;
            } else if (18 <= i && i < 27) {
                deckOfCards[i] = i - 16;
            } else if (27 <= i && i < 36) {
                deckOfCards[i] = i - 25;
            } else if (36 <= i && i < 48) {
                deckOfCards[i] = 10;
            } else if (48 <= i && i < 52) {
                deckOfCards[i] = 1;
            }
        }

        //check to see if you did it right
        //System.out.print(Arrays.toString(deckOfCards));
        //run for 100k iterations
        double loss = 0;
        double win = 0;
        double probablyWin = 0;

        for (int iterator = 0; iterator < 100000; iterator++) {

            //shuffle the deck
            shuffle(deckOfCards);

            //pull random card value for iteration using a method
            secondCard = deckOfCards[pullRandomCard()];

            //add value of second card to beginning card(which is 17)
            int cardTotal = beginCard + secondCard;

            //check
            //System.out.println(secondCard);
            //System.out.println(cardTotal);
            //find out whether win, loss, or probably win(under 21)
            if (cardTotal < 21) {
                probablyWin++;
            } else if (cardTotal == 21) {
                win++;
            } else if (cardTotal > 21) {
                loss++;
            }
        }
        System.out.printf("Likely Victories: " + "%.1f%s\n", probablyWin / 1000, "%");
        System.out.printf("Certain Victories: " + "%.1f%s\n", win / 1000, "%");
        System.out.printf("Defeats: " + "%.1f%s\n", loss / 1000, "%");
    }

    //method to pull random card from deck
    public static int pullRandomCard() {
        int i = (int) (Math.random() * 52);
        return i;
    }

    //method to shuffle deck - http://theoryapp.com/shuffle-array-or-list-in-java/
    public static void shuffle(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int r = i + (int) (Math.random() * (array.length - i));
            int temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }

    }

}
