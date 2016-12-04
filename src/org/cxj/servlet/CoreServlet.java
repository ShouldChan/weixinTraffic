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
 * 核心请求处理类
 * @author cxj
 * @date 2016-03-30
 * */

public class CoreServlet extends HttpServlet{
	private static final long serialVersionUID = 4440739483644821986L; 
	
	/*确认请求来自微信服务器*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//微信加密签名
		String signature=request.getParameter("signature");
		//时间戳
		String timestamp=request.getParameter("timestamp");
		//随机数
		String nonce=request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr"); 
		
		PrintWriter out = response.getWriter();  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr);  
        }  
        out.close();  
        out = null;  
	}
	
	/*处理微信服务器发来的消息*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//为了防止中文乱码，将请求响应的编码设置为UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//接收参数：微信加密签名、时间戳、随机数
		String signature=request.getParameter("signature");
		String timestamp=request.getParameter("timestamp");
		String nonce=request.getParameter("nonce");
		
		PrintWriter out=response.getWriter();
		//请求校验
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			//调用核心服务类接收处理请求
			String respXml=CoreService.processRequest(request);
			out.print(respXml);
			
		}
		out.close();
		out=null;
	}

}
