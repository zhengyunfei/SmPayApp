package com.zero2ipo.common.entity;

import java.io.Serializable;


/**
 * @title 评论实体类
 * @description: 系统评论实体对象类，对应数据库中的S9_SYS_COMMENT表。
 * @author wangli
 * 
 */
public class Comment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String commentId;// 评论标识
	
	private String vipId; // 会员标识;

	private String content; // 评论内容;

	private String commentDate; // 评论时间;

	private String commentLevel; // 评论等级;0 表示好，1 表示中，2 表示差

	private String status; // 有效标识; 0 表示无效，1表示有效



	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
