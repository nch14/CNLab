package cn.chenhaonee.cnlab.tea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.vo.SimpleStudent;

public class StudentScoreListActivity extends Activity {

    private RecyclerView mStudentScoreList;
    private StudentScoreListAdapter mAdapter;
    private List<SimpleStudent> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("成绩列表");
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();

        data = (List<SimpleStudent>) intent.getExtras().getSerializable("list");
        mStudentScoreList = (RecyclerView) findViewById(R.id.student_score_list);
        // Create adapter passing in the sample user data
        mAdapter = new StudentScoreListAdapter(this, data);
        // Attach the adapter to the recyclerview to populate items
        mStudentScoreList.setAdapter(mAdapter);
        // Set layout manager to position the items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mStudentScoreList.setLayoutManager(layoutManager);
        /*// Add the scroll listener
        mBlogList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
            }
        });*/

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mStudentScoreList.addItemDecoration(itemDecoration);
    }


    // Create the basic adapter extending from RecyclerView.Adapter
    // Note that we specify the custom ViewHolder which gives us access to our views
    private class StudentScoreListAdapter extends
            RecyclerView.Adapter<StudentScoreListAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row

            public TextView id;

            private TextView studentName;

            private TextView studentNumber;

            private TextView score;

            private CheckBox scored;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                id = (TextView) itemView.findViewById(R.id.student_id);
                studentName = (TextView) itemView.findViewById(R.id.student_name);
                studentNumber = (TextView) itemView.findViewById(R.id.student_number);
                score = (TextView) itemView.findViewById(R.id.score);
                scored = (CheckBox) itemView.findViewById(R.id.scored);
            }
        }

        // Store a member variable for the contacts
        private List<SimpleStudent> mStudents;
        // Store the context for easy access
        private Context mContext;

        // Pass in the contact array into the constructor
        public StudentScoreListAdapter(Context context, List<SimpleStudent> students) {
            mStudents = students;
            mContext = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public StudentScoreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.list_item_student_score, parent, false);

            // Return a new holder instance
            StudentScoreListAdapter.ViewHolder viewHolder = new StudentScoreListAdapter.ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(StudentScoreListAdapter.ViewHolder holder, int position) {

            // Set item views based on your views and data model
            SimpleStudent student = mStudents.get(position);
            holder.id.setText(""+student.getStudentId());
            holder.studentName.setText(student.getStudentName());
            holder.studentNumber.setText(student.getStudentNumber());
            holder.score.setText(""+student.getScore());
            holder.scored.setChecked(student.isScored());
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return mStudents.size();
        }


    }

}
