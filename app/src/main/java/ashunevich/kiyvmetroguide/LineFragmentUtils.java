package ashunevich.kiyvmetroguide;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


abstract class LineFragmentUtils {

    static public String loadJsonEvent(String jsonName, Context context) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(jsonName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }



}
