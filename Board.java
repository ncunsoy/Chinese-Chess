
public class Board extends AbstractBoard{

    public Board() {
        redItems = new Item[]{new Piyon("d1", "red"), new Piyon("d3", "red"), new Piyon("d5", "red"),
                new Piyon("d7", "red"), new Piyon("d9", "red"),
                new Kale("a1", "red"), new At("a2", "red"), new Fil("a3", "red"),
                new Vezir("a4", "red"), new Sah("a5", "red"), new Vezir("a6", "red"),
                new Fil("a7", "red"), new At("a8", "red"), new Kale("a9", "red"),
                new Top("c2", "red"), new Top("c8", "red")
        };

        blackItems = new Item[]{new Piyon("g1", "black"), new Piyon("g3", "black"), new Piyon("g5", "black"),
                new Piyon("g7", "black"), new Piyon("g9", "black"),
                new Kale("j1", "black"), new At("j2", "black"), new Fil("j3", "black"),
                new Vezir("j4", "black"), new Sah("j5", "black"), new Vezir("j6", "black"),
                new Fil("j7", "black"), new At("j8", "black"), new Kale("j9", "black"),
                new Top("h2", "black"), new Top("h8", "black")
        };

        items = new Item[32];
        for (int i = 0; i < items.length; i++) {
            if (i < 16)
                items[i] = redItems[i];
            else
                items[i] = blackItems[i - 16];
        }

        currentBoard = new Item[10][9];
        for (int i = 0; i < items.length; i++) {
            int row=Math.abs(items[i].getPosition().charAt(0)-'j');
            int column=items[i].getPosition().charAt(1)-'1';
            currentBoard[row][column]=items[i];
        }
    }



    public void print(){
        char c='j';
        for(int i=0;i< currentBoard.length;i++){
            System.out.print(""+c+"\t");

            for(int k=0;k< currentBoard[i].length;k++){
                if(currentBoard[i][k]!=null){
                    System.out.print(currentBoard[i][k].print());
                    if(k<currentBoard[i].length-1)
                        System.out.print("--");
                }

                else{
                    System.out.print("-");
                    if(k<currentBoard[i].length-1)
                        System.out.print("--");
                }
            }
            System.out.println();
            if(i==2 ||i==3 ||i==5 ||i==6)
                System.out.println(" \t|  |  |  |  |  |  |  |  |");
            if(i==4)
                System.out.println(" \t|                       |");
            if(i==0 ||i==8)
                System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
            if(i==1 ||i==7)
                System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
            c--;
        }
        System.out.println();
        System.out.println(" \t1--2--3--4--5--6--7--8--9");
    }

    public String toString(){
        String str="";
        for(int i=0;i< currentBoard.length;i++){

            for(int k=0;k< currentBoard[i].length;k++){
                if(currentBoard[i][k]!=null){
                    str+=(currentBoard[i][k].print());
                    if(k<currentBoard[i].length-1)
                        str+=("--");
                }

                else{
                    str+=("-");
                    if(k<currentBoard[i].length-1)
                        str+=("--");
                }
            }
            str+="\n";


        }
        str+="\n";
        return str;
    }
}
