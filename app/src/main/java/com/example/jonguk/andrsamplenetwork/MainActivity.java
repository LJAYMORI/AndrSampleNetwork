package com.example.jonguk.andrsamplenetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jonguk.andrsamplenetwork.json.ReposJson;
import com.example.jonguk.andrsamplenetwork.json.UserJson;
import com.example.jonguk.andrsamplenetwork.list.GithubAdapter;
import com.example.jonguk.andrsamplenetwork.network.GithubReposService;
import com.example.jonguk.andrsamplenetwork.network.GithubUserService;
import com.example.jonguk.andrsamplenetwork.network.NetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GithubAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.et_username);
        Button btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    requestItems(username);
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new GithubAdapter();
        recyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        if (intent != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            List<String> pathSegments = uri.getPathSegments();

            String username = pathSegments.get(0);
            etUsername.setText(username);
            requestItems(username);
        }
    }

    private void requestItems(String username) {
        mAdapter.clear();
        NetworkManager.getInstance().create(GithubUserService.class)
                .getUser(username)
                .enqueue(new Callback<UserJson>() {
                    @Override
                    public void onResponse(Call<UserJson> call, Response<UserJson> response) {
                        mAdapter.addUser(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserJson> call, Throwable t) {
                        showToast(t.getMessage());
                    }
                });

        NetworkManager.getInstance().create(GithubReposService.class)
                .getRepos(username)
                .enqueue(new Callback<List<ReposJson>>() {
                    @Override
                    public void onResponse(Call<List<ReposJson>> call, Response<List<ReposJson>> response) {
                        mAdapter.addRepos(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ReposJson>> call, Throwable t) {
                        showToast(t.getMessage());
                    }
                });
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
