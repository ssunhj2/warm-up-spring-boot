package com.xun.warmup.domain;

public class Member {
    // 회원id (시스템상에서 사용)
    private Long seqId;
    // 사용자가 설정한 Id
    private String id;
    // 회원명
    private String name;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
