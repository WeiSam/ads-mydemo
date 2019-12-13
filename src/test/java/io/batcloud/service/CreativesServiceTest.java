package io.batcloud.service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.batmobi.dataxsync.common.utils.OkHTTPUtil;
import com.batmobi.dataxsync.common.utils.OkHttpResp;
import com.sam.service.MyJsonService;
import com.zz.common.log.LogService;
import com.zz.common.util.StringUtils;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import io.batcloud.config.AutoConfig;
import io.batcloud.config.DemoConfig;
import io.batcloud.dto.common.BaseResponse;
import io.batcloud.mapper.adsGoodsLibrary.GoodsMapper;
import io.batcloud.model.goods.Goods;
import io.batcloud.task.OrderTask;
import lombok.var;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;

import io.batcloud.Application;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class CreativesServiceTest {

    @Autowired
    Map<String,BaseService> map;

    @Autowired
    AutoConfig autoConfig;

    @Autowired
    OrderTask orderTask;

    @Autowired
    MyJsonService myJsonService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    DemoConfig demoConfig;

    @Test
    public void testMapAuto(){

        for (String str : map.keySet()){
            System.out.println("name:"+str+",value:"+map.get(str).getClass().getName());
        }

    }

    @Test
    public void testTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//        System.out.println(localDateTime.toLocalDate());
//        System.out.println(localDateTime.toLocalTime());
//        System.out.println(localDateTime.getDayOfMonth());
//        System.out.println(localDateTime.getDayOfWeek());
//        System.out.println(localDateTime.getDayOfYear());
//
//        System.out.println("=================================");
//        LocalDateTime localDateTime1 = localDateTime.withYear(2018);
//        System.out.println(localDateTime1);

//        ZoneId zoneId = ZoneId.of("GMT+08:00");
//        System.out.println(zoneId.getId());

//        System.out.println(localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
//        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
//        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
//        System.out.println(RandomStringUtils.randomNumeric(2));
//        System.out.println(RandomStringUtils.random(10,false,true));
//        System.out.println(RandomStringUtils.random(10,true,true));
//        System.out.println(RandomStringUtils.randomNumeric(2,4));

    }

    @Test
    public void testOKHTTP(){
            OkHTTPUtil util = new OkHTTPUtil();
            OkHttpClient client = new OkHttpClient();
            String url = "https://www.snapdeal.com/";
            OkHttpResp resp = util.get(client,url);
            System.out.println("================================");
            System.out.println(resp.getRespBody().toString());
    }

    @Test
    public void testGraphql(){
        GraphQLObjectType fooType = newObject()
                .name("Ci")
                .field(newFieldDefinition()
                        .name("product_number")
                        .type(GraphQLString))
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema().build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();
        System.out.println(fooType.toString());

    }

    @Test
    public void testGraphql02(){

        OkHTTPUtil util = new OkHTTPUtil();
        OkHttpClient client = new OkHttpClient();
        String url = "https://testhzw.myshopify.com/admin/api/2019-04/graphql.json";
        OkHttpResp resp = util.get(client,url);
    }

    @Test
    public void testZz(){
        String str = "    \n" +
                "\n" +
                "\n" +
                "LT822238377CN            \t分担分担\t是多少  dddd";
        Pattern pattern = Pattern.compile("([A-Za-z0-9]+)");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.groupCount());
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }
        System.out.println(matcher.groupCount());
        if (matcher.find() && matcher.groupCount() == 1){
            System.out.println(matcher.group(1));
        }

    }

    @Test
    public  void testZz1() {
        String str = "12";
        System.out.println(str);

        Pattern pattern = Pattern.compile("[A-Za-z0-9,]+");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }


    public List<String> arrayToList(String... args) {
        List<String> list = Arrays.stream(args).collect(Collectors.toList());
        return list;
    }

    @Test
    public  void testZz2() {
        String str = "    \n" +
                "\n" +
                "\n" +
                "LT822238377CN            \t分担分担\t是多少  dddd";
        System.out.println(str);
        System.out.println(extractNumAndLetter("  LT980635849CN,    LT980635115CN  "));
    }

    public static final Pattern nLpattern = Pattern.compile("([A-Za-z0-9]+)");
    public static String extractNumAndLetter(String soure){
        StringJoiner sj = new StringJoiner(",");

        if (!StringUtils.hasText(soure)) {
            return soure;
        }
        Matcher matcher = nLpattern.matcher(soure);
        while (matcher.find()){
            sj.add(matcher.group(1));
        }
        return sj.toString();
    }


    @Test
    public void testTask(){
        String str = "{\"klskd\": 23,\"kdlsk\": 21}";


        if (str.isEmpty())
        for (int i = 0; i<10; i++){
            orderTask.testTask(i);
        }
        try {
            Thread.sleep(1000*60*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStarter(){
        Goods goods = new Goods();
        goods.setId(1);
        goods.setGoodsName("dsjdj");
        System.out.println(myJsonService.toJson(goods));
    }

    @Test
    public void testEx(){
        Example example = new Example(Goods.class);
        example.setCountProperty("storeId");
        example.setDistinct(true);
        int count = goodsMapper.selectCountByExample(example);
        System.out.println(count);
    }
}
