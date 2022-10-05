package com.mumu.novel.core.common.req;

/**
 * 分页请求基类
 *
 * @author mumu
 * @date 2022/10/05
 */
public class PageReqDto {
    /**
     * 请求页码（默认为第 1 页）
     */
    private int pageNum = 1;

    /**
     * 每页大小（默认每页 10 条）
     */
    private int pageSize = 10;

    /**
     * 是否查询所有（默认不查询所有）
     * 为 true 时候，pageNum 与 pageSize 失效
     */
    private boolean fetchAll = false;
}
