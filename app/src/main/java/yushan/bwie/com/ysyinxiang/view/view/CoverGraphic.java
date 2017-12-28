package yushan.bwie.com.ysyinxiang.view.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import yushan.bwie.com.ysyinxiang.R;

/**
 * 类的用途： 发表图文
 * 作者：张佳乐
 * 时间：2017/12/26AM8:40
 */


/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */
public class CoverGraphic extends AppCompatActivity {
    private ImageView fanhui;
    private TextView fabu;
    private EditText edittext;
    int mTextMaxlenght = 0;
    private RelativeLayout fmt;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;//本地
    private static final int CODE_CAMERA_REQUEST = 0xa1;//拍照
    private static final int CODE_RESULT_REQUEST = 0xa2;//最终裁剪后的结果
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 1500;
    private static int output_Y = 700;
    private ImageView headImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.covergraphic);


        initview();


        //沉浸式标题栏
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();


    }

    private void initview() {

        fanhui = (ImageView) findViewById(R.id.fanhui);
        fabu = (TextView) findViewById(R.id.fabu);
        edittext = (EditText) findViewById(R.id.edittext);
        fmt = (RelativeLayout) findViewById(R.id.fmt);
        headImage = (ImageView) findViewById(R.id.headImage);


        fmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showListDialog();

            }
        });


        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //EditText过滤器
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                mTextMaxlenght = 0;


                Editable editable = s;

                // System.out.println(editable.toString() + "    eeeeee");

                String str = s.toString().trim();
                //得到最初字段的长度大小，用于光标位置的判断

                int selEndIndex = Selection.getSelectionEnd(editable);

                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，

                //如果是汉字则为两个字符

                for (int i = 0; i < str.length(); i++) {

                    char charAt = str.charAt(i);


                    //32-122包含了空格，大小写字母，数字和一些常用的符号，

                    //如果在这个范围内则算一个字符，

                    //如果不在这个范围比如是汉字的话就是两个字符

                    if (charAt >= 32 && charAt <= 122) {

                        mTextMaxlenght++;

                    } else {

                        mTextMaxlenght += 2;

                    }


                    // 当最大字符大于160时，进行字段的截取，并进行提示字段的大小

                    if (mTextMaxlenght > 60) {

                        // 截取最大的字段

                        String newStr = str.substring(0, i);

                        edittext.setText(newStr);


                        // 设置新光标所在的位置
                        edittext.setSelection(newStr.length());

                        Toast.makeText(CoverGraphic.this, "最大长度为60个字符或30个汉字！", Toast.LENGTH_SHORT).show();
                        return;


                    }
                }

            }
        });

    }

    private void showListDialog() {
        final String[] items = new String[]{"相册", "相机", "取消"};
        new AlertDialog.Builder(CoverGraphic.this).setTitle("Please choose your choice").setIcon(R.mipmap.gongren).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case 0:
                        choseHeadImageFromGallery();
                        break;
                    case 1:

                        choseHeadImageFromCameraCapture();
                        break;
                    case 2:


                        break;
                }
            }
        }).show();


    }


    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
    // 设置文件类型
        intentFromGallery.setType("image/*");//选择图片
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
    //如果你想在Activity中得到新打开Activity关闭后返回的数据，
    //你需要使用系统提供的startActivityForResult(Intent intent,int requestCode)方法打开新的Activity
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }


    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
// 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {//取消
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST://如果是来自本地的
                cropRawPhoto(intent.getData());//直接裁剪图片
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);//设置图片框
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }


    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
         //把裁剪的数据填入里面
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 2.5);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            headImage.setImageBitmap(photo);
            //新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
            File nf = new File(Environment.getExternalStorageDirectory() + "/Ask");
            nf.mkdir();
            //在根目录下面的ASk文件夹下 创建okkk.jpg文件
            File f = new File(Environment.getExternalStorageDirectory() + "/Ask", "okkk.jpg");
            FileOutputStream out = null;
            try {
                //打开输出流 将图片数据填入文件中
                out = new FileOutputStream(f);
                photo.compress(Bitmap.CompressFormat.PNG, 90, out);
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
// 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
}

