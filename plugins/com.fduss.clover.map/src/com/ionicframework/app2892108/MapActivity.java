package com.ionicframework.app2892108;

import android.app.Activity;

import java.util.List;

import com.ids.sdk.android.cm.ag;
import com.ids.sdk.android.locate.Locator;
import com.ids.sdk.android.map.*;
import com.ids.sdk.android.model.Building;
import com.ids.sdk.android.model.City;
import com.ids.sdk.android.model.Location;
import com.ids.sdk.android.model.RequestCallback;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

public class MapActivity extends Activity {
	
	private MapView mapView;
	private Map map;
	private Locator locator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		
		mapView = new MapView(this); //传入参数类型为Context
	    map = mapView.getMap(); //Map对象是地图显示的关键，对地图的调用都来自该对象。
	    addContentView(mapView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	    //调用setContentView等亦可
	    
	    //加载地图
	    City.requestCity(new RequestCallback<List<City>>() {
	        @Override
	        public void onFinish(List<City> cities) {
	            //简单起见，使用第一个城市的第一个建筑
	            if (cities.size() > 0 && cities.get(0).getBuildingSet().size() > 0) {
	                Building building = cities.get(0).getBuildingSet().iterator().next();
	                map.init(building.getBuildingId()); //加载地图
	            }
	        }
	        @Override
	        public void onFail() {
	        }
	    });
	    
	    locator = Locator.getInstance(this);
	    locator.setListener(new Locator.Listener() {
	        @Override
	        public void onReady() {
	            locator.startLocating(); //可以在此开始定位。也可以在Map.NavListener::startUpdatingLocation方法中开始定位。
	        }

	        @Override
	        public void onFail() {
	        }

	        @Override
	        public void onLocatingSucceed(Location location) {
	            map.setLocation(location); //获得了室内位置。可以在此处将位置传给地图SDK显示。
	        }

	        @Override
	        public void onLocatingFail() {
	            //map.hideLocation(); //定位失败
	        }
	    });
	    
	    City.requestCity(new RequestCallback<List<City>>() {
	        @Override
	        public void onFinish(List<City> cities) {
	            //简单起见，使用第一个城市的第一个建筑
	            if (cities.size() > 0 && cities.get(0).getBuildingSet().size() > 0) {
	                Building building = cities.get(0).getBuildingSet().iterator().next();
	                locator.init(building.getBuildingId()); //加载地图数据
	            }
	        }
	        @Override
	        public void onFail() {
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
