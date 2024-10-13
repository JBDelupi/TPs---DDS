package Service.Server;

import io.javalin.http.Context;

import java.io.IOException;

public interface ICrudViewsHandler {

    void index(Context context);
    void show(Context context);
    void create(Context context) throws IOException;
    void save(Context context);
    void edit(Context context);
    void update(Context context);

}
