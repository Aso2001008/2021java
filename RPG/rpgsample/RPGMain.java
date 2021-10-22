import java.util.Random;
import java.util.Scanner;

import rpgcreature.*;

//////////////////////////////////////
// メインクラス
/////////////////////////////////////
public class RPGMain {
    //private final int MONSTER_NUM=3;
    //private final int COMMAND_BATTLE=1;
    //private final int COMMAND_RECOVERY=2;

    private Braver braver;
    private Monster[] monsters;
    public static void main(String[] args){
        RPGMain rpg = new RPGMain();
        //ゲームスタート
        rpg.game();
    }

    /**
     * ゲームメインメソッド
     */
    public void game(){

        //タイトル表示
        dispTitle();

        //名前入力
        Scanner sc = new Scanner(System.in);
        System.out.println("あなたの名前を入力してください");
        String name = sc.nextLine();
        //入力された名前で主人公（勇者をインスタンス作成）
        braver = new Braver(name);

        //バトルスタートを表示する
        dispBattleStart();

        //敵を3体ランダムに決める
        dicideMonsters();

        //ターン数計測
        int turn = 1;

        //メインループ（無限ループ）
        while(true){
            System.out.printf( "====%dターン目====\n",turn);
            //現在の状態を表示
            dispStatus();
            //入力されたコマンドを取得
            String c = sc.next();
            try{
                int b = Integer.parseInt(c);
            }catch(NumberFormatException e){
                System.out.println("1または2を入力してください");
                continue;
            }
            int command = Integer.parseInt(c);
            if( command == 1){
                //たたかう
                if( !battle() ){
                    break;
                }
            }else if(command == 2){
                //回復する
                braver.recovery();
            }else{
                System.out.println("1または2を入力してください");
                continue;
            }
            turn++;
        }
        sc.close();
    }

    /**
     * タイトルを表示する
     */
    private void dispTitle(){
        System.out.println("==========================");
        System.out.println("=       ASO QUEST        =");
        System.out.println("==========================");
    }

    /**
     * バトルスタートの表示
     */
    private void dispBattleStart(){
        System.out.println("==========================");
        System.out.println("====BATTLE START!!!!!!====");
        System.out.println("==========================");
    }

    /**
     * 現在の状態を表示する
     */
    private void dispStatus(){
        System.out.println("==========================");
        System.out.printf( "= %s                 =\n",braver.getName());
        System.out.printf( "= HP:%3d                 =\n",braver.getHp());
        System.out.println("==========================");
        System.out.println("どうしますか？1:たたかう 2:回復");
    }

    /**
     * モンスターを3体決定する
     */
    private void dicideMonsters(){
        Random r = new Random();
        monsters = new Monster[4];
        for(int i=0; i < 4; i++){
            //乱数を取得してモンスターを決定する
            int value = r.nextInt(4);
            if( value == 0 ){
                monsters[i] = new Slime();
            }else if( value == 1){
                monsters[i] = new Wizard();
            }else if( value == 2){
                monsters[i] = new MetalSlime();
            }else{
                monsters[i] = new Golem();
            }
        }
        
        //「〇〇〇が現れた」を表示
        for(int i = 0; i < 4; i++){
            monsters[i].displayAppearanceMsg();
        }
    }

    /**
     * たたかうコマンドに対する処理
     * 
     *  バトル継続するかのフラグ true：継続する false：バトル終了
     */
    private boolean battle(){
        //どのモンスターに攻撃するかを決定する
        Random r = new Random();
        Monster monster = null;
        //モンスター存在確認
        do{
            int index = r.nextInt(3);
            monster = monsters[index];
        }while( !monster.isThere() );

        //主人公→モンスターへ攻撃！
        braver.attack(monster);
        if( !monster.isAlive() ){
            System.out.printf("%sを倒した！\n",monster.getName());
        }
        
        //3体居なくなった？
        if( isNotThereAllMonster() ){
            //すべて居なくなったら終了
            return false;
        }

        //モンスター→主人公からの攻撃
        for(int i=0; i < 4;i++){
            if( monsters[i].isAlive()){
                monsters[i].attack(braver);
            }
        }

        //主人公が死んだか？
        if( !braver.isAlive() ){
            System.out.println("あなたはしにました");
            return false;
        }

        return true;
    }

    /**
     * 全てのモンスターが居なくなったか？
     * @return true:すべて居なくなった false:まだモンスターは居る
     */
    private boolean isNotThereAllMonster(){
        boolean isNotThereMonster = true;
        for(int i=0; i < 4; i++){
            if( monsters[i].isThere() ){
                isNotThereMonster = false;
                break;
            }
        }
        return isNotThereMonster;
    }
}
