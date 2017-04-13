package com.ninghoo.beta17ma27.weydio2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.ninghoo.beta17ma27.weydio2.R;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.View.ToolbarX;

/**
 * Created by ningfu on 17-4-11.
 */

// 用于继承的Activity。
public abstract class CommonActivity extends AppCompatActivity
{
    private RelativeLayout rlContent;
    private Toolbar toolbar;
    private ToolbarX mToolbarX;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.ac_baselayout);

        initialize();

        View v= getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(v);
        mToolbarX = new ToolbarX(toolbar,this);
    }

    public  abstract  int getLayoutId();

    @Override
    public void startActivity(Intent intent)
    {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anmi_in_right_left,R.anim.anmi_out_right_left);
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.anim_in_left_right,R.anim.anit_out_left_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.anmi_in_right_left, R.anim.anmi_out_right_left);
    }

    private void initialize()
    {

        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


    }
    public  ToolbarX getToolbar()
    {
        if(null==mToolbarX){
            mToolbarX = new ToolbarX(toolbar,this);
        }
        return mToolbarX;
    }
}
