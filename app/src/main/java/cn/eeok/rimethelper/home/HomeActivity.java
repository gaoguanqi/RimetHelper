package cn.eeok.rimethelper.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.eeok.rimethelper.R;
import cn.eeok.rimethelper.base.BaseActivity;
import cn.eeok.rimethelper.event.Events;
import cn.eeok.rimethelper.event.RxBus;
import cn.eeok.rimethelper.utils.LogUtil;
import cn.eeok.rimethelper.utils.SPUtils;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/5.
 */
public class HomeActivity extends BaseActivity implements OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.drawerContent)
    LinearLayout drawerContent;

    TimePickerDialog mDialogHourMinute;
    //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sf = new SimpleDateFormat("HH:mm");


    @BindView(R.id.textOpenTime1)
    TextView textOpenTime1;
    @BindView(R.id.textCloseTime1)
    TextView textCloseTime1;

    @BindView(R.id.textOpenTime2)
    TextView textOpenTime2;
    @BindView(R.id.textCloseTime2)
    TextView textCloseTime2;

    @BindView(R.id.textOpenTime3)
    TextView textOpenTime3;
    @BindView(R.id.textCloseTime3)
    TextView textCloseTime3;


    @BindView(R.id.textHome)
    TextView textHome;

    private SPUtils mSP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        initEvent();
    }



    private void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
            }
        });
        //添加溢出菜单
        toolbar.inflateMenu(R.menu.menu_main);
        // 添加菜单点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        //点击设置菜单
                        break;
                }
                return false;
            }
        });

        // 使用ActionBarDrawerToggle，配合DrawerLayout和ActionBar,以实现推荐的抽屉功能。
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void initData() {
        mSP = new SPUtils("time");
        String openTime1 = mSP.getString("openTime1");
        String closeTime1 = mSP.getString("closeTime1");

        String openTime2 = mSP.getString("openTime2");
        String closeTime2 = mSP.getString("closeTime2");

        String openTime3 = mSP.getString("openTime3");
        String closeTime3 = mSP.getString("closeTime3");


        if(!TextUtils.isEmpty(openTime1) && !TextUtils.isEmpty(closeTime1) && !TextUtils.isEmpty(openTime2) && !TextUtils.isEmpty(closeTime2) && !TextUtils.isEmpty(openTime3) && !TextUtils.isEmpty(closeTime3)){
            textHome.setText("第一次:   "+openTime1 +"---"+closeTime1+"\n第二次:   "+openTime2 +"---"+closeTime2+"\n第三次:   "+openTime3 +"---"+closeTime3);
        }else{
            Toast.makeText(getApplicationContext(), "请先设置时间", Toast.LENGTH_LONG).show();
            drawerLayout.openDrawer(drawerContent);
        }
    }



    private void initEvent() {

        RxBus.getInstance().toObserverable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof Events) {
                            if (((Events) o).code == Events.EVENT_CODE_TAP) {
                                startApp((Events) o);
                            } else if (((Events) o).code == Events.EVENT_CODE_OTHER) {
                                stopApp((Events) o);
                            }
                        }
                    }
                });

        mDialogHourMinute = new TimePickerDialog.Builder()
                .setType(Type.HOURS_MINS)
                .setCallBack(this)
                .build();
    }

    private void stopApp(Events o) {
        LogUtil.e("TAG", o.getContent() + "");
        final Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void startApp(Events o) {
        LogUtil.e("TAG", o.getContent() + "");
        // 通过包名获取要跳转的app，创建intent对象
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.alibaba.android.rimet");
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛
        if (intent != null) {
            startActivity(intent);
        } else {
            // 没有安装要跳转的app应用，提醒一下
            Toast.makeText(getApplicationContext(), "没有找到要启动的 intent", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({R.id.btnOpen1, R.id.btnClose1,R.id.btnOpen2, R.id.btnClose2,R.id.btnOpen3, R.id.btnClose3,R.id.btnSave})
    public void onClick(View view) {
        Bundle mBundle = null;
        switch (view.getId()) {
            case R.id.btnOpen1:
                mBundle = new Bundle();
                mBundle.putInt("flag",11);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "打开");
                break;
            case R.id.btnClose1:
                mBundle = new Bundle();
                mBundle.putInt("flag",12);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "关闭");
                break;

            case R.id.btnOpen2:
                mBundle = new Bundle();
                mBundle.putInt("flag",21);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "打开");
                break;
            case R.id.btnClose2:
                mBundle = new Bundle();
                mBundle.putInt("flag",22);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "关闭");
                break;
            case R.id.btnOpen3:
                mBundle = new Bundle();
                mBundle.putInt("flag",31);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "打开");
                break;
            case R.id.btnClose3:
                mBundle = new Bundle();
                mBundle.putInt("flag",32);
                mDialogHourMinute.setArguments(mBundle);
                mDialogHourMinute.show(getSupportFragmentManager(), "关闭");
                break;

            case R.id.btnSave:
                String openTime1 = textOpenTime1.getText().toString();
                String closeTime1 = textCloseTime1.getText().toString();

                String openTime2 = textOpenTime2.getText().toString();
                String closeTime2 = textCloseTime2.getText().toString();

                String openTime3 = textOpenTime3.getText().toString();
                String closeTime3 = textCloseTime3.getText().toString();

                if(!TextUtils.isEmpty(openTime1) && !TextUtils.isEmpty(closeTime1) && !TextUtils.isEmpty(openTime2) && !TextUtils.isEmpty(closeTime2) && !TextUtils.isEmpty(openTime3) && !TextUtils.isEmpty(closeTime3)){
                    mSP.put("openTime1",openTime1);
                    mSP.put("closeTime1",closeTime1);

                    mSP.put("openTime2",openTime2);
                    mSP.put("closeTime2",closeTime2);

                    mSP.put("openTime3",openTime3);
                    mSP.put("closeTime3",closeTime3);



                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("提示:")
                            .setContentText("设置成功!")
                            .setConfirmText("确定!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    initData();
                                    sDialog.dismissWithAnimation();
                                    drawerLayout.closeDrawers();
                                }
                            })
                            .show();

                }else{
                    Toast.makeText(getApplicationContext(), "time empty", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String text = getDateToString(millseconds);
        Bundle mBundle = timePickerView.getArguments();
        int flag = mBundle.getInt("flag");
        if(flag == 11){
            textOpenTime1.setText(text);
        }else if(flag == 12){
            textCloseTime1.setText(text);
        }else if(flag == 21){
            textOpenTime2.setText(text);
        }else if(flag == 22){
            textCloseTime2.setText(text);
        }else if(flag == 31){
            textOpenTime3.setText(text);
        }else if(flag == 32){
            textCloseTime3.setText(text);
        }else{
            Toast.makeText(getApplicationContext(), "flag error", Toast.LENGTH_LONG).show();
        }
    }


    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
