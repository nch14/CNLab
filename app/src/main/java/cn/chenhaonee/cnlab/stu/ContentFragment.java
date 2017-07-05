package cn.chenhaonee.cnlab.stu;

/**
 * Created by chenh on 2016/7/2.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.chenhaonee.cnlab.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContentFragment extends Fragment {

    public ContentFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ContentFragment newInstance(int sectionNumber) {
        ContentFragment fragment;
        switch (sectionNumber) {
            case 1:
                fragment = new TeacherTaskPage();
                break;
            case 2:
                fragment = new StudentAnalyzePage();
                break;
            default:
                fragment = new ContentFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        return rootView;
    }

    public void update(int taskType){

    }
}





