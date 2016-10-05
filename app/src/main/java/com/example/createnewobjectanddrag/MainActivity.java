package com.example.createnewobjectanddrag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private final static int START_DRAGGING = 0;
    private final static int STOP_DRAGGING = 1;
    static  int i=0;
    boolean touched = false;
    private int status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button hit=(Button)findViewById(R.id.hit);
        hit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RelativeLayout main=(RelativeLayout)findViewById(R.id.MainPanel);
                final LinearLayout Panel=new LinearLayout(getApplicationContext());

                Panel.setOrientation(LinearLayout.VERTICAL);
             //   main.addView(Panel);
                final Button button=new Button(getApplicationContext());
                button.setText(String.valueOf(i));
                button.setId(i);
                EditText header=new EditText(getApplicationContext());
                header.setText("hehe"+String.valueOf(i));
                header.setEnabled(false);
                header.setBackgroundColor(Color.BLUE);
                Panel.addView(button);
                Panel.addView(header);

                button.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent me) {
                        if (me.getAction() == MotionEvent.ACTION_DOWN) {
                            status = START_DRAGGING;


                        }
                        if (me.getAction() == MotionEvent.ACTION_UP) {
                            status = STOP_DRAGGING;
                            Log.i("Drag", "Stopped Dragging");
                        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
                            if (status == START_DRAGGING) {
                                System.out.println("Dragging"+String.valueOf(me.getRawX()));

                                float x,y;
                                if(me.getRawX()<900){x=300;}
                                else if(me.getRawX()>1000){x=1000;}
                                else{x=me.getRawX();}
                                if(me.getRawY()<500){y=500;}
                                else if(me.getRawY()>1500){y=1500;}
                                else{y=me.getRawY();}

                                Panel.setX(x);
                                Panel.setY(y-200);
                               // button.setPadding((int) me.getRawX(), (int) me.getRawY(), 0, 0); //this is not working fine.


                                Panel.invalidate();
                            }
                        }
                        return false;
                    }

                });
                main.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(touched == true) // any event from down and move
                        {
                        }
                        if(event.getAction()==MotionEvent.ACTION_UP){
                            touched = false;
                        }
                        return true;
                    }
                });
                i=i+1;

                main.addView(Panel);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
