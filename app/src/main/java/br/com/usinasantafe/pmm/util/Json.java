package br.com.usinasantafe.pmm.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {

    public Json() {
    }

    public JSONArray jsonArray(String obj) throws JSONException {
        JSONObject jObj = new JSONObject(obj);
        return jObj.getJSONArray("dados");
    }

}
