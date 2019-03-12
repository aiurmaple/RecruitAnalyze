package indi.aiurmaple.recruitanalyze.datatransform.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static String handleJobName(String fileName) {
        String[] arr = fileName.split("_");
        return arr[0];
    }

    public static String handleCity(String fileName) {
        String[] arr1 = fileName.split("_");
        String str = arr1[1];
        Integer num = str.indexOf(".json");
        return str.substring(0, num);
    }

    public static String handleWorkingExp(JSONObject root) {
        JSONObject workingExp = root.getJSONObject("workingExp");
        return workingExp.getString("name");
    }

    public static String handleEduLevel(JSONObject root) {
        JSONObject eduLevel = root.getJSONObject("eduLevel");
        return eduLevel.getString("name");
    }

    public static String handleCompanySize(JSONObject root) {
        JSONObject companySize = root.getJSONObject("size");
        return companySize.getString("name");
    }

    public static String handleCompanyType(JSONObject root) {
        JSONObject companyType = root.getJSONObject("type");
        return companyType.getString("name");
    }

    public static List<String> handleWelfares(JSONObject root) {
        JSONArray welfareArr = root.getJSONArray("welfare");
        List<String> welfares = new ArrayList<>();
        welfareArr.forEach((welfare) -> {
            welfares.add((String)welfare);
        });
        return welfares;
    }
}
