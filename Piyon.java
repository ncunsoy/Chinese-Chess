public class Piyon extends Item{
    boolean nehriGectiMi;

    public Piyon(String position, String type) {
        super(position, type);
        nehriGectiMi=false;
        puan=1;
        if(type.equalsIgnoreCase("red"))
            name="p";
        if(type.equalsIgnoreCase("black"))
            name="P";
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
        int dikey=destination.charAt(0)-this.getPosition().charAt(0);
        int yatay=destination.charAt(1)-this.getPosition().charAt(1);
        if(this.type.equals("red")){
            if(this.getPosition().charAt(0)>'e')
                nehriGectiMi=true;
        }

        if(this.type.equals("black")){
            if(this.getPosition().charAt(0)<'f')
                nehriGectiMi=true;
        }

        if(nehriGectiMi==false){
            if (this.type.equalsIgnoreCase("red")){
                if (dikey==1 && yatay==0)
                    gecerliMi=true;
            }

            if (this.type.equalsIgnoreCase("black")){
                if (dikey==-1 && yatay==0)
                    gecerliMi=true;
            }
        }
        else{
            if (this.type.equalsIgnoreCase("red")){
                if ((dikey==1 && yatay==0) || (dikey==0 && Math.abs(yatay)==1))
                    gecerliMi= true;
            }

            if (this.type.equalsIgnoreCase("black")){
                if ((dikey==-1 && yatay==0) || (dikey==0 && Math.abs(yatay)==1))
                    gecerliMi= true;

            }
        }
        return gecerliMi;
    }



    public String print(){
        if(type.equals("red"))
            return "p";
        else
            return "P";
    }

}

