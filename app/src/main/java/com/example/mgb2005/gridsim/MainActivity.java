package com.example.mgb2005.gridsim;
/**
 * Code in this project was borrowed from https://www.youtube.com/user/slidenerd as well as
 * https://developer.android.com/
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView grid1;
    private static final String TAG = "gridView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Toast t1 = Toast.makeText(getApplicationContext(), "Congradulations, You Pressed Button 1!", Toast.LENGTH_SHORT);
        final Toast t2 = Toast.makeText(getApplicationContext(), "Congradulations, You Pressed Button 2!", Toast.LENGTH_SHORT);

        final Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.show();
            }
        });

        final Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.show();
            }
        });

        //start of grid stuff
        ////


        grid1 = (GridView) findViewById(R.id.GRID1);
        grid1.setAdapter(new Adapt(this,grid1));
        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,String.valueOf(position));
            }
        });
        grid1.invalidateViews();
    }
}


class ViewHolder
{
    ImageView pic;
    ViewHolder(View v)
    {
        pic = (ImageView) v.findViewById(R.id.imageView);
    }
}


class Adapt extends BaseAdapter
{
    private static final String TAG = "gridView";
    public ArrayList<Integer> images;// stores the two pictures
    public ArrayList<Integer> numbers = new ArrayList<Integer>(256);// array that stores 1s and 0s
    Random rand = new Random();                                 //in order to display random images
    GridView grid1 = null;

    JSONObject json;
    public void setJson( JSONObject r )
    {
        json = r;
        String s = json.toString();
        System.out.println("\n\n#######" + s + "#######\n");
        JSONArray ja = null;
        int[][] grid = null;
        try {
             ja = (JSONArray) json.get( "grid" );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for ( int i = 0; i < 16; i++)
        {
            JSONArray temp = ja.optJSONArray(i);
            //System.out.println("\n\n#######" + temp + "#######\n");
            int count;
            for (count = 0; count<temp.length(); count++  )
            {
                int add = temp.optInt(count);
                //System.out.println(add);
                numbers.add(add);
            }
        }
        grid1.invalidateViews();
    }
    private Context context;
    public Adapt(Context context, GridView g)
    {
        this.context = context;
        images = new ArrayList<Integer>();
        images.add( R.drawable.penny);
        images.add(R.drawable.nickle);

        grid1 = g;

        RequestQueue rq = Volley.newRequestQueue(context);
        String url = "http://stman1.cs.unh.edu:6191/games";
        rq.start();
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setJson(response);
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error message");
            }
        });
        rq.add( jreq );

    }
    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(numbers.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int i, View view, ViewGroup vg)
    {
        View row= view;
        ViewHolder holder = null;
        if ( row == null )
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_image, vg, false );
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        int fork = numbers.get(i);
        if ( fork == 0 )
            holder.pic.setImageResource(images.get(0));
        else
            holder.pic.setImageResource(images.get(1));
        return row;
    }
}

