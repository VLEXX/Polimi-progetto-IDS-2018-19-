package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.ChartScore;
import model.gamedata.InitializeAllPlay;
import model.map.Map;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Stack;

public class Updater extends UnicastRemoteObject implements UpdaterInterface {
    private InitializeAllPlay allPlay;

    public Updater(InitializeAllPlay i) throws RemoteException{
        this.allPlay=i;
    }


    public synchronized UpdatePacket updateClient(Player player) throws IOException, RemoteException, CloneNotSupportedException {

        Position position = new Position();
        if (allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell() != null) {
            position.setCurrentcell(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell());
            position.setCurrentroom(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentroom());
        }

        StatesEnum state = null;

        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.ACTION)){
            state = StatesEnum.ACTION;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.SHOOT)){
            state = StatesEnum.SHOOT;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.SHOOT_SECOND)){
            state = StatesEnum.SHOOT_SECOND;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.SHOOT_THIRD)){
            state = StatesEnum.SHOOT_THIRD;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.PICK_UP)){
            state = StatesEnum.PICK_UP;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.MOVE)){
            state = StatesEnum.MOVE;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.END)){
            state = StatesEnum.END;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.WAIT)){
            state = StatesEnum.WAIT;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.POWERUP)){
            state = StatesEnum.POWERUP;
        }
        if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.SPAWN)){
            state = StatesEnum.SPAWN;
        }

        Stack<PowerUp> deck = (Stack<PowerUp>) allPlay.getCurrentDeckState().getPowerupdeck().clone();
        ChartScore chartScore = null;
        Map map = null;
        CurrentPlayerState currentPlayerState = null;
        try {
            chartScore = allPlay.getChartScore().deepClone();
            map = allPlay.getStateSelectedMap().getSelectedmap().deepClone();
            currentPlayerState = allPlay.getCurrentPlayerState().get(player).deepClone();
        } catch (ClassNotFoundException e) {}

        boolean endgame = allPlay.isEndgame();

        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, position, state, deck, endgame);

        for(CurrentPlayerState currentPlayerState1: allPlay.getCurrentPlayerState().values()){
            if(currentPlayerState1.getPlayerposition().getCurrentcell()==null){
                updatePacket.addInHashMap(currentPlayerState1.getActiveplayer(), null);
            }
            else {
                updatePacket.addInHashMap(currentPlayerState1.getActiveplayer(), currentPlayerState1.getPlayerposition());
            }
        }

        updatePacket.setSkullArray(allPlay.getSkullArray());
        updatePacket.setSecondSkullArray(allPlay.getSecondSkullArray());

        return updatePacket;
    }
}
