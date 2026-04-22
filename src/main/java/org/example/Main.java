package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
/*        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

// 1. Create a client
        HttpClient client = HttpClient.newHttpClient();

        // 2. Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/")) // Replace with your URL
                .build();

        // 3. Send the request and handle the response
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join(); // Wait for the request to complete*/
        try {
            URL url = new URL("https://api.github.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                findUrls(inputLine);
            }

            // Clean up
            in.close();
            connection.disconnect();

            System.out.println(content.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void findUrls(String text) {
        // This Regex looks for http or https followed by the typical URL structure
        String regex = "https?://[\\w\\.\\-]+(/[\\w\\.\\-/\\?\\%\\&\\=]*)?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            System.out.println("Found link: " + matcher.group());
            count++;
        }
        System.out.println("\nTotal links found: " + count);
    }
}
