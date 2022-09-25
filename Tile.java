public class Tile
{
    // mine = 9; blank = 0; number = 1-8;
    private int     value;
    private boolean isFlagged;
    private boolean isTriggered;

    // constructors
    public Tile()
    {
        value = 0;
        isFlagged = false;
    }


    public Tile(int v, boolean f)
    {
        value = v;
        isFlagged = f;
    }


    // methods
    public void flag()
    {
        setIsFlagged();
        // set GUI to something
    }

    public void unflag()
    {
        isFlagged = false;
    }


    public void trigger()
    {
        setIsTriggered();
    }


    public void incrementValue()
    {
        value++;
    }


    // setter methods
    public void setValue(int val)
    {
        value = val;
    }


    public void setIsFlagged()
    {
        isFlagged = true;
    }


    public void setIsTriggered()
    {
        isTriggered = true;
    }


    // getter methods
    public int getValue()
    {
        return value;
    }


    public boolean getIsFlagged()
    {
        return isFlagged;
    }


    public boolean getIsTriggered()
    {
        return isTriggered;
    }
}
