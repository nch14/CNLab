package cn.chenhaonee.cnlab.tea;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.vo.Exam;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenh on 2016/8/1.
 */
public class TeacherTaskPage extends ContentFragment {

    private ListView mClassrooms;
    private TaskAdapter mAdpater;
    private List<Exam> data;

    public TeacherTaskPage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_open_class_list, container, false);

        mClassrooms = (ListView) rootView.findViewById(R.id.listView);
        mClassrooms.setEmptyView(rootView.findViewById(R.id.emptyView));

        new LoadAsyncTask().execute(HttpHelper.getStuCheckExamListUrl());


        return rootView;
    }

    private void updateView() {
        mAdpater = new TaskAdapter(data);
        mClassrooms.setAdapter(mAdpater);
        mClassrooms.setOnItemClickListener((parent, view, position, id) -> {
          /*  Intent intent = new Intent(getActivity(), ClassroomDetailActivity.class);
            startActivity(intent);*/
            Exam exam = data.get(position);
            Toast.makeText(getActivity(), "This is "+ exam.getId(), Toast.LENGTH_SHORT).show();
        });
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
                    data = objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, Exam.class));
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

    class TaskAdapter extends ArrayAdapter<Exam> {

        public TaskAdapter(List<Exam> items) {
            super(getActivity(), 0, items);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_classroom, null);
            Exam exam = getItem(position);

            TextView taskStartAt = (TextView) convertView.findViewById(R.id.task_startAt);
            taskStartAt.setText("开始时间:" + exam.getStartAt());

            TextView taskId = (TextView) convertView.findViewById(R.id.task_id);
            taskId.setText("ID:" + exam.getId());

            TextView taskName = (TextView) convertView.findViewById(R.id.task_name);
            taskName.setText(exam.getTitle());

            TextView taskDescription = (TextView) convertView.findViewById(R.id.task_description);
            taskDescription.setText("介绍:" + exam.getDescription());

            TextView taskStatus = (TextView) convertView.findViewById(R.id.task_status);
            taskStatus.setText("状态:" + exam.getStatus());

            return convertView;
        }
    }

    @Override
    public void update(int taskType) {
        switch (taskType) {
            case 0:
                new LoadAsyncTask().execute(HttpHelper.getStuCheckExamListUrl());
                break;
            case 1:
                new LoadAsyncTask().execute(HttpHelper.getStuCheckHomeworkListUrl());
                break;
            case 2:
                new LoadAsyncTask().execute(HttpHelper.getStuCheckExerciseListUrl());
                break;
            default:
                break;
        }
    }
}
