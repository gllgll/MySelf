package com.example.myself;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton1,imageButton2,imageButton3;
    AlertDialog.Builder abuilder,addbuilder,aboutbuilder,aboutMyself;
    RadioGroup radioGroup;
    ImageView imageView;
    TextView textViewman,textViewmessage,textViewadd;

    //菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    //菜单栏功能
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        textViewadd = findViewById(R.id.textView9);
        switch (item.getItemId()){
            case R.id.add:
                addbuilder = new AlertDialog.Builder(MainActivity.this);
                final EditText et = new EditText(this);
                addbuilder.setTitle("请输入添加的信息")
                        .setView(et)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                try {
                                    String string = et.getText().toString();
                                    Pattern pattern =Pattern.compile("[0-9]*");
                                    Matcher matcher = pattern.matcher(string);
                                    if(!matcher.matches()){
                                        textViewadd.setText(string);
                                        textViewadd.setTextColor(Color.GREEN);
                                    }
                                    else {
                                        textViewadd.setText("不能全为数字，请重新输入");
                                        textViewadd.setTextColor(Color.RED);
                                    }
                                }catch (NumberFormatException e){
                                    e.printStackTrace();
                                }
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", null).show();
                break;
            case R.id.about:
                aboutbuilder = new AlertDialog.Builder(MainActivity.this);
                final String[] items = {"加他QQ","去他GitHub仓库"};
                aboutbuilder.setTitle("关于作者")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    final String urlqq="mqqwpa://im/chat?chat_type=wpa&uin=2324942249&version=1";
                                    final AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("即将打开QQ")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                                    intent.setData(Uri.parse(urlqq));
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton("取消",null)
                                            .show();
                                }
                                if(which==1){
                                    final String urlGit = "https://github.com/gllgll/StudyFile";
                                    final AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("前往GitHub")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                                    intent.setData(Uri.parse(urlGit));
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton("取消",null)
                                            .show();
                                }
                            }
                        }).show();
                break;
            case R.id.clear:
                if (TextUtils.isEmpty(textViewadd.getText())){
                    Toast.makeText(MainActivity.this, "已经清除，别重复清除",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "清除成功",
                            Toast.LENGTH_SHORT).show();
                    textViewadd.setText("");
                }
                break;
            case R.id.aboutmyself:
                aboutMyself = new AlertDialog.Builder(MainActivity.this);
                aboutMyself.setTitle("关于本软件")
                        .setMessage("这是GLL的期中考试，是一个个人简介软件，初来乍到，多多指教。酷安by大步去往流星")
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton1 = findViewById(R.id.imageButton);//退出
        imageButton2 = findViewById(R.id.imageButton2);//基本资料
        imageButton3 = findViewById(R.id.imageButton3);//个性资料
        radioGroup = findViewById(R.id.radioGroup);
        imageView = findViewById(R.id.imageView);
        textViewman = findViewById(R.id.textView4);
        textViewmessage = findViewById(R.id.textView5);
        abuilder = new AlertDialog.Builder(MainActivity.this);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abuilder.setTitle("退出提示");
                abuilder.setMessage("您将退出软件！");
                abuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                abuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                abuilder.create().show();
                Log.i("提示","监控");
            }
    });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MainActivity.this, "如果没反应，请先给本软件赋予通知权限", Toast.LENGTH_SHORT).show();
                final NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.my32)
                        .setContentTitle("界面提示")
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(DEFAULT_KEYS_DIALER)
                        .setContentText("当前为基本资料界面");
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivities(MainActivity.this,0, new Intent[]{intent},0);
                mbuilder.setContentIntent(pendingIntent);
                mbuilder.setAutoCancel(true);
                final NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1, mbuilder.build());
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.radioButton2){
                    imageView.setImageResource(R.drawable.head);
                    textViewman.setTextColor(Color.RED);
                    textViewmessage.setTextColor(Color.RED);
                } else {
                    imageView.setImageResource(R.drawable.headman);
                    textViewman.setTextColor(Color.BLUE);
                    textViewmessage.setTextColor(Color.BLUE);
                }
            }
        });
    }
}
