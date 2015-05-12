package com.order;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.morph.wrap.Bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


public abstract class BaseDAOImpl<T> implements IBaseDAO<T> {

	protected Class<T> clazz;
	
	/*
	 * 使用Java的面向接口编程 
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	/*
	 * 使用propertyutils, beanutils
	 */
	public List<T> list(T t) {
		
		// BeanUtils.getProperty(bean, name) ---> 是Java原生反射, 根据属性获得, get方法名, 获得get方法, 调用, 获得值得封装.
		// BeanUtils.describe(bean) --> 会将bean转换为Map, 但是所有的属性, 如果没有值, 也会进行转换.
		// BeanUtils中最只要的是作用是获得 实体的一些属性, 如果你指定了 class在使用它, 就没有必须要了
		
		/**
		 * beanUtils 和 PropertyUtils的区别
		 */
		// 1.BeanUtils中的getProperty, 返回的是String类型; 而PropertyUtils返回的是Object类型.
		// 2.BeanUtils中没有获得所有属性的方法, PropertyUtils中有
		// 3.BeanUtils中有populate方法, PropertyUtils中没有
		try {
			StringBuffer selectCause = new StringBuffer("select ");
			StringBuffer formCause = new StringBuffer("from " + t.getClass().getSimpleName().toLowerCase() + " ");
			StringBuffer whereCause = new StringBuffer("where 1 = 1 ");
			
			PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(t);
			for (int i = 0; i < propertyDescriptors.length; i++) {
				selectCause.append(propertyDescriptors[i].getName());
				if (i != propertyDescriptors.length - 1) {
					selectCause.append(",");
				}
				selectCause.append(" ");
				
				Object fieldValue = PropertyUtils.getProperty(t, propertyDescriptors[i].getName());
				if (null != fieldValue) {
					if (fieldValue instanceof String) {
						whereCause.append("and " + propertyDescriptors[i].getName() + " like '" + fieldValue + "' ");
					}
					if (fieldValue instanceof Integer) {
						whereCause.append("and " + propertyDescriptors[i].getName() + " = " + fieldValue + " ");
					}
					if (fieldValue instanceof Double) {
						whereCause.append("and " + propertyDescriptors[i].getName() + " = " + fieldValue + " ");
					}
				}
			}
			
			String sql = selectCause.toString() + formCause.toString() + whereCause.toString();
			System.out.println(sql);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
