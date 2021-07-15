public class Game_ship{
    private String name;
    private int hp;
    private int x;
    private int y;

    //名前とhpの設定(コンストラクタ)
    public Game_ship(String name){
        this.name = name;
        hp = 3;
    }

    //hp参照
    public int gethp(){
        return hp;
    }

    //船名参照
    public String getname(){
        return name;
    }

    //xy設定
    public void setxy(int x,int y){
        this.x = x;
        this.y = y;
    }

    //現在位置参照
    public int getshipx(){
        return this.x;
    }
    public int getshipy(){
        return this.y;
    }

    //攻撃を受けた
    public boolean attacked(int x,int y){
        System.out.print(this.name+":");
        if(this.hp > 0){
            int value = Math.abs(this.x - x)+Math.abs(this.y - y);
            if(value == 0){
                hp--;
                if(hp <= 0){
                    System.out.println("撃沈");
                    return false;
                }else{
                    System.out.println("命中だがまだ沈まない　移動します");
                    return true;
                }
            }else if(value == 1){
                System.out.println("波高し");
                return false;
            }else{
                System.out.println("はずれ");
                return false;
            }
        }else{
            System.out.println("撃沈済み");
            return false;
        }
    }

    //死亡フラグ
    public boolean die(){
        if(hp <= 0){
            return true;
        }else{
            return false;
        }
    }

    //
}