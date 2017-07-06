package cn.chenhaonee.cnlab.tea;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.vo.Question;

public class QuestionsListActivity extends Activity {

    private ListView listView;
    private QuestionsAdapter mAdapter;
    private List<Question> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("问题列表");
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        questions = (List<Question>) getIntent().getExtras().getSerializable("Question");


        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.emptyView));
        mAdapter = new QuestionsAdapter(questions);
        listView.setAdapter(mAdapter);
    }

    class QuestionsAdapter extends ArrayAdapter<Question> {

        public QuestionsAdapter(List<Question> items) {
            super(QuestionsListActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null)
                convertView = QuestionsListActivity.this.getLayoutInflater().inflate(R.layout.list_item_detail_question, null);
            Question q = getItem(position);

            TextView taskStartAt = (TextView) convertView.findViewById(R.id.title_id);
            taskStartAt.setText(q.getTitle() + "(" + q.getId() + ")");

            TextView taskId = (TextView) convertView.findViewById(R.id.description);
            taskId.setText("描述:" + q.getDescription());

            TextView taskName = (TextView) convertView.findViewById(R.id.type);
            taskName.setText("类型:" + q.getType());

            TextView taskDescription = (TextView) convertView.findViewById(R.id.difficulty);
            taskDescription.setText("难度:" + q.getDifficulty());

            TextView taskStatus = (TextView) convertView.findViewById(R.id.git_url);
            taskStatus.setText("Git地址:" + q.getGitUrl());

            TextView groupId = (TextView) convertView.findViewById(R.id.duration);
            groupId.setText("Duration:" + q.getDuration());

            TextView link = (TextView) convertView.findViewById(R.id.link);
            link.setText("链接:" + q.getLink());

            TextView id = (TextView) convertView.findViewById(R.id.id);
            id.setText("ID:" + q.getCreator().getId());

            TextView username = (TextView) convertView.findViewById(R.id.name);
            username.setText("姓名:" + q.getCreator().getName());

            TextView usernameCreator = (TextView) convertView.findViewById(R.id.username_creator);
            usernameCreator.setText("用户名:" + q.getCreator().getUsername());

            TextView typeCreator = (TextView) convertView.findViewById(R.id.type_creator);
            typeCreator.setText("用户类型:" + q.getCreator().getType());

            TextView gender = (TextView) convertView.findViewById(R.id.gender);
            gender.setText("性别:" + q.getCreator().getGender());

            TextView email = (TextView) convertView.findViewById(R.id.email);
            email.setText("用户类型:" + q.getCreator().getEmail());
            return convertView;
        }
    }
}
