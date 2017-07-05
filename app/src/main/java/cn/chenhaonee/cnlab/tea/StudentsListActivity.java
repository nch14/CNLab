package cn.chenhaonee.cnlab.tea;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.Student;
import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.vo.Exam;
import okhttp3.Request;
import okhttp3.Response;

public class StudentsListActivity extends Activity {

    private ListView listView;
    private StudentsAdapter mAdapter;
    private List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        listView = (ListView) findViewById(R.id.listView);
        int id = getIntent().getExtras().getInt("id");
        new LoadAsyncTask().execute(HttpHelper.getTeaGetStuList(id));
    }

    private void updateView() {
        mAdapter = new StudentsAdapter(data);
        listView.setAdapter(mAdapter);
    }


    class LoadAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            Request request = HttpHelper.builder().url(params[0]).build();
            Response response = HttpHelper.takeTask(request);
            if (response.isSuccessful()) {
                try {
                    String result = response.body().string();
                    ObjectMapper objectMapper = new ObjectMapper();
                    data = objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean response) {
            if (response)
                updateView();
        }
    }

    class StudentsAdapter extends ArrayAdapter<Student> {

        public StudentsAdapter(List<Student> items) {
            super(StudentsListActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null)
                convertView = StudentsListActivity.this.getLayoutInflater().inflate(R.layout.list_item_student, null);
            Student student = getItem(position);

            TextView taskStartAt = (TextView) convertView.findViewById(R.id.name_id_gender);
            taskStartAt.setText(student.getName() + "(" + student.getId() + ")" + "," + student.getGender());

            TextView taskId = (TextView) convertView.findViewById(R.id.username);
            taskId.setText("Username:" + student.getUsername());

            TextView taskName = (TextView) convertView.findViewById(R.id.git_id);
            taskName.setText("GitId:" + student.getGitId());

            TextView taskDescription = (TextView) convertView.findViewById(R.id.mail);
            taskDescription.setText("EMail:" + student.getEmail());

            TextView taskStatus = (TextView) convertView.findViewById(R.id.school_id);
            taskStatus.setText("SchoolId:" + student.getSchoolId());

            TextView groupId = (TextView) convertView.findViewById(R.id.group_id);
            groupId.setText("GroupId:" + student.getGroupId());
            return convertView;
        }
    }
}
