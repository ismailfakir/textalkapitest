package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionOptions;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TextTalkUtils {

    private static Logger log=AppLogger.getLogger(TextTalkUtils.class.getName());

    public static String REQUEST_URL="";


    public static String getToken() throws Exception{
        String token=null;
        URL serverURL = new URL(AppConfiguration.getBaseUrl());
        JSONRPC2Session mySession = new JSONRPC2Session(serverURL);

        // Construct new request
        String method = "Admin.login";
        String requestID = "1";
        final List<Object> params =new ArrayList<Object>();
        params.add(AppConfiguration.getConfiguration(AppConfiguration.USER_NAME));
        params.add(AppConfiguration.getConfiguration(AppConfiguration.USER_PASSWORD));

        JSONRPC2Request request = new JSONRPC2Request(method,params,requestID);

        // Send request
        JSONRPC2Response response = null;

        response = mySession.send(request);


        // Print response result / error
        if (response.indicatesSuccess()){
            //System.out.println("TOKEN: "+response.getResult());
            log.info("TOKEN: "+response.getResult());
            token=response.getResult().toString();
        } else{
            //System.out.println(response.getError().getMessage());
            log.error(response.getError().getMessage());
            token=response.getError().getMessage();
        }

        return token;
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
        Date lastUpdateaDate=getDate(AppConfiguration.getConfiguration(AppConfiguration.LAST_UPDATE));
        long diff = nowDate.getTime() - lastUpdateaDate.getTime();
        //long minutes = TimeUnit.MILLISECONDS.convert(diff,TimeUnit.MINUTES);
        long hours = diff/(60*60 * 1000);

        //log.info(hours+" Hours passed since token update");

        if(hours>12.0){
            log.info("token update needed");
            return true;
        }else {
            return false;
        }

    }


    public static void updateToken() throws Exception{

        if(isUpdateToken()){
            log.info("Updating token");
            AppConfiguration.setConfiguration(AppConfiguration.TOKEN,getToken());
            AppConfiguration.setConfiguration(AppConfiguration.LAST_UPDATE,TextTalkUtils.getCurrentTime());
            log.info("Token updated successfully");
            return;
        }

    }

    public static void loadConfigurations() throws Exception{
        updateToken();
        REQUEST_URL= AppConfiguration.getRequestUrl();

    }



}
