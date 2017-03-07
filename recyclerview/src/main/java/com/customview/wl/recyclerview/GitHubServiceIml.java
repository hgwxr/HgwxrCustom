package com.customview.wl.recyclerview;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * @author wl
 * @date 2017/3/3
 * @content
 */

public class GitHubServiceIml implements GitHubService {
    @Override
    public Call<String> listRepos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<String> listCall = service.listRepos();
        return listCall;
    }
}
