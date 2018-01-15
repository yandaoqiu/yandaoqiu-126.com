package com.ydq.ihelp.service;

import java.util.List;

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
	List<?> getItem(SelfRequest request);
	
	
	/**
	 * ��ȡ�б�����
	 * @param request
	 * @return
	 */
	<T> T getItemDetail(SelfRequest request);
	

	/**
	 * ����
	 * @param request
	 * @return
	 */
	boolean updateItem(SelfRequest request);
}
