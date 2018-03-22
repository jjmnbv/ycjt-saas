package com.beitu.saas.risk.domain.platform;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2017-05-27 11:59
 */
public abstract class TripleServiceBaseInput implements Serializable {
    private static final long serialVersionUID = -8973697330227052995L;

    public abstract String getName();

    public abstract String getMobile();

    public abstract String getIdentityNo();
}
