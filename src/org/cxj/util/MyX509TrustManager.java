package org.cxj.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/*
 * ���ι�����
 * 
 * @author cxj
 * @date 2016-04-12
 * */
public class MyX509TrustManager implements X509TrustManager{

	//���ͻ���֤��
	public void checkClientTrusted(X509Certificate[] chain,String authType) throws
	CertificateException{
		
	}
	//����������֤��
	public void checkServerTrusted(X509Certificate[] chain,String authType) throws
	CertificateException{
		
	}
	//���������ε�X509֤������
	public X509Certificate[] getAcceptedIssuers(){
		return null;
	}
}
