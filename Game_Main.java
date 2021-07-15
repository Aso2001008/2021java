import java.util.*;

import java.util.*;

public class Game_Main{
    public static void main(String[] args){
        Game_game.title();
        Game_ship[] ships = new Game_ship[3];
        ships[0] = new Game_ship("船1");
        ships[1] = new Game_ship("船2");
        ships[2] = new Game_ship("船3");
        Game_game.setting(ships);
        int turn = 1;
        boolean flg = true;
        while(flg){
            Game_game.turnline(turn);
            Game_game.avail(ships);
            Game_game.bomb(ships);
            turn++;
            flg = Game_game.next(ships);
        }
        Game_game.gameclear(turn);
    }
}