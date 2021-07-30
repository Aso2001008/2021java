public class Game_Main{
    public static void main(String[] args){
        //タイトル表示
        Game_game.title();

        //船数だけインスタンス化
        Game_ship[] ships = new Game_ship[Game_game.ship_cnt];
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
        System.out.print("　　あなたは");
        if(turn >= 60){
            System.out.print("一般一等兵");
        }else if(turn >= 50){
            System.out.print("　 軍曹 　");
        }else if(turn >= 40){
            System.out.print("　 大佐　 ");
        }else if(turn >= 30){
            System.out.print("　 大将　 ");
        }else if(turn >= 20){
            System.out.print("　 元帥　 ");
        }else if(turn >= 0){
            System.out.print("　　神　　");
        }
        System.out.println("級");
        System.out.println("***************************");
    }
}