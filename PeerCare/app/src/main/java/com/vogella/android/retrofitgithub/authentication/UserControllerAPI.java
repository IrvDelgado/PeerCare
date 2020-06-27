package com.vogella.android.retrofitgithub.authentication;

import com.vogella.android.retrofitgithub.authentication.signin.LoginPayloadRequest;
import com.vogella.android.retrofitgithub.authentication.signup.UserAddRequest;
import com.vogella.android.retrofitgithub.common.apiresponse.ApiResponse;
import com.vogella.android.retrofitgithub.common.user.User;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserControllerAPI {
    String ENDPOINT = "http://192.168.1.41:8080/api/users/";

    @POST("login")
    Single<ApiResponse> login(@Body LoginPayloadRequest loginPayloadRequest);

    @POST("add")
    Single<User> addUser(@Body UserAddRequest userAddRequest);
}
