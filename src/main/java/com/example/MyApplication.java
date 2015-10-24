package com.example;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        super(MultiPartFeature.class);
        packages(this.getClass().getPackage().getName());
    }
}