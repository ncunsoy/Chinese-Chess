public class Top extends Item{

    int tasAdedi=0;

    public Top(String position, String type) {
        super(position, type);
        puan=4.5;
        if(type.equalsIgnoreCase("red"))
            name="t";
        if(type.equalsIgnoreCase("black"))
            name="T";
    }

    public void move(String destination) {
        if(canMove(destination)){
            setPosition(destination);

        }

    }

    public boolean canMove(String destination){
        int row=Math.abs(destination.charAt(0)-'j');
        int column=destination.charAt(1)-'1';

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



    public boolean aradaTasVarMi(String destination){

        boolean tas=false;
        int dikey=Math.abs(destination.charAt(0)-this.getPosition().charAt(0));
        int yatay=Math.abs(destination.charAt(1)-this.getPosition().charAt(1));
        int row=this.getPosition().charAt(0)-'j';
        int column=this.getPosition().charAt(1)-'1';


        return tas;
    }



    public String print(){
        if(type.equals("red"))
            return "t";
        else
            return "T";
    }
}

