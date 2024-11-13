package Models.Repository.EntityManager;

import Models.Domain.Reporte.TemplateReporte;
import jakarta.persistence.*;

import java.util.function.Supplier;


public class EntityManagerHelper {

    private static EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db");
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static EntityManagerFactory emf() {
        if(emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("db");
        }
        return emf;
    }

    private static ThreadLocal<EntityManager> threadLocal() {
        if(threadLocal == null) {
            threadLocal = new ThreadLocal<>();
        }
        return threadLocal;
    }

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal().get();
        if (manager == null || !manager.isOpen()) {
            manager = emf().createEntityManager();
            threadLocal().set(manager);
        }
        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if(em != null) {
            threadLocal.set(null);
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }

    public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(!tx.isActive()){
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }

    }

    public static void rollback(){
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.rollback();
        }
    }

    public static Query createQuery(String query, Class<TemplateReporte> templateReporteClass) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o){
        entityManager().persist(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }
    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch(Throwable e) {
            rollback();
            throw e;
        }
    }
}
