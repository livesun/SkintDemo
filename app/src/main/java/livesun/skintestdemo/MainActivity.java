package livesun.skintestdemo;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.test_imag);
        findViewById(R.id.test_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }



    private void test(){

        try {
            Class<AssetManager> managerClass = AssetManager.class;
            AssetManager asset = managerClass.newInstance();
            Method method = managerClass.getDeclaredMethod("addAssetPath", String.class);
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "test.skin";
            method.invoke(asset,path);

            Resources resources=new Resources(asset,new DisplayMetrics(),new Configuration());
            //资源名   资源类型  皮肤包名
            int id = resources.getIdentifier("test_src", "drawable", "livesun.skintest");
            Drawable drawable = resources.getDrawable(id);
            imageView.setImageDrawable(drawable);
            Toast.makeText(this,"换肤成功",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
