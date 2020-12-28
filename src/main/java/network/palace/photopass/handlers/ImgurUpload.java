package network.palace.photopass.handlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import network.palace.core.Core;
import okhttp3.*;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImgurUpload {

    public String convertToBinary(Image img) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) img, "jpg", b);
        byte[] jpgByteArray = b.toByteArray();
        StringBuilder sb = new StringBuilder();
        for (byte by : jpgByteArray)
            sb.append(Integer.toBinaryString(by & 0xFF));
        return sb.toString();
    }

    public boolean uploadImage(String clientID, String imgData) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", imgData)
                .build();
        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/image")
                .method("POST", body)
                .addHeader("Authorization", "Client-ID " + clientID)
                .build();
        Response response = client.newCall(request).execute();
        String resData = response.body().string();
        JsonObject jsonData = new JsonParser().parse(resData).getAsJsonObject();
        Core.logInfo(jsonData.get("data").getAsJsonObject().get("link").getAsString());
        return true;
    }

}
