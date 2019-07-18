package utd.com.drawingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;

/***************************************************************************************************
 * Name: Theophilus Ojukwu II
 * UTD ID: teo140130
 *
 **************************************************************************************************/

public class DrawActivity extends AppCompatActivity {
    FollowView follow;
    public SeekBar seekBar;
    int progressMade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        follow = findViewById(R.id.followview);
        follow.invalidate();

        // this is for the seek bar which changes the value for the brush stroke
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressMade = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking brush size", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                follow.setBrushSize(progressMade); // sets the brush size
                Toast.makeText(getApplicationContext(), "Stopped tracking", Toast.LENGTH_SHORT).show();
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
    /***********************************************************************************************
     * Function: redButton_click
     * Returns: nothing
     *
     * Description: this function is called when the red button is clicked
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void redButton_click(View view) {

        follow.setBrushColor(Color.RED);
        follow.invalidate();
    }
    /***********************************************************************************************
     * Function: blueButton_click
     * Returns: nothing
     *
     * Description: this function is called when the blue button is clicked
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void blueButton_click(View view) {
        follow.setBrushColor(Color.BLUE);
        follow.invalidate();
    }
    /***********************************************************************************************
     * Function: greenButton_click
     * Returns: nothing
     *
     * Description: this function is called when the green button is clicked
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void greenButton_click(View view) {
        follow.setBrushColor(Color.GREEN);
        follow.invalidate();
    }
    /***********************************************************************************************
     * Function: blackButton_click
     * Returns: nothing
     *
     * Description: this function is called when the black button is clicked
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void blackButton_click(View view) {
        follow.setBrushColor(Color.BLACK);
        follow.invalidate(); //calls the onDraw method
    }
    /***********************************************************************************************
     * Function: undo_click
     * Returns: nothing
     *
     * Description: this function is called when the undo button is clicked and removes a line
     * drawn
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void undo_click(View view) {
        follow.undo();
    }
}
