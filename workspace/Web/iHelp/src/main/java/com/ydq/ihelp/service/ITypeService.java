package com.ydq.ihelp.service;

import java.util.List;

import com.ydq.ihelp.model.db.TypeItem;
import com.ydq.ihelp.pojo.SelfRequest;

/**
 * ���ͽӿ�
 * @author yandaoqiu
 *
 */
public interface ITypeService 
{

	/**
	 * ��ȡ�����б�
	 * @param request
	 * @return
	 */
	List<TypeItem> getTypeItems(SelfRequest request);
	
	/**
	 * ɾ������
	 * @param request
	 * @return
	 */
	boolean deleteTypeItem(SelfRequest request);
	
	
}
