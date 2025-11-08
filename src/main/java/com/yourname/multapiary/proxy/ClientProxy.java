package com.yourname.multapiary.proxy;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        // Register client-side only things like renderers
    }

    @Override
    public void init() {
        super.init();
        // Register client-side only things like key bindings
    }
}
