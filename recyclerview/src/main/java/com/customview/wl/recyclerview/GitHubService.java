package com.customview.wl.recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author wl
 * @date 2017/3/3
 * @content
 */

public interface GitHubService {

    @GET("api/cook/classify")
    Call<String> listRepos();
}
