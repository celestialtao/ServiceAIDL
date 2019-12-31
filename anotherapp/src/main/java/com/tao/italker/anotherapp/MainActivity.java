package com.tao.italker.anotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import com.tao.italker.startservicefromanotherapp.IAppServiceRemoteBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent mIntentService;
    private EditText mEtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtInput = (EditText) findViewById(R.id.etInput);
        mIntentService = new Intent();
        mIntentService.setComponent(new ComponentName("com.tao.italker.startservicefromanotherapp",
                "com.tao.italker.startservicefromanotherapp.AppService"));
        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_Sync).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(mIntentService);
                break;
            case R.id.btn_stop_service:
                stopService(mIntentService);
                break;
            case R.id.btn_bind_service:
                bindService(mIntentService,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(this);
                mBinder = null;
                break;
            case R.id.btn_Sync:
                if(mBinder != null){
                    //远程通信需要捕获异常，跨进程通信
                    try {
                        mBinder.setData(mEtInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Bind Service");
        System.out.println(service);
 //       mBinder = (IAppServiceRemoteBinder) service; //强制类型转换为AIDL定义的接口，此处出错无法使用
        mBinder = IAppServiceRemoteBinder.Stub.asInterface(service);//通过此方法将服务返回的IBinder转换为AIDL定义的Binder接口
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private IAppServiceRemoteBinder mBinder = null;
}
