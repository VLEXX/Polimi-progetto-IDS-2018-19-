package model;

import model.map.Cell;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.TagbackGrenade;
import model.datapacket.Action;
import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.weaponscard.Thor;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataPacketTest {

    @Test
    void getCell() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void getPlayer() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getPlayer(), null);
    }

    @Test
    void setCell() {
        DataPacket dataPacket = new DataPacket();
        Cell cell = new Cell(1);
        dataPacket.setCell(cell);
        assertEquals(dataPacket.getCell().getCellId(), 1);
    }

    @Test
    void setPlayer() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(dataPacket.getPlayer(), Player.YELLOW);
    }

    @Test
    void getStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void setStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.END);
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MOVE);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.PICK_UP);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.SHOOT);
        dataPacket.setStatesEnum(StatesEnum.MID);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MID);
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.START);
        dataPacket.setStatesEnum(StatesEnum.WAIT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.WAIT);
    }


    @Test
    void setWeapon() {
        DataPacket dataPacket = new DataPacket();
        Weapon weapon = new Thor();
        dataPacket.setWeapon(weapon);
        assertEquals(dataPacket.getWeapon(), weapon);
    }

    @Test
    void getWeapon() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getWeapon(), null);
    }

    @Test
    void setReplaceWeapon() {
        DataPacket dataPacket = new DataPacket();
        Weapon weapon = new Thor();
        dataPacket.setReplaceWeapon(weapon);
        assertEquals(dataPacket.getReplaceWeapon(), weapon);
    }

    @Test
    void getReplaceWeapon() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getReplaceWeapon(), null);
    }

    @Test
    void setPaymentPowerUp() {
        DataPacket dataPacket = new DataPacket();
        ArrayList<PowerUp> arrayList = new ArrayList<>();
        PowerUp powerUp = new TagbackGrenade(Munitions.RED);
        arrayList.add(powerUp);
        dataPacket.setPaymentPowerUp(arrayList);
        assertEquals(dataPacket.getPaymentPowerUp().contains(powerUp), true);
    }

    @Test
    void getPaymentPowerUp() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getPaymentPowerUp().isEmpty(), true);
    }

    @Test
    void setTargetPlayers() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.getTargetPlayersFirst().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersFirst().contains(Player.BLUE), true);
        dataPacket.getTargetPlayersSecond().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersSecond().contains(Player.BLUE), true);
        dataPacket.getTargetPlayersThird().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersThird().contains(Player.BLUE), true);
    }

    @Test
    void setSecondAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setSecondAttack(true);
        assertEquals(dataPacket.isSecondAttack(), true);
    }

    @Test
    void setThirdAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setThirdAttack(true);
        assertEquals(dataPacket.isThirdAttack(), true);
    }

    @Test
    void isSecondAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isSecondAttack(), false);
    }

    @Test
    void isThirdAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isThirdAttack(), false);
    }

    @Test
    void setAction() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setAction(Action.SHOOT);
        assertEquals(dataPacket.getAction(), Action.SHOOT);
    }

    @Test
    void getAction() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getAction(), null);
    }

    @Test
    void setFirstAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setFirstAttack(true);
        assertEquals(dataPacket.isFirstAttack(), true);
    }

    @Test
    void isFirstAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isFirstAttack(), false);
    }

    @Test
    void getWeaponsToBeRecharged() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getWeaponsToBeRecharged().isEmpty(), true);
    }
}