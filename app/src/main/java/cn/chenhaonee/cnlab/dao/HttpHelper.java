package cn.chenhaonee.cnlab.dao;

import android.util.Base64;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenhaonee on 2017/6/18.
 */

public class HttpHelper {
    private static final String baseUrl = "http://115.29.184.56:8090/api";

    private static final String login = "/user/auth";

    private static int courseId = 1;

    private static final String stuCheckExamList = "/course/" + courseId + "/exam";
    private static final String stuCheckHomeworkList = "/course/" + courseId + "/homework";
    private static final String stuCheckExerciseList = "/course/" + courseId + "/exercise";

    ///assignment/{assignmentId}/score
    private static final String teaGetAllScore = "/assignment/{assignmentId}/score";

    private static final String teaGetAllClass = "/group";

    private static final String teaGetStuList = "/group/{groupId}/students";

    private static final String stuAnalyze = "/assignment/{assignmentId}/student/{studentId}/analysis";

    private static final int studentId = 227;

    private static final String getReadme = "/assignment/{assignmentID}/student/{studentID}/question/{questionID}";

    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectionPool(new ConnectionPool(20, 60, TimeUnit.SECONDS)).build();

    private static String authorization;

    public static Request.Builder builder() {
        return new Request.Builder().addHeader("Authorization", authorization);
    }

    public static Response takeTask(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void addHead(String username, String password) {
        String toEncode = username + ":" + password;
        String token = net.iharder.Base64.encodeBytes(toEncode.getBytes());
        authorization = "Basic " + token;
    }

    public static String getStuCheckExamListUrl() {
        return baseUrl + stuCheckExamList;
    }

    public static String getStuCheckHomeworkListUrl() {
        return baseUrl + stuCheckHomeworkList;
    }

    public static String getStuCheckExerciseListUrl() {
        return baseUrl + stuCheckExerciseList;
    }

    public static String getLoginUrl() {
        return baseUrl + login;
    }

    public static String getTeaGetAllClass() {
        return baseUrl + teaGetAllClass;
    }

    public static String getTeaGetStuList(int groupId) {
        return (baseUrl + teaGetStuList).replace("{groupId}", "" + groupId);
    }

    public static String getTeaGetAllScore(int assignmentId) {
        return (baseUrl + teaGetAllScore).replace("{assignmentId}", "" + assignmentId);
    }

    public static String getStuAnalyze(int assignment, int studentId) {
        return (baseUrl + stuAnalyze).replace("{assignmentId}", "" + assignment).replace("{studentId}", "" + studentId);
    }

    public static String getGetReadme(int assignment, int studentId, int questionID) {
        return (baseUrl + stuAnalyze).replace("{assignmentId}", "" + assignment).replace("{studentId}", "" + studentId).replace("{questionID}", "" + questionID);
    }
}
