package io.batcloud.controller;

import com.batmobi.dataxsync.common.utils.OkHTTPUtil;
import com.batmobi.dataxsync.common.utils.OkHttpResp;
import io.batcloud.dto.common.BaseResponse;
import io.batcloud.model.goods.Goods;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @ResponseBody
    @GetMapping(value = "/testHttp")
    public BaseResponse<Object> testHttp(){
        OkHTTPUtil util = new OkHTTPUtil();
        OkHttpClient client = new OkHttpClient();
        String url = "https://www.snapdeal.com/";
        OkHttpResp resp = util.get(client,url);
        return BaseResponse.success(resp);
    }

}
