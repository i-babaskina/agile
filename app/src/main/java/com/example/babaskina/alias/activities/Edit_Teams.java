package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.model_classes.Team;

import java.util.ArrayList;
import java.util.Collections;

public class Edit_Teams extends AppCompatActivity {

    public ArrayList<String> teams = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__teams);
        Resources res = getResources();
        Collections.addAll(teams, res.getStringArray(R.array.teamset));
        NewAdapter adapter = new NewAdapter(teams, this, false);
        final ListView list = (ListView) findViewById(R.id.teamsListView);
        list.setAdapter(adapter);
        final EditText edit1 = (EditText) findViewById(R.id.teamNameEditText);
        edit1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    teams.add(0, edit1.getText().toString());
                    edit1.setText("");
                    return true;
                }
                return false;
            }
        });
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

    public void onClickTeamSReady(ArrayList<Team> array){
        Exchange.teams = array;
        //return Exchange.teams;
    }

    public ArrayList<Team> convertToTeams()
    {
        ArrayList<Team> teamsNew = new ArrayList<Team>();
        for(int i = 0 ; i < teams.size(); i++)
        {
            Team t = new Team();
            t.setTeamId(i);
            t.setTeamName(teams.get(i));
            teamsNew.add(t);
        }
        return teamsNew;
    }
    public void onClickBackToGame (View view) {
        Intent intent = new Intent(Edit_Teams.this, GameActivity.class);
        //intent.putExtra("arrayTeams",array);
        onClickTeamSReady(convertToTeams());
        //Log.

        startActivity(intent);
    }
}
