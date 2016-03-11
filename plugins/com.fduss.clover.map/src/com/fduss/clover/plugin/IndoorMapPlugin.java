package com.fduss.clover.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import com.ionicframework.app2892108.MapActivity;

import android.content.Intent;

public class IndoorMapPlugin extends CordovaPlugin {

	@Override
	/**
	 * 默認執行的函數
	 */
	public boolean execute(String action, JSONArray args,
			final CallbackContext callbackContext) throws JSONException {

		Intent intent = new Intent(cordova.getActivity(), MapActivity.class);
		cordova.getActivity().startActivity(intent);
		return true;
	}

}
