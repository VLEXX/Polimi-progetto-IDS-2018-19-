/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

import java.util.HashMap;

/**
 * Class with weapons cost
 */
public class Weapon {

    private HashMap<Munitions, Integer> firstPrice;
    private HashMap<Munitions, Integer> secondPrice;
    private HashMap<Munitions, Integer> thirdPrice;
    private boolean firstUse;
    private Munitions cardColor;

    /**
     * Constructor that initializes the cost of the weapons
     */
    public Weapon(){
        firstPrice = new HashMap<>();
        secondPrice = new HashMap<>();
        thirdPrice = new HashMap<>();
        firstUse = true;
        cardColor = null;
    }

    public HashMap<Munitions, Integer> getFirstPrice() {
        return firstPrice;
    }

    public HashMap<Munitions, Integer> getSecondPrice() {
        return secondPrice;
    }

    public HashMap<Munitions, Integer> getThirdPrice() {
        return thirdPrice;
    }

    public void setFirstPrice(Munitions m1, int p1) {
        firstPrice.put(m1, p1);
    }

    public void setSecondPrice(Munitions m2, int p2) {
        secondPrice.put(m2, p2);
    }

    public void setThirdPrice(Munitions m3, int p3) {
        thirdPrice.put(m3, p3);
    }

    public void setFirstUse(boolean firstUse) {
        this.firstUse = firstUse;
    }

    public boolean getFirstUse() {
        return firstUse;
    }

    public int munitionsChecker(Munitions m) {
        if (this.cardColor == m)
            return 1;
        else
            return 0;
    }

    public void setCardColor(Munitions cardColor) {
        this.cardColor = cardColor;
    }

    public Munitions getCardColor() {
        return cardColor;
    }
}
