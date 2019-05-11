/**
 * @author Federico Scatà
 */
package Model;

import Model.WeaponsCard.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPacket {
    private Cell cell;
    private Player player;
    private StatesEnum statesEnum;
    private Weapon weapon;
    private Weapon replaceWeapon;
    private ArrayList<PowerUp> paymentPowerUp;

    public DataPacket(){
        this.cell=null;
        this.player=null;
        this.statesEnum=null;
        this.weapon=null;
        this.replaceWeapon=null;
        this.paymentPowerUp = new ArrayList<>();
    }

    /**
     * @return cell
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return
     */
    public StatesEnum getStatesEnum() {
        return statesEnum;
    }

    /**
     * @param statesEnum
     */
    public void setStatesEnum(StatesEnum statesEnum) {
        this.statesEnum = statesEnum;
    }

    /**
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @return weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * @param replaceWeapon
     */
    public void setReplaceWeapon(Weapon replaceWeapon) {
        this.replaceWeapon = replaceWeapon;
    }

    /**
     * @return replaceWeapon
     */
    public Weapon getReplaceWeapon() {
        return replaceWeapon;
    }

    /**
     * @param paymentPowerUp
     */
    public void setPaymentPowerUp(ArrayList<PowerUp> paymentPowerUp) {
        this.paymentPowerUp = paymentPowerUp;
    }

    /**
     * @return paymentPowerUp
     */
    public ArrayList<PowerUp> getPaymentPowerUp() {
        return paymentPowerUp;
    }
}
