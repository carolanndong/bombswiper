public class Coords
{
    private int row;
    private int col;

    public Coords(int r, int c)
    {
        row = r;
        col = c;
    }


    @Override
    public int hashCode()
    {
        return Integer.hashCode(row) + Integer.hashCode(col);
    }


    @Override
    public boolean equals(Object o)
    {
        Coords c = (Coords)o;
        return row == c.getRow() && col == c.getCol();
    }


    public void setRow(int r)
    {
        row = r;
    }


    public void setCol(int c)
    {
        col = c;
    }


    public int getRow()
    {
        return row;
    }


    public int getCol()
    {
        return col;
    }

}
