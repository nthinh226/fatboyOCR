package com.phungtsm.test.fatboyOCR.api;

import com.phungtsm.test.fatboyOCR.Currency;
import com.phungtsm.test.fatboyOCR.ImageUtil;
import com.phungtsm.test.fatboyOCR.Post;
import com.phungtsm.test.fatboyOCR.result.CCCD_Info;
import com.phungtsm.test.fatboyOCR.result.CMND_Info;
import com.phungtsm.test.fatboyOCR.result.PP_Info;
import com.phungtsm.test.fatboyOCR.result.ResponCCCD;
import com.phungtsm.test.fatboyOCR.result.ResponCMND;
import com.phungtsm.test.fatboyOCR.result.ResponPP;
import com.phungtsm.test.fatboyOCR.result.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
//    @Headers({"Accept: application/json"})
    @POST("api/v1/vision/vnm/login")
    Call<Currency> getResult(@Body Post post);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/v1/vision/vnm/idr")
    Call<ResponCCCD> getCCCDInfo(@Body ImageUtil imageUtil, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/v1/vision/vnm/idr")
    Call<ResponCMND> getCMNDInfo(@Body ImageUtil imageUtil, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/v1/vision/vnm/idr")
    Call<ResponPP> getPPInfo(@Body ImageUtil imageUtil, @Header("Authorization") String auth);
}
