package com.example.demo.business.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {
    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryConfig(@Value("${cloud.key}") String key,
                            @Value("${cloud.secret}") String secret ,
                            @Value("${cloud.name}") String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName =cloud;
        cloudinary.config.apiSecret =secret;
        cloudinary.config.apiKey=key;
    }

    public Map upload(Object file, Map options){
        try{
            return cloudinary.uploader().upload(file,options);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method generates the URL for the actor's list
     */
    public String createUrl(String name) {
        return cloudinary
                .url()
                .transformation(new Transformation()
                        .width(100)
                        .height(100)
                        .crop("fill")
                        .radius(50)
                        .gravity("face"))
                .generate(name);
    }

    /**
     * This method generates the URL for an image whose name is known and has been provided
     */
    public String createUrl(String name, int width, int height) {
        return cloudinary
                .url()
                .transformation(new Transformation()
                        .width(width)
                        .height(height)
                        .crop("fill")
                        .radius(50)
                        .gravity("face"))
                .generate(name);

    }

    /**
     * Creates a transformation from the URL provided
     */
    public String createSmallImage(String url, int width, int height) {
        return cloudinary
                .url()
                .transformation(new Transformation()
                        .width(width)
                        .height(height)
                        .crop("fill")
                        .radius(50)
                        .gravity("face"))
                .type("fetch").generate(url);

    }

    public String createUrl(String name, int width, int height, String action){
        return cloudinary
                .url()
                .transformation(new Transformation()
                        .width(width) //50
                        .height(height)//50
                        .border("2px_solid_black")
                        .crop(action)) //thumb
                .imageTag(name);//my message
    }
}
