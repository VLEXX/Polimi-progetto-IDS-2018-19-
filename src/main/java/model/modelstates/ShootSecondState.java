package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

import java.util.HashMap;

public class ShootSecondState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public ShootSecondState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if(dataPacket.isSecondAttack()==false){
            return MessageEnum.OK;
        }
        Weapon weapon;
        if(dataPacket.isSecondAttack()==true) {
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    MessageEnum messageEnum = weapon.secondAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersSecond(), allPlay);
                    if(messageEnum.equals(MessageEnum.OK)){
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                            if(weapon.hasThirdAttack()==true) {
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_THIRD));
                                allPlay.notifyObserver();
                                return messageEnum;
                            }
                            else{
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                                allPlay.notifyObserver();
                                return messageEnum;
                            }
                        }
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                            if(weapon.hasThirdAttack()==true) {
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_SECOND));
                                allPlay.notifyObserver();
                                return messageEnum;
                            }
                            else{
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                                allPlay.notifyObserver();
                                return messageEnum;
                            }
                        }
                    }
                    return messageEnum;
                }
            }
        }
        return MessageEnum.WEAPON_NOT_FOUND;
    }
}
