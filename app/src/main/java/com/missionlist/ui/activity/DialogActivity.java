package com.missionlist.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.missionlist.R;
import com.missionlist.model.Mission;
import com.missionlist.utils.Util;
import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;


public class DialogActivity extends Activity {
    private TextView tv_dialog_title;
    private TextView tv_dialog_action;
    private RelativeLayout rl_dialog_set_status;
    private RelativeLayout rl_dialog_delete;
    private Dialog dialog;

    private int status;
    private String ID;
    private String LOCAL_ID;
    private Mission mission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView(){
        tv_dialog_title = (TextView)findViewById(R.id.tv_dialog_title);
        tv_dialog_action = (TextView)findViewById(R.id.tv_dialog_action);
        rl_dialog_set_status = (RelativeLayout)findViewById(R.id.rl_dialog_set_status);
        rl_dialog_delete = (RelativeLayout)findViewById(R.id.rl_dialog_delete);
        final int status_done = getResources().getIntArray(R.array.status)[2];

        mission = Util.getMissionObject(getIntent());
        if (mission.getStatus() == status_done){
            tv_dialog_action.setText("Reset to in process");
        }else{
            tv_dialog_action.setText("Set to done");
        }

        rl_dialog_set_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mission.getStatus() == status_done){
                    mission.setStatus(getResources().getIntArray(R.array.status)[1]);
                    mission.pinInBackground(MListApp.GROUP_NAME,new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            setResult(Activity.RESULT_OK);
                            Util.showMessage(getApplicationContext(), "Save in local success", Toast.LENGTH_SHORT);
                            finish();
                        }
                    });

                }else{
                    mission.setStatus(getResources().getIntArray(R.array.status)[2]);
                    mission.pinInBackground(MListApp.GROUP_NAME,new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            setResult(Activity.RESULT_OK);
                            Util.showMessage(getApplicationContext(), "Save in local success", Toast.LENGTH_SHORT);
                            finish();
                        }
                    });
                }
            }
        });

        rl_dialog_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mission.getObjectId() == null){
                    mission.unpinInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                setResult(Activity.RESULT_OK);
                                Util.showMessage(getApplicationContext(), "Delete in local success", Toast.LENGTH_SHORT);
                                finish();
                            }
                        }
                    });
                }else{
                    mission.setDelete(true);
                    mission.pinInBackground(MListApp.GROUP_NAME,new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                setResult(Activity.RESULT_OK);
                                Util.showMessage(getApplicationContext(), "Delete in local success", Toast.LENGTH_SHORT);
                                finish();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
