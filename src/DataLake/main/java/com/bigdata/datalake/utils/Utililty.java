/**
 *
 */
package com.bigdata.datalake.utils;

import com.bigdata.datalake.jsonobject.JsonObject;
import com.bigdata.datalake.jsonobject.SchemaDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author vsnair
 */
public class Utililty {

    public static JsonObject createJsonToJavaObject(File file) {

        JsonObject jsonObject = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Gson gson = new GsonBuilder().create();
            jsonObject = gson.fromJson(bufferedReader, SchemaDetails.class);

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
