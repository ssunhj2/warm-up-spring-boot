package com.xun.warmup.domain;

public class Member {
    // 회원id (시스템상에서 사용)
    private Long id;
    // 회원명
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
