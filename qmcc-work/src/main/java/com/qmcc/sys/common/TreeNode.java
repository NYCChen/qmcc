package com.qmcc.sys.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeNode {

	private Integer id;
	@JsonProperty("parentId")//此处是为了在部门管理中的前端模板而转化为parentId
	private Integer pid;
	private String title;
	private String icon;
	private String href;
	private Boolean spread;
	private List<TreeNode> children=new ArrayList<TreeNode>();
	/**
	 *首页左边导航树的构造器
	 */
	public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.icon = icon;
		this.href = href;
		this.spread = spread;
	}


    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
    }
}
