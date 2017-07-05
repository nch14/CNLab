package cn.chenhaonee.cnlab.tea;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.vo.Assignment;
import cn.chenhaonee.cnlab.vo.QuestionInfo;
import cn.chenhaonee.cnlab.vo.SimpleQuestions;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenh on 2016/8/1.
 */
public class MarksPage extends ContentFragment {

    private ListView mQuestions;
    private MarksAdapter mAdapter;
    private List<SimpleQuestions> data;

    private Button mButton;
    private EditText mEditText;

    public MarksPage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_questions, container, false);

        mQuestions = (ListView) rootView.findViewById(R.id.listView);

        mButton = (Button) rootView.findViewById(R.id.search_button);
        mEditText = (EditText) rootView.findViewById(R.id.assignment_id_input);
        mButton.setOnClickListener(v -> {
            String text = mEditText.getText().toString();
            if (text != null)
                new LoadAsyncTask().execute(HttpHelper.getTeaGetAllScore(Integer.parseInt(text)));
        });


        return rootView;
    }

    private void updateView() {
        mAdapter = new MarksAdapter(data);
        mQuestions.setAdapter(mAdapter);
        mQuestions.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(),StudentScoreListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", (Serializable) data.get(position).getStudents());
            intent.putExtras(bundle);
            startActivity(intent);
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
                    data = objectMapper.readValue(result, Assignment.class).getQuestions();
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

    class MarksAdapter extends ArrayAdapter<SimpleQuestions> {

        public MarksAdapter(List<SimpleQuestions> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_questions, null);
            SimpleQuestions questions = getItem(position);
            QuestionInfo questionInfo = questions.getQuestionInfo();

            TextView taskStartAt = (TextView) convertView.findViewById(R.id.title_id);
            taskStartAt.setText(questionInfo.getTitle() + "(" + questionInfo.getId() + ")");

            TextView taskId = (TextView) convertView.findViewById(R.id.description);
            taskId.setText(questionInfo.getDescription());

            TextView taskName = (TextView) convertView.findViewById(R.id.type);
            taskName.setText("类型：" + questionInfo.getType());

            return convertView;
        }
    }

}
