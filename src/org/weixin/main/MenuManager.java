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

/*�˵���������
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
	 * ����˵��ṹ
	 * 
	 * @return
	 * */

	private static Menu getMenu() {
		/**
		 * �¹ʿ촦
		 *    �����촦
		 *    ģ�����
		 */
		ViewButton btn11 = new ViewButton();
		btn11.setName("�����촦");
		btn11.setType("view");
		btn11.setUrl("http://lemonyo.duapp.com/accident_send.jsp");

		ClickButton btn12 = new ClickButton();
		btn12.setName("ģ�����");
		btn12.setType("click");
		btn12.setKey("mncz");

		/**
		 * ҵ���ѯ
		 *    ��ʻ֤��Ϣ��ѯ
		 *    ��������Ϣ��ѯ
		 *    ��ͨΥ����Ϣ��ѯ
		 *    ���������ѯ
		 */
		ViewButton btn21 = new ViewButton();
		btn21.setName("��ʻ֤��Ϣ��ѯ");
		btn21.setType("view");
		btn21.setUrl("http://lemonyo.duapp.com/driver_send.jsp");

		ViewButton btn22 = new ViewButton();
		btn22.setName("��������Ϣ��ѯ");
		btn22.setType("view");
		btn22.setUrl("http://lemonyo.duapp.com/car_send.jsp");

		ViewButton btn23=new ViewButton();
		btn23.setName("��ͨΥ�²�ѯ");
		btn23.setType("view");
		btn23.setUrl("http://lemonyo.duapp.com/offense_send.jsp");

		ClickButton btn24=new ClickButton();
		btn24.setName("���������ѯ");
		btn24.setType("click");
		btn24.setKey("jgwdcx");
		
		/**
		 * ��ѯ����
		 *   ������ѯ
		 *   �ܱ�����
		 *   ��ϵ����
		 */
		ClickButton btn31 = new ClickButton();
		btn31.setName("������ѯ");
		btn31.setType("click");
		btn31.setKey("jgzx");

		ClickButton btn32=new ClickButton();
		btn32.setName("�ܱ�����");
		btn32.setType("click");
		btn32.setKey("zbss");

		ViewButton btn33 =new ViewButton();
		btn33.setName("��ϵ����");
		btn33.setType("view");
		btn33.setUrl("http://lemonyo.duapp.com/contactus.html");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("����촦");
		mainBtn1.setSub_button(new Button[] { btn11, btn12});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("ҵ���ѯ");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��ѯ����");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wxb21fe51ea23037c1";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "c3709b0ea88c5162c9babfea6232f7e7";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ�");
		}
	}
}
