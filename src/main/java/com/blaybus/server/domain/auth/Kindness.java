package com.blaybus.server.domain.auth;

public enum Kindness {
    DILIGENT("부지런해요"),
    FUNNY("재밌어요"),
    METICULOUS("꼼꼼해요"),
    FRIENDLY("친근해요"),
    WARM("따뜻해요"),
    SENSITIVE("예민해요"),
    RESPONSIBLE("책임감 있어요"),
    EXPERIENCED("노하우 많아요"),
    DELICATE("섬세해요"),
    THOUGHTFUL("사려 깊어요"),
    CALM("차분해요"),
    PATIENT("인내심 있어요");

    private final String description;

    Kindness(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}