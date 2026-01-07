import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YouTube {
    public static void main(String[] args) {
        try{

            // Get your API KEY: https://developers.google.com/youtube/v3/getting-started
            String apiKey = "YOURAPIKEYHERE";
            String keyword = "SpaceX";

            // creating the API call with arguments keyword and apiKey
            String sURL = "https://www.googleapis.com/youtube/v3/search" +
                    "?part=id%2Csnippet&q=" + keyword + "&key=" + apiKey;
            URL newURL = new URL(sURL);

            // create connection
            URLConnection conn = newURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            //create inputStream
            InputStream inputStream = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            // building the response with string buffer
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            // get the final response as string
            String jsonData = sb.toString();

            // parse string as JSON object using org.json library
            JSONObject jsonObject = new JSONObject(jsonData);

            // getting list of videos
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                JSONObject id = item.getJSONObject("id");
                String kind = id.getString("kind");
                // only parsing youtube videos
                if(kind.equals("youtube#video")){
                    JSONObject snippet = item.getJSONObject("snippet");

                    // in the snippet get video info
                    String title = snippet.getString("title");
                    String publishedAt = snippet.getString("publishedAt");

                    System.out.println("Title: " + title);
                    System.out.println("Published At: " + publishedAt);
                }
            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
