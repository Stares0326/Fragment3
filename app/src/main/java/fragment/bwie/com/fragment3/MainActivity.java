package fragment.bwie.com.fragment3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int stackID1;
    private int stackID2;
    private int stackID3;
    private int stackID4;
    private FragmentManager.OnBackStackChangedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        listener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.e("qijian","backstack changed");
            }
        };
        supportFragmentManager.addOnBackStackChangedListener(listener);
    }

    private void initView() {
        Button button1 = (Button) findViewById(R.id.btn_add_frag1);
        Button button2 = (Button)  findViewById(R.id.btn_add_frag2);
        Button button3 = (Button)  findViewById(R.id.btn_add_frag3);
        Button button4 = (Button)  findViewById(R.id.btn_add_frag4);
        Button button5 = (Button) findViewById(R.id.btn_backtofrag2_0);
        Button button6 = (Button) findViewById(R.id.btn_backtofrag2_includesive);
        Button button7 = (Button) findViewById(R.id.btn_popbackstack);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_frag1:
                Fragment1 fragment1 = new Fragment1();
                stackID1 = addFragment(fragment1, "fragment1");
                break;
            case R.id.btn_add_frag2:
                Fragment2 fragment2 = new Fragment2();
                stackID2 = addFragment(fragment2, "fragment2");
                break;
            case R.id.btn_add_frag3:
                Fragment3 fragment3 = new Fragment3();
                stackID3 = addFragment(fragment3, "fragment3");
                break;
            case R.id.btn_add_frag4:
                Fragment4 fragment4 = new Fragment4();
                stackID4 = addFragment(fragment4, "fragment4");
                break;
            case R.id.btn_backtofrag2_0:
                popBackStackToFrag2_0();
                break;
            case R.id.btn_backtofrag2_includesive:
                popBackStackToFrag2_Inclusive();
                break;
            case R.id.btn_popbackstack:
                popBackStack();
                break;
        }
    }

    /*添加Fragment1*/
    private int addFragment(Fragment fragment, String stackName) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout_container, fragment);
        fragmentTransaction.addToBackStack(stackName);
        int commit = fragmentTransaction.commit();
        return commit;
    }
    private void popBackStack(){

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.popBackStack();
    }

    private void popBackStackToFrag2_0(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.popBackStack("fragment2",0);//方法一：通过TAG回退
//        supportFragmentManager.popBackStack(stackID2,0);//方法二：通过Transcation ID回退
    }

    private void popBackStackToFrag2_Inclusive(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //方法一：通过Tag回退
        supportFragmentManager.popBackStack("fragment2",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //方法二：通过Transcation ID 回退
//        supportFragmentManager.popBackStack(stackID2,FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.removeOnBackStackChangedListener(listener);

    }
}
