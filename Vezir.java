public class Vezir extends Item{

    public Vezir(String position, String type) {
        super(position, type);
        puan=2;
        if(type.equalsIgnoreCase("red"))
            name="v";
        if(type.equalsIgnoreCase("black"))
            name="V";
    }
    public void move(String destination) {
        if(canMove(destination)){
            String hamle=getPosition()+destination;
            setPosition(destination);

        }

    }

    public boolean canMove(String destination){
        if(gecerliMi(destination) && palasinIcindeMi(destination)){
            return true;
        }
        return false;
    }

    public boolean gecerliMi(String destination){
        boolean gecerliMi=false;
        int dikey=Math.abs(destination.charAt(0)-this.getPosition().charAt(0));
        int yatay=Math.abs(destination.charAt(1)-this.getPosition().charAt(1));

        if(dikey==1 && yatay==1)
            gecerliMi=true;

        return gecerliMi;
    }


    public boolean palasinIcindeMi(String destination){
        boolean icinde=false;
        if(type.equalsIgnoreCase("red")){
            if(destination.charAt(0)=='a' || destination.charAt(0)=='b'|| destination.charAt(0)=='c')
                if(destination.charAt(1)=='4' || destination.charAt(1)=='5'|| destination.charAt(1)=='6')
                    icinde=true;
        }

        else{
            if(destination.charAt(0)=='j' || destination.charAt(0)== 'i' || destination.charAt(0)=='h')
                if(destination.charAt(1)=='4' || destination.charAt(1)=='5'|| destination.charAt(1)=='6')
                    icinde=true;
        }
        return icinde;
    }


    public String print(){
        if(type.equals("red"))
            return "v";
        else
            return "V";
    }

}
