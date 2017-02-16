package Model;

import Model.GridCell;
import android.support.v7.app.AppCompatActivity;

import com.example.mgb2005.gridsim.R;


/**
 * Created by Matt on 2/13/2017.
 */

public class Plant extends GridCell {
    int rsv;
    int rid = 0; // resource ID
    String des = "";
    public Plant(int r, int c, int v) {
        super(r, c, v);
        rsv = v;
        setString(v);
    }

    private void setString(int v )
    {
        if ( v == 1000 ) {
            des = "Tree";
            rid = R.drawable.tree;
        }
        else if ( v > 1000 && v < 2000 )
        {
            des = "bushes";
            rid = R.drawable.bushes;
        }
        else if ( v == 2002 )
        {
            des = "clover";
            rid = R.drawable.clover;
        }
        else if ( v == 2003 )
        {
            des = "mushroom";
            rid = R.drawable.mushroom;
        }
        else if ( v == 2002 )
        {
            des = "sunflower";
            rid = R.drawable.sunflower;
        }
        else if ( v> 4000 && v < 1000000 )
        {
            des = "reserved";
            rid = R.drawable.blank;
        }
        else
        {
            System.err.println("An unexpected value was passed in. Raw Server Value: " + v);
        }
    }


}
