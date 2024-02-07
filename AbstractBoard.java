import java.io.Serializable;

public abstract class AbstractBoard implements BoardInterface, Serializable {

    Item [] items;
    Item[] blackItems;
    Item[] redItems;
    Item [][] currentBoard;

}


