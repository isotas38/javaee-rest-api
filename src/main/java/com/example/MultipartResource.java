package com.example;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by murayama on 2015/09/10.
 */
@Path("multipart")
public class MultipartResource {
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("item1") String item1,
            @FormDataParam("item2") String item2,
            @FormDataParam("file") InputStream in,
            @FormDataParam("file")FormDataContentDisposition info
    ) throws IOException {
        System.out.println(item1);
        System.out.println(item2);
        java.nio.file.Path path = Paths.get("/var/www/html", info.getFileName());
        if(Files.exists(path)) {
            String message = "file: " + info.getFileName() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(message).build();
        } else {
            Files.copy(in, path);
            return Response.status(Response.Status.OK).build();
        }
    }
}
