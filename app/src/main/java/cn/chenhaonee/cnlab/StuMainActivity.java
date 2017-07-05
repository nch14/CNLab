package cn.chenhaonee.cnlab;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

import cn.chenhaonee.cnlab.stu.ContentFragment;

public class StuMainActivity extends Activity {

    private ContentFragment currentContentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        currentContentFragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                currentContentFragment = ContentFragment.newInstance(1);
                break;
            case R.id.navigation_dashboard:
                currentContentFragment = ContentFragment.newInstance(2);
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.content, currentContentFragment).commit();
        return true;
    };

    private ArrayAdapter<String> mTaskTypeAdapter;

    private String[] mItems = new String[]{"考试", "作业", "练习"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        mTaskTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        mTaskTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setAdapter(mTaskTypeAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentContentFragment.update(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
