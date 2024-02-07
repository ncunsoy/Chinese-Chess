public class Fil extends Item{

    public Fil(String position, String type) {
        super(position, type);
        puan=2;
        if(type.equalsIgnoreCase("red"))
            name="f";
        if(type.equalsIgnoreCase("black"))
            name="F";
    }

    public void move(String destination) {
        if(canMove(destination)) {
            String hamle=getPosition()+destination;
            setPosition(destination);
        }

    }

    public boolean canMove(String destination) {
        if(gecerliMi(destination)){
            //if(!aradaTasVarMi(destination))
            return true;
        }
        return false;
    }

    public boolean gecerliMi(String destination){
        boolean gecerliMi=false;
        int dikey=Math.abs(destination.charAt(0)-this.getPosition().charAt(0));
        int yatay=Math.abs(destination.charAt(1)-this.getPosition().charAt(1));

        if(type.equalsIgnoreCase("red")){
            //Nehri gecemez.
            if(destination.charAt(0)<='e'){
                if(dikey==2 && yatay==2)
                    gecerliMi=true;
            }
        }

        if(type.equalsIgnoreCase("black")){
            //Nehri gecemez.
            if(destination.charAt(0)>='f'){
                if(dikey==2 && yatay==2)
                    gecerliMi=true;
            }
        }
        return gecerliMi;
    }




    public String print(){
        if(type.equals("red"))
            return "f";
        else
            return "F";
    }
}
