/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.ro;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @name 分页查询
 * @author liting
 * @version $Id: Page.java, v 0.1 2017年9月20日 下午4:54:26 liting Exp $
 */
public class Page implements Serializable{
    
    /** TODO: detail description  */
    private static final long serialVersionUID = -3775663988960619786L;
    /**
     * 当前页
     */
    private Integer currentPage = 1;
    /**
     * 页大小
     */
    private Integer pageSize = 10;
    
    public Integer getStartIndex() {
        return (currentPage-1)*pageSize;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
