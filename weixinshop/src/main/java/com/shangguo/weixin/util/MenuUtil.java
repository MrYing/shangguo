package com.shangguo.weixin.util;

import net.sf.json.JSONObject;

import com.shangguo.weixin.accessToke.AccessToken;
import com.shangguo.weixin.menu.BaseButton;
import com.shangguo.weixin.menu.ComplexButton;
import com.shangguo.weixin.menu.Menu;
import com.shangguo.weixin.menu.ViewButton;

/**
 * 创建微信自定义菜单工具类
 * @author JabnMai
 * @date 2015-7-6
 */
public class MenuUtil {
    public final static String urlMode = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /*public final static String appID = "wx916a1d9dce639147";
    public final static String appsecret = "63b8f49e77e95ffdab3baac9615a0c14";*/
    
    public static boolean createMenu(){
    	AccessToken accessToken = new AccessToken();
    	boolean result = false;
    	String access_token = accessToken.getAccess_token();
    	String requestUrl = urlMode.replace("ACCESS_TOKEN", access_token);
    	
        ViewButton viewButton = new ViewButton();
        viewButton.setName("我要买");
        viewButton.setType("view");
        viewButton.setUrl("http://wap.koudaitong.com/v2/feature/1axd59g80");
        
        ViewButton viewButton21 = new ViewButton();
        viewButton21.setName("我的订单");
        viewButton21.setType("view");
        viewButton21.setUrl("http://wap.koudaitong.com/v2/feature/1axd59g80");
        
        ViewButton viewButton22 = new ViewButton();
        viewButton22.setName("关于我们");
        viewButton22.setType("view");
        viewButton22.setUrl("http://wap.koudaitong.com/v2/feature/1axd59g80");
        
        BaseButton[] button21 = new BaseButton[]{viewButton21,viewButton22};
        
        ComplexButton complexButton = new ComplexButton();
        complexButton.setName("果都中心");        
        complexButton.setSub_button(button21);
        
    	Menu menu = new Menu();
    	BaseButton[] button = new BaseButton[]{viewButton,complexButton};
    	menu.setButton(button);
    	
    	String menujson = JSONObject.fromObject(menu).toString();
    	String serverResult = CommonUtil.httpsRequest(requestUrl, "POST", menujson);
    	System.out.println(serverResult);
    	return result;
    }	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createMenu();
	}

}

