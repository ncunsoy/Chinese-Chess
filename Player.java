import java.io.Serializable;

public class Player implements Serializable {

    float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.
    String isim;
    boolean sahGuvendeMi;


    public Player(String isim,float puan, String type){
        this.isim   =isim;
        this.puan=puan;
        sahGuvendeMi=true;
    }

    public void setSahGuvendeMi(boolean sahGuvendeMi){
        this.sahGuvendeMi=sahGuvendeMi;
    }

    public boolean getSahGuvendeMi(){
        return sahGuvendeMi;
    }

}

