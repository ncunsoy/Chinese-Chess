import java.util.ArrayList;

public class At extends Item{

    public At(String position, String type) {
        super(position, type);
        puan=4;
        if(type.equalsIgnoreCase("red"))
            name="a";
        if(type.equalsIgnoreCase("black"))
            name="A";
    }

    public void move(String destination) {
        if(canMove(destination)){
            String hamle=getPosition()+destination;
            setPosition(destination);
        }

    }

    public boolean canMove(String destination) {
        if(gecerliMi(destination)){
            return true;
        }
        return false;
    }

    public boolean gecerliMi(String destination){
        boolean gecerliMi=false;
        int dikey=Math.abs(destination.charAt(0)-this.getPosition().charAt(0));
        int yatay=Math.abs(destination.charAt(1)-this.getPosition().charAt(1));

        if(dikey==2 && yatay==1)
            gecerliMi=true;
        if(dikey==1 && yatay==2)
            gecerliMi=true;
        return gecerliMi;
    }





    public String print(){
        if(type.equals("red"))
            return "a";
        else
            return "A";
    }


}

