package com.will.zengzewill.mobilecodeinfo.protocol;

import com.will.zengzewill.mobilecodeinfo.model.InfoModel;

/**
 * Created by ze on 16/4/11.
 */
public interface MobileCodeInfoDelegate {
    public void didFinishFetchInfo(InfoModel model);
    public void didFailInfo(String err);
}
