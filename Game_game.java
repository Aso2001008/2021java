import java.util.*;

public class Game_game{
    public static int field_x = 5;
    public static int field_y = 5;
    public static int ship_cnt = 3;
    public static boolean[][] field = new boolean[field_x][field_y];



    //タイトル表示
    public static void title(){
        System.out.println("***************************");
        System.out.println("       　戦艦ゲーム         ");
        System.out.println("***************************");
    }

    //ターン表示
    public static void turnline(int turn){
        System.out.println("---------[ターン"+turn+"]---------");
    }

    //船の初期位置
    public static void setting(Game_ship[] ship){
        Random rd = new Random();
        int cnt = 0;
        while(cnt < ship_cnt){
            int dx = rd.nextInt(field_x);
            int dy = rd.nextInt(field_y);
            if(!field[dx][dy]){
                field[dx][dy] = true;
                cnt++;
            }
        }
        for(int a = 0;a < field_x;a++){
            for(int b = 0;b < field_y;b++){
                if(field[a][b]){
                    ship[cnt-1].setxy(a,b);
                    cnt--;
                }
            }
        }
    }

    //船移動
    public static void move(Game_ship sh){
        Random rd = new Random();
        boolean f = true;
        while(f){
            int dx = rd.nextInt(field_x);
            int dy = rd.nextInt(field_y);
            if(!field[dx][dy]){
                field[sh.getshipx()][sh.getshipy()] = false;
                field[dx][dy] = true;
                sh.setxy(dx,dy);
                f = false;
            }
        }
    }

    //船の状態
    public static void avail(Game_ship[] ship){
        for(int a = 0;a < ship.length;a++){
            if(ship[a].gethp() > 0){
                System.out.println(ship[a].getname()+":生きてる");
            }else{
                System.out.println(ship[a].getname()+":死んでる");
            }
        }
    }
        

    //爆弾投下
    public static void bomb(Game_ship[] ship){
        Scanner sc = new Scanner(System.in);
        System.out.println("爆弾のX座標を入力してください(1-"+field_x+")");
        int bx = sc.nextInt() - 1;
        System.out.println("爆弾のY座標を入力してください(1-"+field_y+")");
        int by = sc.nextInt() - 1;
        boolean hitflg = false;
        for(Game_ship s : ship){
            hitflg = s.attacked(bx, by);
            if(hitflg){
                Game_game.move(s);
                hitflg = false;
            }
        }
    }

    //続けるか
    public static boolean next(Game_ship[] s){
        if(s[0].die() && s[1].die() && s[2].die()){
            return false;
        }else{
            return true;
        }
    }

    //クリア画面
    public static void gameclear(int t){
        System.out.println("***************************");
        System.out.println("     クリア！やったね！     ");
        System.out.println("　 クリアターン:"+t+"ターン　");
        //System.out.println("あなたは"+scored(t)+"級");
        System.out.println("***************************");
    }
    /*
    public static String scored(int t){
        if(t >= 60){
            return "一般一等兵";
        }else if(t >= 50){
            return "　 軍曹 　";
        }else if(t >= 40){
            return "　 大佐　 ";
        }else if(t >= 30){
            return "　 大将　 ";
        }else if(t >= 20){
            return "　 元帥　 ";
        }else if(t >= 0){
            return "　　神　　";
        }
    }
    */
}