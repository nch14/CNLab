package cn.chenhaonee.cnlab.stu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.vo.QuestionResult;
import cn.chenhaonee.cnlab.vo.StudentItem;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenh on 2016/8/1.
 */
public class ReadmePage extends ContentFragment {

    private ListView mClassrooms;
    private TaskAdapter mAdpater;
    private List<QuestionResult> data;

    public ReadmePage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_analyze, container, false);

        mClassrooms = (ListView) rootView.findViewById(R.id.listView);
        mClassrooms.setEmptyView(rootView.findViewById(R.id.emptyView));

        EditText assignmentIdView = (EditText) rootView.findViewById(R.id.assignment_id_input);

        EditText studentIdView = (EditText) rootView.findViewById(R.id.student_id_input);

        Button button = (Button) rootView.findViewById(R.id.search_button);
        button.setOnClickListener(v -> {
            try {
                int assignmentId = Integer.parseInt(assignmentIdView.getText().toString());
                int studentId = Integer.parseInt(studentIdView.getText().toString());
                new LoadAsyncTask().execute(HttpHelper.getStuAnalyze(assignmentId, studentId));
            } catch (Exception e) {
                Toast.makeText(getActivity(), "请检查输入参数", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private void updateView() {
        mAdpater = new TaskAdapter(data);
        mClassrooms.setAdapter(mAdpater);
        mClassrooms.setOnItemClickListener((parent, view, position, id) -> {

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
                    data = objectMapper.readValue(result, StudentItem.class).getQuestionResults();
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

    class TaskAdapter extends ArrayAdapter<QuestionResult> {

        public TaskAdapter(List<QuestionResult> items) {
            super(getActivity(), 0, items);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_question_result, null);
            QuestionResult questionResult = getItem(position);

            TextView titleBindId = (TextView) convertView.findViewById(R.id.title_id);
            titleBindId.setText(questionResult.getQuestionTitle() + "(" + questionResult.getQuestionId() + ")");

            //ScoreResult

            TextView taskId = (TextView) convertView.findViewById(R.id.git_url_score_result);
            taskId.setText("GitURL:" + questionResult.getScoreResult().getGit_url());

            TextView taskName = (TextView) convertView.findViewById(R.id.score);
            taskName.setText("Score:" + questionResult.getScoreResult().getScore());

            CheckBox taskDescription = (CheckBox) convertView.findViewById(R.id.scored);
            taskDescription.setChecked(questionResult.getScoreResult().isScored());

            //MetricData
            TextView taskStatus = (TextView) convertView.findViewById(R.id.git_url_metric_data);
            taskStatus.setText("GitURL:" + questionResult.getMetricData().getGit_url());

            CheckBox measured = (CheckBox) convertView.findViewById(R.id.measured);
            measured.setChecked(questionResult.getMetricData().isMeasured());

            TextView total_line_count = (TextView) convertView.findViewById(R.id.total_line_count);
            total_line_count.setText("TotalLineCount:" + questionResult.getMetricData().getTotal_line_count());

            TextView comment_line_count = (TextView) convertView.findViewById(R.id.comment_line_count);
            comment_line_count.setText("CommentLineCount:" + questionResult.getMetricData().getTotal_line_count());

            TextView field_count = (TextView) convertView.findViewById(R.id.field_count);
            field_count.setText("CommentLineCount:" + questionResult.getMetricData().getTotal_line_count());

            TextView method_count = (TextView) convertView.findViewById(R.id.method_count);
            method_count.setText("CommentLineCount:" + questionResult.getMetricData().getTotal_line_count());

            TextView max_coc = (TextView) convertView.findViewById(R.id.max_coc);
            max_coc.setText("CommentLineCount:" + questionResult.getMetricData().getTotal_line_count());

            //TestResult
            TextView git_url_test_result = (TextView) convertView.findViewById(R.id.git_url_test_result);
            git_url_test_result.setText("GitURL:" + questionResult.getTestResult().getGit_url());

            CheckBox compile_succeeded = (CheckBox) convertView.findViewById(R.id.compile_succeeded);
            compile_succeeded.setChecked(questionResult.getTestResult().isCompile_succeeded());

            CheckBox tested = (CheckBox) convertView.findViewById(R.id.tested);
            tested.setChecked(questionResult.getTestResult().isCompile_succeeded());

            return convertView;
        }
    }
}
