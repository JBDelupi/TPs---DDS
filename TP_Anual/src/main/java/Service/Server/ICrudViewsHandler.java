package Service.Server;

public interface ICrudViewsHandler {

    void index(Object ... Context);
    void show(Object ... Context);
    void create(Object ... Context);
    void save(Object ... Context);
    void edit(Object ... Context);
    void update(Object ... Context);
    void delete(Object ... Context);

}
