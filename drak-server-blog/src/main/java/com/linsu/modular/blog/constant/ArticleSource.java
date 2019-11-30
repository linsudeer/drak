package com.linsu.modular.blog.constant;

public enum ArticleSource {
    PC("PC"), WX("WX");

    private String source;

    ArticleSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
