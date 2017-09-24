package com.example.km.genericapp.utilities;

import android.content.Context;

import com.example.km.genericapp.constants.Constants;

import java.io.IOException;
import java.io.InputStream;

/**
 * Embedded resource helper.
 */
public class EmbeddedResourceHelper {

    private static EmbeddedResourceHelper instance;

    private EmbeddedResourceHelper() {
    }

    public static EmbeddedResourceHelper getInstance() {
        if (instance == null) {
            instance = new EmbeddedResourceHelper();
        }
        return instance;
    }

    /**
     * Read full text content from the specified test resources file.
     */
    public static String ReadContentFromTestResources(String fileName) {
        String content = null;
        try {
            InputStream is = instance.getClass().getClassLoader().getResourceAsStream(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            content = new String(buffer, Constants.ENCODING_UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return content;
    }

    /**
     * Read full text content from the specified assets file.
     */
    public static String ReadContentFromAssets(Context context, String fileName) {
        String content = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            content = new String(buffer, Constants.ENCODING_UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return content;
    }
}
