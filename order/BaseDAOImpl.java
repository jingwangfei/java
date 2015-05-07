package com.order;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


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
	
	
	public List<T> list (T entity) {
		List<T> list = new ArrayList<T>();
		
		if (entity.getClass() != clazz) {
			list = null;
		} else {
			StringBuffer selectCause =  new StringBuffer("select ");
			String fromCuase = " from " + clazz.getSimpleName().toLowerCase();
			StringBuffer whereCuase = new StringBuffer(" where 1 = 1");
			
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				selectCause.append(fields[i].getName());
				if (i != fields.length - 1) {
					selectCause.append(", ");
				}
				
				String getMethodName = "get" 
									+ fields[i].getName().substring(0, 1).toUpperCase()
									+ fields[i].getName().substring(1);
				try {
					Method getMethod = clazz.getDeclaredMethod(getMethodName, null);
					Object retValue = getMethod.invoke(entity, null);
					
					if (null != retValue) {
						if (retValue instanceof String) {
							whereCuase.append(" and " + fields[i].getName() + " like '%" + retValue + "%'");
						}
						if (retValue instanceof Integer ||
								retValue instanceof Double) {
							whereCuase.append(" and " + fields[i].getName() + " = " + retValue);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} 
				
			}
			
			String sql = selectCause + fromCuase + whereCuase;
			System.out.println(sql);
		}
		
		return list;
	}
	
	
	
}
