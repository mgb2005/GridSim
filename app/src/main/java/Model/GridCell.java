package Model;

import com.example.mgb2005.gridsim.R;

/**
 * Created by Matt on 2/13/2017.
 */

public class GridCell {
    int rid = 0;
    int rsv;//raw server value
    int row;
    int col;

    public GridCell( int r, int c, int v )
    {
        row = r;
        col = c;
        rsv = v;
    }

    public int getResourceID()
    {
        return rid;
    }

    public String getCellType()
    {
        return "";
    }

    public String getCellInfo()
    {
        return "Row: " + row + ", Column: " + col + ", Raw Server Value: " + rsv;
    }
}
