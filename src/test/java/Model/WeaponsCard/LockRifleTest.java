/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;
import Model.Exceptions.PlayerAlreadyAdded;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockRifleTest {

    @Test
    void attack() {
        Position myposition = new Position();
        Player activeplayer = Player.BLUE;
        Position positionToAttack = new Position();
        PlayerBoard playerToAttack = new PlayerBoard();
        playerToAttack.setPlayer(Player.YELLOW);
        Map map1 = new Map();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        map1 = initializeMap1.initializeMap();
        myposition.setCurrentroom(map1.getRoomList().get(0));
        myposition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.getPlayer());
        } catch (PlayerAlreadyAdded a) {
        }
        playerToAttack.setMarksBox(new MarksBox());
        playerToAttack.setDamageBox(new DamageBox());
        LockRifle lockRifle = new LockRifle();
        //Caso corretto
        try {
            playerToAttack = lockRifle.attack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        assertEquals(playerToAttack.getDamageBox().getDamageTot(), 2);
        assertEquals(playerToAttack.getMarksBox().getMyMarksMap().get(Player.BLUE), 1);
        //Caso giocatore colore sbagliato
        try{
            positionToAttack.getCurrentcell().removeInCellPlayer(Player.YELLOW);
            positionToAttack.getCurrentcell().addInCellPlayer(Player.GREEN);
        } catch (PlayerNotFound p2){}
        catch (PlayerAlreadyAdded p3){}
        try {
            playerToAttack = lockRifle.attack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        //Caso posizione sbagliata
        positionToAttack.setCurrentroom(map1.getRoomList().get(3));
        positionToAttack.setCurrentcell(map1.getRoomList().get(3).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(Player.YELLOW);
        } catch (PlayerAlreadyAdded p5){}
        try {
            playerToAttack = lockRifle.attack(myposition, activeplayer, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
    }

    @Test
    void hooking() {
        Position myposition = new Position();
        Player activeplayer = Player.BLUE;
        Position positionToAttack = new Position();
        PlayerBoard playerToAttack = new PlayerBoard();
        playerToAttack.setPlayer(Player.YELLOW);
        Map map1 = new Map();
        InitializeMap1 initializeMap1 = new InitializeMap1();
        map1 = initializeMap1.initializeMap();
        myposition.setCurrentroom(map1.getRoomList().get(0));
        myposition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        try {
            positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.getPlayer());
        } catch (PlayerAlreadyAdded a) {
        }
        playerToAttack.setMarksBox(new MarksBox());
        LockRifle lockRifle = new LockRifle();
        try {
            playerToAttack = lockRifle.hooking(activeplayer, myposition, positionToAttack, playerToAttack);
        } catch (PlayerNotFound p) {
            System.out.println("Player not found");
        } catch (PositionNotFound ps) {
            System.out.println("Position not found");
        }
        assertEquals(playerToAttack.getMarksBox().getMyMarksMap().get(Player.BLUE), 1);
    }

    @Test
    void isFirstUse() {
        LockRifle l = new LockRifle();
        assertEquals(l.isFirstUse(), true);
    }

    @Test
    void getMunitionsToPay() {
        LockRifle l = new LockRifle();
        assertEquals(l.getMunitionsToPay(), Munitions.BLUE);
    }

    @Test
    void getPriceToPayHooking() {
        LockRifle l = new LockRifle();
        assertEquals(l.getPriceToPayHooking(), 1);
    }

    @Test
    void getMunitionsToPayHooking() {
        LockRifle l = new LockRifle();
        assertEquals(l.getMunitionsToPayHooking(), Munitions.RED);
    }

    @Test
    void getPriceToPay() {
        LockRifle l = new LockRifle();
        assertEquals(l.getPriceToPay(), 2);
    }
}
