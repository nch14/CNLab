package cn.chenhaonee.cnlab.tea;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import cn.chenhaonee.cnlab.R;
import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.vo.TeachingClass;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenh on 2016/8/1.
 */
public class AllClassPage extends ContentFragment {

    private ListView mClassrooms;
    private ArrayAdapter<String> mAdapter;
    private List<TeachingClass> datas;

    public AllClassPage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_open_class_list, container, false);

        mClassrooms = (ListView) rootView.findViewById(R.id.listView);
        mClassrooms.setEmptyView(rootView.findViewById(R.id.emptyView));

        new LoadAsyncTask().execute(HttpHelper.getTeaGetAllClass());
        return rootView;
    }

    private void updateView() {
        List<String> data = this.datas.stream().map(TeachingClass::getName).collect(Collectors.toList());
        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data);
        mClassrooms.setAdapter(mAdapter);
        mClassrooms.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), StudentsListActivity.class);
            intent.putExtra("id", datas.get(position).getId());
            startActivity(intent);
            String name = data.get(position);
            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
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
                    datas = objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, TeachingClass.class));
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

}
