package com.smallrain.mall.common.beans;

import java.util.List;

import org.springframework.data.domain.Page;

import com.github.pagehelper.PageInfo;

import lombok.Data;

/**
 * 分页数据封装类
 */
@Data
public class SPage<T> {

	private Integer pageNum;
	private Integer pageSize;
	private Integer totalPage;
	private Long total;
	private List<T> list;

	/**
	 * 将PageHelper分页后的list转为分页信息
	 */
	public static <T> SPage<T> restPage(List<T> list) {
		SPage<T> result = new SPage<T>();
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		result.setTotalPage(pageInfo.getPages());
		result.setPageNum(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotal(pageInfo.getTotal());
		result.setList(pageInfo.getList());
		return result;
	}

	/**
	 * 将SpringData分页后的list转为分页信息
	 */
	public static <T> SPage<T> restPage(Page<T> pageInfo) {
		SPage<T> result = new SPage<T>();
		result.setTotalPage(pageInfo.getTotalPages());
		result.setPageNum(pageInfo.getNumber());
		result.setPageSize(pageInfo.getSize());
		result.setTotal(pageInfo.getTotalElements());
		result.setList(pageInfo.getContent());
		return result;
	}

}
