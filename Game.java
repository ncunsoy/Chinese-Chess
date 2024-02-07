import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends AbstractGame{
    Item topunAtladigiTas=null;
    boolean oyunSonlandiMi=false;

    public Game(String playerRed, String playerBlack) {
        super(playerRed, playerBlack);
    }

    @Override
    void play(String from, String to) {
        if (!oyunSonlandiMi) {

            Item item = tasiBul(from);
            boolean ikiSahGuvende = sahKarsilikliDegil(from, to);
            if (item != null) {

                if (ikiSahGuvende && !aradaTasVarMi(item, to) && item.canMove(to) && !ucDefaSah(item,to) ) {
                    if (oyunSirasi.getSahGuvendeMi()) {
                        hamleYap(item,to);
                        if(oyunBittiMi()){
                            Player p=null;
                            if(oyunSirasi.equals(red))
                                p=black;
                            else
                                p=red;
                            System.out.print("ŞAH MAT! " + oyunSirasi.isim + " oyunu kazandi. ");
                            System.out.println(oyunSirasi.isim + "'in puani: " +oyunSirasi.puan + ", " + p.isim + "'nin puani: " + p.puan);
                            oyunSonlandiMi = true;
                        }
                        if (sahiTehditEdenItemler(oyunSirasi).size() > 0) {
                            hamleYap(item,from);
                            System.out.println("hatali hareket");
                        }

                        else {
                            if (oyunSirasi.equals(red))
                                oyunSirasi = black;
                            else
                                oyunSirasi = red;
                        }
                    } else {
                        if (sahKacarMi(oyunSirasi) || sahTehditiniKes(oyunSirasi)) {
                            hamleYap(item,to);
                            if (sahiTehditEdenItemler(oyunSirasi).size() > 0) {
                                hamleYap(item,from);
                                System.out.println("hatali hareket");
                            }
                            if(oyunBittiMi()){
                                System.out.print("ŞAH MAT! " + oyunSirasi.isim + " oyunu kazandı. ");
                                System.out.println(oyunSirasi.isim + "'in puanı: " +oyunSirasi.puan + "," + oyunSirasi.isim + "'in puanı: " + oyunSirasi
                                        .puan);
                                oyunSonlandiMi = true;
                            }
                            else{
                                if (oyunSirasi.equals(red))
                                    oyunSirasi = black;
                                else
                                    oyunSirasi = red;
                            }
                        }
                    }

                }else
                    System.out.println("hatali hareket");
            } else
                System.out.println("hatali hareket");
        }
        else
            System.out.println("hatali hareket");
    }


    public void hamleYap(Item item,String to){
        //Item[][] clone=board.currentBoard.clone();
        String from=item.getPosition();
        Item toItem=board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')];


        if (board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')] == null) {
            if (!(item instanceof Top)) {
                board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')] = item;
                board.currentBoard[Math.abs(from.charAt(0) - 'j')][(from.charAt(1) - '1')] = null;
                item.move(to);
            }

            if ((item instanceof Top)) {
            aradaTasVarMi(item,to);
            if (topunAtladigiTas == null) {
                board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')] = item;
                board.currentBoard[Math.abs(from.charAt(0) - 'j')][(from.charAt(1) - '1')] = null;
                item.move(to);
            }
            }
        }

        else {
            if(item instanceof Top){
                aradaTasVarMi(item,to);
                if (topunAtladigiTas != null) {
                    if (!item.type.equals(toItem.type)) {
                        item.move(to);
                        oyunSirasi.puan += toItem.puan;
                        board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')] = item;
                        board.currentBoard[Math.abs(from.charAt(0) - 'j')][(from.charAt(1) - '1')] = null;
                        toItem.setPosition("xx");
                    }
                    else
                        System.out.println("hatali hareket");
                    topunAtladigiTas=null;
                }
            }
            else {
                Item item2 = board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')];
                if (item.type.equals(item2.type)) {
                    System.out.println("hatali hareket");

                } else {
                    item.move(to);
                    toItem.setPosition("xx");
                    oyunSirasi.puan +=toItem.puan;
                    board.currentBoard[Math.abs(to.charAt(0) - 'j')][(to.charAt(1) - '1')] = item;
                    board.currentBoard[Math.abs(from.charAt(0) - 'j')][(from.charAt(1) - '1')] = null;
                }
            }
        }
    }

    public boolean oyunBittiMi(){
        ArrayList<Item> i=new ArrayList<>();
        Sah s=null;
        for(int a = 0; a <board.redItems.length; a++){
            if(oyunSirasi.equals(red)&& board.blackItems[a] instanceof Sah)
                s=(Sah)board.blackItems[a];
            if(oyunSirasi.equals(black)&& board.redItems[a] instanceof Sah)
                s=(Sah)board.redItems[a];
        }


        if(oyunSirasi.equals(red)){
            for(int b = 0; b <board.redItems.length; b++)
                if(!board.redItems[b].getPosition().equalsIgnoreCase("xx"))
                    if(canPlay(board.redItems[b],s.getPosition()))
                        i.add(board.redItems[b]);
        }
        if(oyunSirasi.equals(black)){
            for(int b = 0; b <board.blackItems.length; b++)
                if(!board.blackItems[b].getPosition().equalsIgnoreCase("xx"))
                    if(canPlay(board.blackItems [b],s.getPosition()))
                        i.add(board.blackItems[b]);
        }
        Player p=null;
        if(oyunSirasi.equals(red))
            p=black;
        else
            p=red;
        if(i.size()>=1 && !sahKacarMi(p) && !sahTehditiniKes(p))
            return true;
        return false;

    }

    public boolean ucDefaSah(Item item, String to){
        Sah redS=null;
        Sah blackS=null;

        for(int i=0;i<board.redItems.length;i++)
            if(board.redItems[i] instanceof Sah)
                redS= (Sah) board.redItems[i];

        for(int i=0;i<board.blackItems.length;i++)
            if(board.blackItems[i] instanceof Sah)
                blackS= (Sah) board.blackItems[i];

        if(item.type.equalsIgnoreCase("red")){
            if(canPlay(item, blackS.getPosition())){
                if(hamlelerRed.size()>0)
                    if(hamlelerRed.get(hamlelerRed.size()-1).equals(item))
                        hamlelerRed.add(item);
            }

            else
                hamlelerRed.clear();

            if(hamlelerRed.size()>3)
                return true;
        }

        if(item.type.equalsIgnoreCase("black")){
            if(canPlay(item, redS.getPosition())){
                if(hamlelerBlack.size()>0)
                    if(hamlelerBlack.get(hamlelerBlack.size()-1).equals(item))
                        hamlelerBlack.add(item);
            }

            else
                hamlelerBlack.clear();

            if(hamlelerBlack.size()>3)
                return true;
        }

        return false;

    }


    public boolean canPlay(Item item,String to){
        if(!(to.charAt(0)>='a' && to.charAt(0)<='z' && to.charAt(1)>='1'  && to.charAt(1)<='9'))
                return false;
        Item toItem=board.currentBoard[Math.abs(to.charAt(0)-'j')][to.charAt(1)-'1'];
        if(toItem==null || !toItem.type.equalsIgnoreCase(item.type)){
            if(item instanceof At)
                if(item.canMove(to) && !aradaTasVarMi(item,to) )
                    return true;

            if(item instanceof Kale)
                if(item.canMove(to) && !aradaTasVarMi(item,to))
                    return true;
            if(item instanceof Fil)
                if(item.canMove(to) && !aradaTasVarMi(item,to))
                    return true;
            if(item instanceof Top)
                if(item.canMove(to) && !aradaTasVarMi(item,to)){
                    return true;
                }

            if(item instanceof Vezir || item instanceof Sah || item instanceof Piyon)
                if(item.canMove(to))
                    return true;
        }
        return false;

    }


    public boolean aradaTasVarMi(Item item,String destination){
        int tasAdedi=0; //Top için
        boolean tas=false;
        int dikey=Math.abs(destination.charAt(0)-item.getPosition().charAt(0));
        int yatay=Math.abs(destination.charAt(1)-item.getPosition().charAt(1));
        int row=Math.abs(item.getPosition().charAt(0)-'j');
        int column=item.getPosition().charAt(1)-'1';

        if(item instanceof At){
            if(destination.charAt(1)-item.getPosition().charAt(1)==2){
                    if(board.currentBoard[row][column+1]!=null)
                        tas=true;
            }

            if(destination.charAt(1)-item.getPosition().charAt(1)==2){
                if(board.currentBoard[row][column-1]!=null)
                    tas=true;
            }

            if(item.getPosition().charAt(0)-destination.charAt(0)==2) {
                if (board.currentBoard[row+1][column] != null)
                    tas = true;
            }

            if(destination.charAt(0)-item.getPosition().charAt(0)==2){
                if(board.currentBoard[row-1][column]!=null)
                    tas=true;
            }

        }

        if(item instanceof Fil){
            int row1=Math.abs(destination.charAt(0)-'j');
            int row2=Math.abs(item.getPosition().charAt(0)-'j');
            int controlRow=(row1+row2)/2;

            int column1=destination.charAt(1)-'1';
            int column2=item.getPosition().charAt(1)-'1';
            int controlColumn=(column1+column2)/2;


            if(board.currentBoard[controlRow][controlColumn]!=null)
                tas=true;

        }

        if(item instanceof Kale){
            if(dikey==0 && yatay!=0){
                if(destination.charAt(1)>item.getPosition().charAt(1)){
                    for(int i=1;i<yatay;i++){
                        if(board.currentBoard[row][column+i]!=null)
                            tas=true;
                    }
                }

                if(destination.charAt(1)<item.getPosition().charAt(1)){
                    for(int i=1;i<yatay;i++){
                        if(board.currentBoard[row][column-i]!=null)
                            tas=true;
                    }
                }
            }

            if(dikey!=0 && yatay==0){
                if(destination.charAt(0)>item.getPosition().charAt(0)){
                    for(int i=1;i<dikey;i++){
                        if(board.currentBoard[row-i][column]!=null)
                            tas=true;
                    }
                }

                if(destination.charAt(0)<item.getPosition().charAt(0)){
                    for(int i=1;i<dikey;i++){
                        if(board.currentBoard[row+i][column]!=null)
                            tas=true;
                    }
                }
            }
        }
        if(item instanceof Top){
            topunAtladigiTas=null;
            if(dikey==0 && yatay!=0){
                if(destination.charAt(1)>item.getPosition().charAt(1)) {
                    for (int i = 1; i < yatay; i++) {
                        if (board.currentBoard[row][column + i] != null) {
                            tas = true;
                            topunAtladigiTas = board.currentBoard[row][column + i];
                            tasAdedi++;
                        }
                    }
                }

                if(destination.charAt(1)<item.getPosition().charAt(1)){
                    for(int i=1;i<yatay;i++){
                        if(board.currentBoard[Math.abs(destination.charAt(0)-'j')][(destination.charAt(1)-'1')+i]!=null){
                            tas=true;
                            topunAtladigiTas =board.currentBoard[Math.abs(destination.charAt(0)-'j')][(destination.charAt(1)-'1')+i];
                            tasAdedi++;
                        }
                    }
                }
            }

            if(dikey!=0 && yatay==0){
                if(destination.charAt(0)>item.getPosition().charAt(0)){
                    for(int i=1;i<dikey;i++){
                        if(board.currentBoard[Math.abs(destination.charAt(0)-'j')+i][(destination.charAt(1)-'1')]!=null){
                            tas=true;
                            topunAtladigiTas =board.currentBoard[Math.abs(destination.charAt(0)-'j')+i][(destination.charAt(1)-'1')];
                            tasAdedi++;
                        }
                    }

                }

                if(destination.charAt(0)<item.getPosition().charAt(0)){
                    for(int i=1;i<dikey;i++){
                        if(board.currentBoard[row+i][column]!=null){
                            tas=true;
                            topunAtladigiTas =board.currentBoard[row+i][column];
                            tasAdedi++;
                        }
                    }
                }
            }
            boolean engel=false;
            Item to=board.currentBoard[Math.abs(destination.charAt(0)-'j')][destination.charAt(1)-'1'];

            if(tasAdedi==0 && to==null){
                engel=false;
            }

            else{
                if(tasAdedi==1 && to !=null){
                    engel=false;
                }

                else{
                    engel=true;
                    topunAtladigiTas=null;
                }

            }
            return engel;
        }
        return tas;

    }


    public ArrayList<Item> sahiTehditEdenItemler(Player player){
        // Item dizisini dön şahı tehdit eden itemi döndür. play içinde de o taş yenebilir mi veya arasına girilebillir mi diye bak.
        ArrayList<Item> tehditEdenItems=new ArrayList<>();
        String redSah="";
        String blackSah="";

        for(int i=0;i<board.redItems.length;i++){
            if(board.redItems[i] instanceof Sah)
                redSah=board.redItems[i].getPosition();
        }

        for(int i=0;i<board.blackItems.length;i++){
            if(board.blackItems[i] instanceof Sah){
                blackSah=board.blackItems[i].getPosition();
            }

        }

        if(player.equals(red)){
            for (int i=0;i<board.blackItems.length;i++)
                if(canPlay(board.blackItems[i],redSah) &&(!board.blackItems[i].getPosition().equals("xx"))){
                    tehditEdenItems.add(board.blackItems[i]);
                }


        }

        if(player.equals(black)){
            for (int i=0;i<board.redItems.length;i++)
                if(canPlay(board.redItems[i],blackSah) &&(!board.redItems[i].getPosition().equals("xx"))){
                    tehditEdenItems.add(board.redItems[i]);
                }


        }
        if(tehditEdenItems.size()==0)
            player.setSahGuvendeMi(true);
        else
            player.setSahGuvendeMi(false);
        return tehditEdenItems;

    }

    public boolean sahKacarMi(Player player){
        //Şahın olası kaçış yerlerinde tehlikede olup olmadığını kontrol et.
        String sahinKonumu="";
        Sah sah=null;

        if(player.equals(red)){
            for(int i=0;i<board.redItems.length;i++)
                if(board.redItems[i] instanceof Sah){
                    sahinKonumu=board.redItems[i].getPosition();
                    sah=(Sah)board.redItems[i];
                    break;
                }
            if(canPlay(sah,""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1))) {
                int a=0;
                hamleYap(sah,(""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1)));
                for (;a<board.blackItems.length;a++){
                    if(canPlay(board.blackItems[a],(""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(a>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1))) {
                int b=0;
                hamleYap(sah,(""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1)));
                for (;b<board.blackItems.length;b++){
                    if(canPlay(board.blackItems[b],(""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(b>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1)))){
                int c=0;
                hamleYap(sah,(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1))));
                for (;c<board.blackItems.length;c++){
                    if(canPlay(board.blackItems[c],(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1)))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(c>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1)))) {
                int d=0;
                hamleYap(sah,(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1))));
                for (;d<board.blackItems.length;d++){
                    if(canPlay(board.blackItems[d],(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1)))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(d>=board.blackItems.length)
                    return true;
            }
        }

        if(player.equals(black)){
            for(int i=0;i<board.blackItems.length;i++)
                if(board.blackItems[i] instanceof Sah){
                    sahinKonumu=board.blackItems[i].getPosition();
                    sah=(Sah)board.blackItems[i];
                    break;
                }
            if(canPlay(sah,""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1))) {
                int a=0;
                hamleYap(sah,(""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1)));
                for (;a<board.redItems.length;a++){
                    if(canPlay(board.redItems[a],(""+((char)(sahinKonumu.charAt(0)+1))+sahinKonumu.charAt(1))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(a>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1))) {
                int b=0;
                hamleYap(sah,(""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1)));
                for (;b<board.redItems.length;b++){
                    if(canPlay(board.redItems[b],(""+((char)(sahinKonumu.charAt(0)-1))+sahinKonumu.charAt(1))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(b>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1)))){
                int c=0;
                hamleYap(sah,(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1))));
                for (;c<board.redItems.length;c++){
                    if(canPlay(board.redItems[c],(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)+1)))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(c>=board.blackItems.length)
                    return true;
            }
            if(canPlay(sah,""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1)))) {
                int d=0;
                hamleYap(sah,(""+(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1)))));
                for (;d<board.redItems.length;d++){
                    if(canPlay(board.redItems[d],(""+(sahinKonumu.charAt(0))+((char)(sahinKonumu.charAt(1)-1)))))
                        break;
                }
                hamleYap(sah,sahinKonumu);
                if(d>=board.blackItems.length)
                    return true;
            }
        }
        return false;
    }




    public Item tasiBul(String from){
        Item item=null;

        if(oyunSirasi.equals(red)){
            int i=0;
            for(;i<board.redItems.length;i++){
                if(board.redItems[i].getPosition().equals(from)){
                    item=board.redItems[i];
                    break;
                }
            }
        }

        if(oyunSirasi.equals(black)){
            int i=0;
            for(;i<board.blackItems.length;i++){
                if(board.blackItems[i].getPosition().equals(from)){
                    item=board.blackItems[i];
                    break;
                }
            }
        }
        return item;
    }

    public boolean sahKarsilikliDegil(String from, String to){
        boolean guvenli=false;
        int sahSayisi=0;

        if(from.charAt(1)==to.charAt(1))
            guvenli=true;
        else{
            int satir=Math.abs(from.charAt(0)-'j');
            int sutun=from.charAt(1)-'1';

            for(int i = 0; i <board.currentBoard.length; i++){
                if(satir==i)
                    continue;
                Item item=board.currentBoard[i][sutun];
                if(item!=null && !(item instanceof Sah))
                    guvenli=true;
                if(item instanceof Sah)
                    sahSayisi++;

            }
            if(sahSayisi<2)
                guvenli=true;

        }
        return guvenli;

    }

    public boolean sahTehditiniKes(Player player){
        //Tehdit eden taşı yiyip yiyemeyeceğini kontrol eder.

        if(player.equals(red)) {
            int i = 0;
            for (; i < board.redItems.length; i++) {
                if (!board.redItems[i].getPosition().equals("xx")) {
                    if (canPlay(board.redItems[i], (sahiTehditEdenItemler(player).get(0).getPosition()))) {
                        return true;
                    }
                }
            }
        }

        if(player.equals(black)) {
            int i = 0;
            for (; i < board.blackItems.length; i++) {
                if (!board.blackItems[i].getPosition().equals("xx")) {
                    sahiTehditEdenItemler(player);
                    if (canPlay(board.blackItems[i], (sahiTehditEdenItemler(player).get(0).getPosition()))) {
                        return true;
                    }
                }
            }
        }

        //Taşın hareketini kesmek için

        //Red
        if(player.equals(red)){
            Sah s=null;
            for(int i=0;i<board.redItems.length;i++){
                if(board.redItems[i] instanceof Sah)
                    s =(Sah) board.redItems[i];
            }

            //Aynı satir farkli sutun
            if(s.getPosition().charAt(0) != sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)){
                if(sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)<s.getPosition().charAt(0)){
                    for(int j=0;j<board.redItems.length;j++){
                        if(!board.redItems[j].getPosition().equalsIgnoreCase("xx")){
                            for(int i=0; i<s.getPosition().charAt(0)-sahiTehditEdenItemler(player).get(0).getPosition().charAt(0); i++){
                                String row=""+(char)(sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)+i);
                                String yer=row+sahiTehditEdenItemler(player).get(0).getPosition().charAt(1);
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if(canPlay(board.redItems[j],yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.redItems[j].getPosition();
                                    hamleYap(board.redItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.redItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.redItems[j],from);
                                }

                            }}}}
                if(sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)>s.getPosition().charAt(0)){
                    for(int j=0;j<board.redItems.length;j++){
                        if(!board.redItems[j].getPosition().equalsIgnoreCase("xx")){
                            for(int i=0; i<sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)-s.getPosition().charAt(0); i++){
                                String row=""+(char)(s.getPosition().charAt(0)+i);
                                String yer=row+s.getPosition().charAt(1);
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if(canPlay(board.redItems[j],yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.redItems[j].getPosition();
                                    hamleYap(board.redItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.redItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.redItems[j],from);
                            }}}}}}

            //Ayni sutun farkli satir
            if(s.getPosition().charAt(1) != sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)){
                if(sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)<s.getPosition().charAt(1)){
                    for(int j=0;j<board.redItems.length;j++){
                        if(!board.redItems[j].getPosition().equalsIgnoreCase("xx")){
                            for(int i=0; i<s.getPosition().charAt(1)-sahiTehditEdenItemler(player).get(0).getPosition().charAt(1); i++){
                                String column=""+(char)(sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)+i);
                                String yer=sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)+column;
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if(canPlay(board.redItems[j],yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.redItems[j].getPosition();
                                    hamleYap(board.redItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.redItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.redItems[j],from);
                            }}}}}
                if(sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)>s.getPosition().charAt(1)){
                    for(int j=0;j<board.redItems.length;j++){
                        if(!board.redItems[j].getPosition().equalsIgnoreCase("xx")){
                            for(int i=0; i<sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)-s.getPosition().charAt(1); i++){
                                String column=""+(char)(s.getPosition().charAt(1)+i);
                                String yer=s.getPosition().charAt(0)+column;
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if(canPlay(board.redItems[j],yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.redItems[j].getPosition();
                                    hamleYap(board.redItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.redItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.redItems[j],from);
                            }}}}
            }

        }}

        //Black
        if(player.equals(black)) {
            Sah s = null;
            for (int i = 0; i < board.blackItems.length; i++) {
                if (board.blackItems[i] instanceof Sah)
                    s = (Sah) board.blackItems[i];
            }

            //Aynı satir farkli sutun
            if (s.getPosition().charAt(0) != sahiTehditEdenItemler(player).get(0).getPosition().charAt(0)) {
                if (sahiTehditEdenItemler(player).get(0).getPosition().charAt(0) < s.getPosition().charAt(0)) {
                    for (int j = 0; j < board.blackItems.length; j++) {
                        if (!board.blackItems[j].getPosition().equalsIgnoreCase("xx")) {
                            for (int i = 0; i < s.getPosition().charAt(0) - sahiTehditEdenItemler(player).get(0).getPosition().charAt(0); i++) {
                                String row = "" + (char) (sahiTehditEdenItemler(player).get(0).getPosition().charAt(0) + i);
                                String yer = row + sahiTehditEdenItemler(player).get(0).getPosition().charAt(1);
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if (canPlay(board.blackItems[j], yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.blackItems[j].getPosition();
                                    hamleYap(board.blackItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.blackItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.blackItems[j],from);
                                }
                            }
                        }
                    }
                }
                if (sahiTehditEdenItemler(player).get(0).getPosition().charAt(0) > s.getPosition().charAt(0)) {
                    for (int j = 0; j < board.blackItems.length; j++) {
                        if (!board.blackItems[j].getPosition().equalsIgnoreCase("xx")) {
                            for (int i = 0; i < sahiTehditEdenItemler(player).get(0).getPosition().charAt(0) - s.getPosition().charAt(0); i++) {
                                String row = "" + (char) (s.getPosition().charAt(0) + i);
                                String yer = row + s.getPosition().charAt(1);
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if (canPlay(board.blackItems[j], yer) &&  item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.blackItems[j].getPosition();
                                    hamleYap(board.blackItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.blackItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.blackItems[j],from);
                                }
                            }
                        }
                    }
                }
            }

            //Ayni sutun farkli satir
            if (s.getPosition().charAt(1) != sahiTehditEdenItemler(player).get(0).getPosition().charAt(1)) {
                if (sahiTehditEdenItemler(player).get(0).getPosition().charAt(1) < s.getPosition().charAt(1)) {
                    for (int j = 0; j < board.blackItems.length; j++) {
                        if (!board.blackItems[j].getPosition().equalsIgnoreCase("xx")) {
                            for (int i = 0; i < s.getPosition().charAt(1) - sahiTehditEdenItemler(player).get(0).getPosition().charAt(1); i++) {
                                String column = "" + (char) (sahiTehditEdenItemler(player).get(0).getPosition().charAt(1) + i);
                                String yer = sahiTehditEdenItemler(player).get(0).getPosition().charAt(0) + column;
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if (canPlay(board.blackItems[j], yer) && item==null && !(board.blackItems[j] instanceof Sah)){
                                    String from=board.blackItems[j].getPosition();
                                    hamleYap(board.blackItems[j],yer);
                                    if(sahiTehditEdenItemler(player).size()==0){
                                        hamleYap(board.blackItems[j],from);
                                        return true;
                                    }
                                    hamleYap(board.blackItems[j],from);
                                }
                            }
                        }
                    }
                }
                if (sahiTehditEdenItemler(player).get(0).getPosition().charAt(1) > s.getPosition().charAt(1)) {
                    for (int j = 0; j < board.blackItems.length; j++) {
                        if (!board.blackItems[j].getPosition().equalsIgnoreCase("xx")) {
                            for (int i = 0; i < sahiTehditEdenItemler(player).get(0).getPosition().charAt(1) - s.getPosition().charAt(1); i++) {
                                String column = "" + (char) (s.getPosition().charAt(1) + i);
                                String yer = s.getPosition().charAt(0) + column;
                                Item item=board.currentBoard[Math.abs(yer.charAt(0)-'j')][yer.charAt(1)-'1'];
                                if (canPlay(board.blackItems[j], yer) && item==null && !(board.blackItems[j] instanceof Sah)) {
                                    String from = board.blackItems[j].getPosition();
                                    hamleYap(board.blackItems[j], yer);
                                    if (sahiTehditEdenItemler(player).size() == 0) {
                                        hamleYap(board.blackItems[j], from);
                                        return true;
                                    }
                                    hamleYap(board.blackItems[j], from);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    void save_binary(String address) {
        ObjectOutputStream writer=null;
        try {
            writer=new ObjectOutputStream(new FileOutputStream(address));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.writeObject(board);
            writer.writeObject((oyunSirasi));
            writer.writeObject(black);
            writer.writeObject(red);
            writer.writeObject(hamlelerBlack);
            writer.writeObject(hamlelerRed);
            for(int i=0;i<board.items.length;i++){
                writer.writeObject(board.items[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(writer!=null)
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    void save_text(String address) {
        PrintWriter writer=null;
        try {
            writer=new PrintWriter(new FileOutputStream(address));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            writer.write(board.toString());
            writer.write(""+red.isim+"\n");
            writer.write(""+red.puan+"\n");
            writer.write(""+red.sahGuvendeMi+"\n");

            writer.write(""+black.isim+"\n");
            writer.write(""+black.puan+"\n");
            writer.write(""+black.sahGuvendeMi+"\n");

            writer.write(oyunSirasi.isim+"\n");


            for(int i=0;i<board.items.length;i++){
                writer.write(board.items[i].toString());
                writer.write("\n");
            }

            if(writer!=null)
                writer.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    void load_text(String address) {
        Scanner reader=null;
        try {
            reader=new Scanner(new FileInputStream(address));
            for(int i=0;i<11;i++)
                reader.nextLine();
            red.isim=reader.next();
            red.puan=Float.parseFloat(reader.next());
            String durum=reader.next();
            if(durum.equalsIgnoreCase("true"))
                red.sahGuvendeMi=true;
            else
                red.sahGuvendeMi=false;

            black.isim=reader.next();
            black.puan=Float.parseFloat(reader.next());
            durum=reader.next();
            if(durum.equalsIgnoreCase("true"))
                black.sahGuvendeMi=true;
            else
                black.sahGuvendeMi=false;

            String sira=reader.next();
            if(sira.equalsIgnoreCase(black.isim))
                oyunSirasi=black;
            else
                oyunSirasi=red;

            reader.nextLine();
            int index=0;
            while (reader.hasNextLine()){
                String item=reader.nextLine();
                String[] args=item.split(" ");
                board.items[index].type=args[0];
                board.items[index].name=args[1];
                board.items[index].setPosition(args[2]);
                board.items[index].puan=Double.parseDouble(args[3]);
                index++;

            }

            board.currentBoard = new Item[10][9];
            for (int i = 0; i <board.items.length; i++) {
                if(!board.items[i].getPosition().equalsIgnoreCase("xx"))
                {
                    int row=Math.abs(board.items[i].getPosition().charAt(0)-'j');
                    int column=board.items[i].getPosition().charAt(1)-'1';
                    board.currentBoard[row][column]=board.items[i];
                }
            }
            if(reader!=null)
                reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    void load_binary(String address) {
        ObjectInputStream reader=null;
        try {
            reader=new ObjectInputStream(new FileInputStream(address));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            board=(Board) (reader.readObject());
            oyunSirasi=(Player) reader.readObject();
            black=(Player) reader.readObject();
            red= (Player) reader.readObject();
            hamlelerBlack= (ArrayList<Item>) reader.readObject();
            hamlelerRed=(ArrayList<Item>) reader.readObject();
            for(int i=0;i<board.items.length;i++){
                board.items[i]=(Item)reader.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(reader!=null)
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardInterface getBoard() {
        return board;
    }
}


