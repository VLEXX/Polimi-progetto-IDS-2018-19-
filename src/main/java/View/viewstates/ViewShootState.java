package View.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import View.ViewDatabase;

import java.util.Scanner;

public class ViewShootState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        return null;
    }
}
