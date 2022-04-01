package com.yichen.decryptdemo.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/31 18:23
 * @describe 解密 servlet request 包装
 */
@Slf4j
public class DecryptServletRequestWrapper extends HttpServletRequestWrapper {

    private Map<String,Object> requestBody;


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public DecryptServletRequestWrapper(HttpServletRequest request) {
        super(request);
        // 这里手动赋值，可以是 解密数据
        requestBody = new HashMap<>(16);
        JSONObject params = getJsonParam(request);
        requestBody.putAll(params);
        printMap(requestBody);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(JSON.toJSONString(requestBody).getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    public Map<String, Object> getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Map<String, Object> requestBody) {
        this.requestBody = requestBody;
    }

    /**
     * 获取 入参
     * @param request 请求
     * @return 入参结果集
     */
    public static JSONObject getJsonParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("getJsonParam => {}", JSON.toJSONString(jsonParam));
        return jsonParam;
    }

    public static void printMap(Map<String,Object>  params){
        params.forEach((key, value) -> log.info("key =>  {}, value => {}", key, value));
    }

}
