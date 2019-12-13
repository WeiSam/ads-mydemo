package io.batcloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import io.batcloud.dto.common.BaseResponse;
import io.batcloud.dto.common.PageResponse;
import io.batcloud.service.BaseService;


/**  
* <p>Title: BaseController</p>  
* <p>Description: Controller的基类，处理异常，公共方法 </p>  
* @author gaolinlou  
* @date 2018年8月9日  
*/  
public abstract class BaseController<E extends  BaseService<T>,T> {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected  E service;

	/**
	 * 通用列表查询
	 * @param selectOption 查询参数
	 * @param pageSize 页大小
	 * @param pageNum  页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list",method = RequestMethod.GET )
	protected BaseResponse<List<T>> list(HttpServletRequest request, T selectOption,
									   @RequestParam(value = "pageSize",required = false) Integer pageSize,
									   @RequestParam(value = "pageNum",required = false) Integer pageNum){
		if(pageNum != null && pageSize != null){
			PageHelper.startPage(pageNum, pageSize);
		}
		List<T> result = service.selectList(selectOption);
		return PageResponse.successPage(result);
	}

	/**
	 * 通用更新操作
	 * 注意body中需要包含主键
	 * @param entity
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public BaseResponse<?> modify(HttpServletRequest request,@RequestBody T entity,@PathVariable("id")String id){
		service.updateById(entity);
		return BaseResponse.success();
	}

	/**
	 * 通用查询明细操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public BaseResponse<T> cat(HttpServletRequest request,@PathVariable("id") String id){
		T t = service.selectById(id);
		return BaseResponse.success(t);
	}


	/**
	 * 通用删除操作
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public BaseResponse<?> del(HttpServletRequest request,@PathVariable String id){
		service.deleteById(id);
		return BaseResponse.success();
	}
}