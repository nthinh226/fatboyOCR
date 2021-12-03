package com.phungtsm.test.fatboyOCR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phungtsm.test.fatboyOCR.api.APIClient;
import com.phungtsm.test.fatboyOCR.api.APIInterface;
import com.phungtsm.test.fatboyOCR.result.CCCD_Info;
import com.phungtsm.test.fatboyOCR.result.CMND_Info;
import com.phungtsm.test.fatboyOCR.result.PP_Info;
import com.phungtsm.test.fatboyOCR.result.ResponCCCD;
import com.phungtsm.test.fatboyOCR.result.ResponCMND;
import com.phungtsm.test.fatboyOCR.result.ResponPP;
import com.phungtsm.test.fatboyOCR.result.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvSoCMND,tvHoTenCMND,tvNgaySinhCMND,tvQueQuanCMND,tvThuongTruCMND;
    private TextView tvSoCCCD, tvHoTenCCCD, tvNgaySinhCCCD, tvGioiTinhCCCD, tvQueQuanCCCD, tvThuongTruCCCD, tvQuocTichCCCD, tvThoiHanCCCD;
    private TextView tvSoPP, tvHoTenPP, tvNgaySinhPP, tvGioiTinhPP, tvSoGCMND, tvQueQuanPP, tvQuocTichPP, tvLoaiPP, tvThoiHanPP;
    private LinearLayout pp_layout, cccd_layout, cmnd_layout;


    APIInterface apiInterface;
    ImageUtil imageUtil;
    private int flag;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pp_layout = findViewById(R.id.pp_layout);
        cccd_layout = findViewById(R.id.cccd_layout);
        cmnd_layout = findViewById(R.id.cmnd_layout);

        tvSoCMND = findViewById(R.id.tv_so_cmnd);
        tvHoTenCMND = findViewById(R.id.tv_ho_ten_cmnd);
        tvNgaySinhCMND = findViewById(R.id.tv_ngay_sinh_cmnd);
        tvQueQuanCMND = findViewById(R.id.tv_que_quan_cmnd);
        tvThuongTruCMND = findViewById(R.id.tv_thuong_tru_cmnd);

        tvSoCCCD = findViewById(R.id.tv_so_cccd);
        tvHoTenCCCD = findViewById(R.id.tv_ho_ten_cccd);
        tvNgaySinhCCCD = findViewById(R.id.tv_ngay_sinh_cccd);
        tvGioiTinhCCCD = findViewById(R.id.tv_gioi_tinh_cccd);
        tvQueQuanCCCD = findViewById(R.id.tv_que_quan_cccd);
        tvThuongTruCCCD =  findViewById(R.id.tv_thuong_tru_cccd);
        tvQuocTichCCCD = findViewById(R.id.tv_quoc_tich_cccd);
        tvThoiHanCCCD = findViewById(R.id.tv_thoi_han_cccd);

        tvSoPP = findViewById(R.id.tv_so_pp);
        tvHoTenPP = findViewById(R.id.tv_ho_ten_pp);
        tvNgaySinhPP = findViewById(R.id.tv_ngay_sinh_pp);
        tvGioiTinhPP = findViewById(R.id.tv_gioi_tinh_pp);
        tvSoGCMND = findViewById(R.id.tv_so_gcmnd);
        tvQueQuanPP = findViewById(R.id.tv_que_quan_pp);
        tvLoaiPP = findViewById(R.id.tv_loai_pp);
        tvThoiHanPP = findViewById(R.id.tv_thoi_han_pp);
        tvQuocTichPP = findViewById(R.id.tv_quoc_tich_pp);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Log.e("onCreate: ", String.valueOf(MainActivity.Flag));
        flag = MainActivity.Flag;
        imageUtil = Camera.imageUtil;
        getPersonalInfo();

    }

    private void getPersonalInfo() {
        Post post = new Post("password","OCR_TEST","jr67gj0h76gr83nf8734nj59g4he895jh87nr","PWD@123","khanhpx@mobile-id.vn");
        Call<Currency> call1 = apiInterface.getResult(post);
        call1.enqueue(new Callback<Currency>() {

            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity2.this,"Call API Success",Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                accessToken = currency.getAccess_token();
                if(currency != null){
                    if(flag==1){
                        cmnd_layout.setVisibility(View.VISIBLE);
                        Call<ResponCMND> callCMND = apiInterface.getCMNDInfo(imageUtil,"Bearer "+accessToken);
                        callCMND.enqueue(new Callback<ResponCMND>() {
                            @Override
                            public void onResponse(Call<ResponCMND> call, Response<ResponCMND> response) {
                                Toast.makeText(MainActivity2.this,"get CMND Info success",Toast.LENGTH_SHORT).show();
                                ResponCMND responCMND = response.body();
                                CMND_Info cmnd_info = responCMND.getData();
                                tvSoCMND.setText(cmnd_info.getId_number());
                                tvHoTenCMND.setText(cmnd_info.getName());
                                tvNgaySinhCMND.setText(cmnd_info.getDob());
                                tvQueQuanCMND.setText(cmnd_info.getHome());
                                tvThuongTruCMND.setText(cmnd_info.getAddress());
                            }

                            @Override
                            public void onFailure(Call<ResponCMND> call, Throwable t) {
                                Toast.makeText(MainActivity2.this,"Call CMND Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if(flag ==2){
                        cccd_layout.setVisibility(View.VISIBLE);
                        Call<ResponCCCD> callCCCD = apiInterface.getCCCDInfo(imageUtil,"Bearer "+accessToken);
                        callCCCD.enqueue(new Callback<ResponCCCD>() {
                            @Override
                            public void onResponse(Call<ResponCCCD> call, Response<ResponCCCD> response) {
                                Toast.makeText(MainActivity2.this,"get CCCD Info success",Toast.LENGTH_SHORT).show();
                                ResponCCCD responCCCD = response.body();
                                CCCD_Info cccd_info = responCCCD.getData();
                                tvSoCCCD.setText(cccd_info.getId_number());
                                tvHoTenCCCD.setText(cccd_info.getName());
                                tvNgaySinhCCCD.setText(cccd_info.getDob());
                                tvGioiTinhCCCD.setText(cccd_info.getGender());
                                tvQueQuanCCCD.setText(cccd_info.getHome());
                                tvThuongTruCCCD.setText(cccd_info.getAddress());
                                tvQuocTichCCCD.setText(cccd_info.getNationality());
                                tvThoiHanCCCD.setText(cccd_info.getDoe());
                            }

                            @Override
                            public void onFailure(Call<ResponCCCD> call, Throwable t) {
                                Toast.makeText(MainActivity2.this,"Call CCCD Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if(flag ==3){
                        pp_layout.setVisibility(View.VISIBLE);

                        Call<ResponPP> callCCCD = apiInterface.getPPInfo(imageUtil,"Bearer "+accessToken);
                        callCCCD.enqueue(new Callback<ResponPP>() {
                            @Override
                            public void onResponse(Call<ResponPP> call, Response<ResponPP> response) {
                                Toast.makeText(MainActivity2.this,"get CCCD Info success",Toast.LENGTH_SHORT).show();
                                ResponPP responPP = response.body();
                                PP_Info pp_info = responPP.getData();

                                tvSoPP.setText(pp_info.getPassport_number());
                                tvHoTenPP.setText(pp_info.getName());
                                tvNgaySinhPP.setText(pp_info.getDob());
                                tvGioiTinhPP.setText(pp_info.getGender());
                                tvQueQuanPP.setText(pp_info.getPob());
                                tvLoaiPP.setText(pp_info.getType());
                                tvThoiHanPP.setText(pp_info.getDoe());
                                tvSoGCMND.setText(pp_info.getId_number());
                                tvQuocTichPP.setText(pp_info.getNationality());
                            }

                            @Override
                            public void onFailure(Call<ResponPP> call, Throwable t) {
                                Toast.makeText(MainActivity2.this,"Call PP Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }
            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity2.this,"Call API Error",Toast.LENGTH_SHORT).show();
            }

        });


    }
}