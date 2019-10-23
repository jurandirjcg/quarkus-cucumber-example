/*
 * Copyright 2017 Jurandir C. Goncalves
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.jgon.canary.ws.rest.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jgon.canary.exception.ApplicationRuntimeException;
import br.com.jgon.canary.util.MessageSeverity;
import br.com.jgon.canary.util.Page;
import br.com.jgon.canary.util.ReflectionUtil;

/**
 * Auxlia na conversao do objeto para o objeto de response
 * @author jurandir
 *
 * @param <O> - Origem
 */
//TODO implementar no Canary
public abstract class ResponseConverter<O> {
	
	public ResponseConverter() {
	
	}
	
	@SuppressWarnings("unchecked")
	public <N extends ResponseConverter<O>> N converter(O obj) {
		try {
			N ret = (N) getInstance(this.getClass());
			
			List<Field> thisFields = ReflectionUtil.listAttributes(this.getClass());
			boolean isResponseConverterType = false;
			
			Object objAux;
			for(Field fld : thisFields) {
				objAux = null;
				WSTransient wst = ReflectionUtil.getAnnotation(fld, WSTransient.class);
				if(wst != null) {
					continue;
				}
				WSAttribute wsa = ReflectionUtil.getAnnotation(fld, WSAttribute.class);
				if(wsa != null) {
					objAux = ReflectionUtil.getAttributteValue(obj, wsa.value());
				} else {
					objAux = ReflectionUtil.getAttributteValue(obj, fld.getName());
				}
				
				isResponseConverterType = ResponseConverter.class.isAssignableFrom(fld.getType());
				
				if(!isResponseConverterType) {
					ReflectionUtil.setFieldValue(ret, fld, objAux);
				}else if(isResponseConverterType) {
					ResponseConverter<?> respConv = checkResponse(objAux, fld.getType());
					ReflectionUtil.setFieldValue(ret, fld, respConv);
				}
			}

			return ret;
		}catch (Exception e) {
			throw new ApplicationRuntimeException(MessageSeverity.ERROR, e, "Tratar depois");
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T extends ResponseConverter<E>, E> ResponseConverter<E> checkResponse(E value, Class<?> returnClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(value == null) {
			return null;
		}
		
		ResponseConverter<E> ret;
		ret = (ResponseConverter<E>) getInstance(returnClass);
		ret.converter(value);

		return ret;
	}
	
	@SuppressWarnings("unchecked")
	private <E> E getInstance(Class<E> klass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<?>[] ctors = klass.getDeclaredConstructors();
		Constructor<?> ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0) {
				break;
			}
		}

		ctor.setAccessible(true);
		return (E) ctor.newInstance();

	}
	
	/**
	 * 
	 * @param listObj
	 * @return
	 */
	public <N extends ResponseConverter<O>> List<N> converter(Collection<O> listObj){
		if(listObj == null){
			return null;
		}
		
		List<N> newList = new ArrayList<N>(listObj.size());
		for(O obj: listObj){
			newList.add(this.converter(obj));
		}
		return newList;
	}
	/**
	 * 
	 * @param paginacao
	 * @return
	 */
	public <N extends ResponseConverter<O>> Page<N> converter(Page<O> paginacao){
		Page<N> pRetorno = new Page<N>();
		
		pRetorno.setTotalPages(paginacao.getTotalPages());
		pRetorno.setCurrentPage(paginacao.getCurrentPage());
		pRetorno.setElementsPerPage(paginacao.getElementsPerPage());
		pRetorno.setTotalElements(paginacao.getTotalElements());
		pRetorno.setElements(this.converter(paginacao.getElements()));
		
		return pRetorno;
	}
}
