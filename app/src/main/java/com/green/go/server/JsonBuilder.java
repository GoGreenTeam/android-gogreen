package com.green.go.server;

/**
 * Created by navega on 11/27/16.
 */

public class JsonBuilder {


    public static String BUILD_IDENTITY() {
        String result = "{" +
                "'token': '" + "token" + "'," +
                "}";
        return result;
    }

    public static String BUILD_PUBLICATION(String titulo, String description, String concelho, String latitude, String longitude) {
        String result = "{\"titulo\": \"" + titulo + "\"," +
                "\"descricao\": \"" + description + "\"," +
                "\"concelho\": \"" + concelho + "\"," +
                "\"tipo\": \"" + concelho + "\"," +
                "\"localizacao\":{\"latitude\": \"" + latitude + "\",\"longitude\": \"" + longitude + "\"}}";

        return result;
    }

}
