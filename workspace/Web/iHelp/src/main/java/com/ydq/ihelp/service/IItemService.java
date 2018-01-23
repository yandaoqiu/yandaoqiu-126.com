package com.ydq.ihelp.service;


import com.ydq.ihelp.pojo.BaseResponse;
import com.ydq.ihelp.pojo.SelfRequest;

/**
 * �б�ӿ�
 * @author yandaoqiu
 *
 */
public interface IItemService {

	/**
	 * ��ȡ�б�
	 * @param request �����������
	 * @return
	 */
	BaseResponse getItem(SelfRequest request,String location,int start,int length);
	
	
	/**
	 * ��ȡ�б�����
	 * @param request
	 * @return
	 */
	<T> T getItemDetail(SelfRequest request,String itemid);
	

	/**
	 * ����
	 * @param request
	 * @return
	 */
	boolean updateItem(SelfRequest request);
}
