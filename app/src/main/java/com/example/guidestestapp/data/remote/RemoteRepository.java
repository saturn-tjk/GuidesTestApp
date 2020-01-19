package com.example.guidestestapp.data.remote;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.guidestestapp.data.DataSource;
import com.example.guidestestapp.data.Guide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RemoteRepository implements DataSource {

    private static RemoteRepository INSTANCE;

    private String guidesUrl = "https://guidebook.com/service/v2/upcomingGuides/";

    private Context context;

    private RemoteRepository(Context context) {
        this.context = context;
    }

    public static RemoteRepository getInstance( Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(context);
        }
        return INSTANCE;
    }


    @Override
    public void getGuides(int startPosition, int loadSize, final LoadGuidesCallBack callBack) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, guidesUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onGuidesLoaded(creteGuidesFromJsonStr(response));
                        //json(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onLoadError(error);
                    }
                });
        requestQueue.add(stringRequest);

    }

    @Override
    public void getGuide(LoadGuideCallBack callBack) {

    }

    @Override
    public void saveGuides(List<Guide> guides) {

    }


    private List<Guide> creteGuidesFromJsonStr(String jsonString){

        List<Guide> guides = new ArrayList<>();
        JSONObject mainJsonObject = null;
        JSONArray jsonArray = null;
        try {
            mainJsonObject = new JSONObject(jsonString);
            jsonArray = mainJsonObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert jsonArray != null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                Guide guide = new Guide(
                        jsonObject.getString("url"),
                        jsonObject.getString("endDate"),
                        jsonObject.getString("name"),
                        jsonObject.getString("icon")
                );
                guides.add(guide);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return guides;
    }

    private JSONObject json(String s) {
        JSONObject main = null;
        try {
            main = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return main;
    }
}
