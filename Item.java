import java.io.Serializable;

public class Item extends AbstractItem implements Serializable {

    public Item(String position,String type){
        super(position,type);
    }

    @Override
    public String print() {
        return null;
    }


    @Override
    public boolean canMove(String to) {
        return false;
    }




    @Override
    public void move(String destination) {
        setPosition(destination);
    }

    public String toString(){
        String str="";
        str+=type+" ";
        str+= name+" ";
        str+= getPosition()+" ";  // tahtadaki konumu gösterir. Örneğin, a1
        str+= puan+" ";
        return str;

    }
}


