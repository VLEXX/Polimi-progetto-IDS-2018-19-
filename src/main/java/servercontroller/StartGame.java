/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.modelstates.ActionState;
import model.modelstates.EndTurnState;
import model.modelstates.SpawnState;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.powerups.PowerUp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private HashMap<StatesEnum, model.modelstates.State> stateHashMap;
    private UpdateThread updateThread;
    private IDClientList idClientList;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo, HashMap<StatesEnum, model.modelstates.State> hashMap, UpdateThread update, IDClientList clientList){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = hashMap;
        this.updateThread = update;
        this.idClientList=clientList;
    }

    public synchronized void run(){

        while(true){
            try {
                if(allPlay.isEndgame()==false) {
                    DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                    MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket);
                    objectOutputStream.writeObject(messageEnum);
                    if(allPlay.getHashMapState().get(player) instanceof EndTurnState){
                        if(allPlay.getCurrentPlayerState().get(player).isEndturn()==true) {
                            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.WAIT));
                            if (idClientList.getIndexArray() < idClientList.getPlayerArrayList().size()-1) {
                                idClientList.increaseIndexArray();
                            }
                            else{
                                idClientList.resetIndexArray();
                            }
                            if (allPlay.getCurrentPlayerState().get(idClientList.getPlayerArrayList().get(idClientList.getIndexArray())).getPlayerposition().getCurrentcell() == null) {
                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.SPAWN));
                            } else {
                                allPlay.getHashMapState().replace(idClientList.getPlayerArrayList().get(idClientList.getIndexArray()), stateHashMap.get(StatesEnum.ACTION));
                            }
                            allPlay.getCurrentPlayerState().get(player).setEndturn(false);
                        }
                    }
                    updateThread.updateClient();
                }
                if(allPlay.isEndgame()==true) {
                    break;
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
        }
    }

    public HashMap<StatesEnum, model.modelstates.State> getStateHashMap() {
        return stateHashMap;
    }
}
