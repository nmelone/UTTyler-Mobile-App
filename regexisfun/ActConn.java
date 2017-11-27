import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ActConn {
	public static String args0, args1, args2;
	
	public ActConn(String[] args) {
		args0 = args[0];
		// Either "query" or "login"
		
		args1 = args[1];
		// if (args0 =="query") {"select * from *}
		// if (args0 =="login") {email@server.ext}
		
		if(args[2]!=null)
			args2 = args[2];
			// if (args0 =="login") {"password"} else null
	}
        // DO NOT CHANGE cosc5384.us
        // replace yourTeamUsername
	public String makeTheConnection(){
        String login_url = "http://cosc5384.us/teamfive/" + args0 +".php";
        String type = args0;
        try {
            String post_data = "";
            //setting up the url, making a connection, and setup a post
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //setup output stream of data and post the data
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            if (type.equals("login")) {
                String email = args1;
                String password = args2;
                post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            } else if (type.equals("query")) {
                String query = args1;
                post_data = URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8");
            }
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //reading data from http
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            //process the recieved data
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            //return the data
            return result.toString();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return null;
	}
}