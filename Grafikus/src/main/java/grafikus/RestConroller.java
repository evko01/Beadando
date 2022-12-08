package grafikus;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestConroller {
    static String token ="c82f7f5d030c64d86e4a76e23f4a11b7bf17debdfdb4a0630cd7e3bfe5bb8a6d";
    static HttpURLConnection connection;

    static void segéd1(){
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }
    static void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        connection.connect();
    }

    static void segéd3(int code) throws IOException {
        int statusCode = connection.getResponseCode();   // Getting response code
        System.out.println("statusCode: "+statusCode);
        if (statusCode == code) {     // If responseCode is code, data fetch successful
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)   // Append response line by line
                jsonResponseData.append(readLine);
            in.close();
            System.out.println("List of users: " + jsonResponseData.toString());    // Print result in string format
        } else {
            System.out.println("Hiba!!!");
        }
        connection.disconnect();
    }


    static void Post(String name, String gender, String email, String status) throws IOException {
        URL postUrl = new URL("https://gorest.co.in/public/v1/users");  // Url for making POST request
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("POST");
        segéd1();
    }



}
