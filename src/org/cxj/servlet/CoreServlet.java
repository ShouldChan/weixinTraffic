package org.cxj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cxj.service.CoreService;
import org.cxj.util.SignUtil;


/*
 * ������������
 * @author cxj
 * @date 2016-03-30
 * */

public class CoreServlet extends HttpServlet{
	private static final long serialVersionUID = 4440739483644821986L; 
	
	/*ȷ����������΢�ŷ�����*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//΢�ż���ǩ��
		String signature=request.getParameter("signature");
		//ʱ���
		String timestamp=request.getParameter("timestamp");
		//�����
		String nonce=request.getParameter("nonce");
		//����ַ���
		String echostr = request.getParameter("echostr"); 
		
		PrintWriter out = response.getWriter();  
        // ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr);  
        }  
        out.close();  
        out = null;  
	}
	
	/*����΢�ŷ�������������Ϣ*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//Ϊ�˷�ֹ�������룬��������Ӧ�ı�������ΪUTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//���ղ�����΢�ż���ǩ����ʱ����������
		String signature=request.getParameter("signature");
		String timestamp=request.getParameter("timestamp");
		String nonce=request.getParameter("nonce");
		
		PrintWriter out=response.getWriter();
		//����У��
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			//���ú��ķ�������մ�������
			String respXml=CoreService.processRequest(request);
			out.print(respXml);
			
		}
		out.close();
		out=null;
	}

}
