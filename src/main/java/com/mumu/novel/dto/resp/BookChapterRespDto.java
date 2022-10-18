package com.mumu.novel.dto.resp;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 响应 dto - 小说章节详细信息
 *
 * @author mumu
 * @date 2022/10/18
 */
@Data
@Builder
public class BookChapterRespDto implements Serializable {

    private static final long serialVersionUID = 2815995438969379159L;

    /**
     * 章节ID
     */
    @Schema(description = "章节ID")
    private Long id;

    /**
     * 小说ID
     */
    @Schema(description = "小说ID")
    private Long bookId;

    /**
     * 章节号
     */
    @Schema(description = "章节号")
    private Integer chapterNum;

    /**
     * 章节名
     */
    @Schema(description = "章节名")
    private String chapterName;

    /**
     * 章节字数
     */
    @Schema(description = "章节字数")
    private Integer chapterWordCount;

    /**
     * 章节更新时间
     */
    @Schema(description = "章节更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:dd")
    private Date chapterUpdateTime;

    /**
     * 是否收费;1-收费 0-免费
     */
    @Schema(description = "是否收费;1-收费 0-免费")
    private Integer isVip;

}
