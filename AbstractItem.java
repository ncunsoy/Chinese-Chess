import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractItem implements ItemInterface, Serializable {

    String type;
    private String position;  // tahtadaki konumu gösterir. Örneğin, a1
    double puan;
    String name;


    public AbstractItem(String position,String type){
        this.position=position;
        this.type=type;
    }

    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {
        this.position=position;
        }


    public abstract String print();



    public abstract boolean canMove(String to);


}
