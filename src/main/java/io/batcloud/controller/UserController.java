package io.batcloud.controller;

import com.batmobi.dataxsync.common.utils.OkHTTPUtil;
import com.batmobi.dataxsync.common.utils.OkHttpResp;
import io.batcloud.dto.common.BaseResponse;
import io.batcloud.model.goods.Goods;
import io.batcloud.task.OrderTask;
import io.batcloud.utils.AopTargetUtils;
import okhttp3.OkHttpClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    OrderTask orderTask;

    @ResponseBody
    @GetMapping(value = "/testHttp")
    public BaseResponse<Object> testHttp(){
        OkHTTPUtil util = new OkHTTPUtil();
        OkHttpClient client = new OkHttpClient();
        String url = "https://www.snapdeal.com/";
        OkHttpResp resp = util.get(client,url);
        return BaseResponse.success(resp);
    }

    @GetMapping(value = "/proxy")
    @ResponseBody
    public BaseResponse<Object> testProxy(){
        orderTask.testProxy();
       return BaseResponse.success("结束");
    }

}
