package net.cloudcentrik.textalk;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TextTalkUtils {


    public static String USER_NAME="";
    public static String USER_PASSWORD="";
    public static String BASE_URL="";
    public static String TOKEN="";


    public static String REQUEST_URL=BASE_URL+"&auth="+TOKEN;


    public static String getToken() throws Exception{
        String token=null;

        URL serverURL = new URL(BASE_URL);
        JSONRPC2Session mySession = new JSONRPC2Session(serverURL);

        // Construct new request
        String method = "Admin.login";
        String requestID = "1";
        final List<Object> params =new ArrayList<Object>();
        params.add(USER_NAME);
        params.add(USER_PASSWORD);

        JSONRPC2Request request = new JSONRPC2Request(method,params,requestID);

        // Send request
        JSONRPC2Response response = null;

        response = mySession.send(request);


        // Print response result / error
        if (response.indicatesSuccess()){
            System.out.println("TOKEN: "+response.getResult());
            token=response.getResult().toString();
        } else{
            System.out.println(response.getError().getMessage());
            token=response.getError().getMessage();
        }

        return token;
    }

    /*consoule print */
    public static void log(String str){
        System.out.println(str);
    }

    public static String  getCurrentTime(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date=new Date();
        return formatter.format(date);
    }

    public static Date  getDate(String dateString)throws Exception{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date=formatter.parse(dateString);
        return date;
    }

    public static boolean isUpdateToken() throws Exception{

        Date nowDate=getDate(getCurrentTime());
        Date lastUpdateaDate=getDate(AppPropertyUtils.getProperty("LAST_UPDATE"));
        long diff = nowDate.getTime() - lastUpdateaDate.getTime();
        //long minutes = TimeUnit.MILLISECONDS.convert(diff,TimeUnit.MINUTES);
        long hours = diff/(60*60 * 1000);

        log(hours+" passed");

        if(hours<12){
            return false;
        }else {
            return false;
        }

    }


    public static void updateToken() throws Exception{

        if(isUpdateToken()){
            AppPropertyUtils.updateProperty("TOKEN",getToken());
        }

    }

    public static void loadConfigurations() throws Exception{
        updateToken();
        USER_NAME=AppPropertyUtils.getProperty("USER_NAME");
        USER_PASSWORD=AppPropertyUtils.getProperty("USER_PASSWORD");
        BASE_URL=AppPropertyUtils.getProperty("BASE_URL");
        TOKEN=AppPropertyUtils.getProperty("TOKEN");
        REQUEST_URL= BASE_URL+"&auth="+TOKEN;
    }


}
