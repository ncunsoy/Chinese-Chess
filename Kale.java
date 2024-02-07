public class Kale extends Item{

    public Kale(String position, String type) {
        super(position, type);
        puan=9;
        if(type.equalsIgnoreCase("red"))
            name="k";
        if(type.equalsIgnoreCase("black"))
            name="K";
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

        if(dikey==0 && yatay!=0)
            gecerliMi=true;
        if(yatay==0 && dikey!=0)
            gecerliMi=true;
        return gecerliMi;
    }




    public String print(){
        if(type.equals("red"))
            return "k";
        else
            return "K";
    }
}

