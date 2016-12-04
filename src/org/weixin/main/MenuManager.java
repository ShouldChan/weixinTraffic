package org.weixin.main;

import org.cxj.menu.Button;
import org.cxj.menu.ClickButton;
import org.cxj.menu.ComplexButton;
import org.cxj.menu.Menu;
import org.cxj.menu.ViewButton;
import org.cxj.pojo.Token;
import org.cxj.util.CommonUtil;
import org.cxj.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*菜单管理器类
 * 
 * @author cxj
 * @date1 2016-04-12
 * @date2 2016-04-13
 * @date3 2016-05-30
 * 
*/
public class MenuManager {
	private static Logger log=LoggerFactory.getLogger(MenuManager.class);
	
	/*
	 * 定义菜单结构
	 * 
	 * @return
	 * */

	private static Menu getMenu() {
		/**
		 * 事故快处
		 *    自助快处
		 *    模拟操作
		 */
		ViewButton btn11 = new ViewButton();
		btn11.setName("自助快处");
		btn11.setType("view");
		btn11.setUrl("http://lemonyo.duapp.com/accident_send.jsp");

		ClickButton btn12 = new ClickButton();
		btn12.setName("模拟操作");
		btn12.setType("click");
		btn12.setKey("mncz");

		/**
		 * 业务查询
		 *    驾驶证信息查询
		 *    机动车信息查询
		 *    交通违章信息查询
		 *    交管网点查询
		 */
		ViewButton btn21 = new ViewButton();
		btn21.setName("驾驶证信息查询");
		btn21.setType("view");
		btn21.setUrl("http://lemonyo.duapp.com/driver_send.jsp");

		ViewButton btn22 = new ViewButton();
		btn22.setName("机动车信息查询");
		btn22.setType("view");
		btn22.setUrl("http://lemonyo.duapp.com/car_send.jsp");

		ViewButton btn23=new ViewButton();
		btn23.setName("交通违章查询");
		btn23.setType("view");
		btn23.setUrl("http://lemonyo.duapp.com/offense_send.jsp");

		ClickButton btn24=new ClickButton();
		btn24.setName("交管网点查询");
		btn24.setType("click");
		btn24.setKey("jgwdcx");
		
		/**
		 * 咨询互动
		 *   交管咨询
		 *   周边搜索
		 *   联系我们
		 */
		ClickButton btn31 = new ClickButton();
		btn31.setName("交管咨询");
		btn31.setType("click");
		btn31.setKey("jgzx");

		ClickButton btn32=new ClickButton();
		btn32.setName("周边搜索");
		btn32.setType("click");
		btn32.setKey("zbss");

		ViewButton btn33 =new ViewButton();
		btn33.setName("联系我们");
		btn33.setType("view");
		btn33.setUrl("http://lemonyo.duapp.com/contactus.html");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("事务快处");
		mainBtn1.setSub_button(new Button[] { btn11, btn12});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("业务查询");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("咨询互动");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxb21fe51ea23037c1";
		// 第三方用户唯一凭证密钥
		String appSecret = "c3709b0ea88c5162c9babfea6232f7e7";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}
