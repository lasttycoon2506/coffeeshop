package coffeeshop.Entities.Items;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class ItemDAO implements DAO<Item>{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    public Optional<Item> get(Integer itemId) {
        EntityManager em = getEntityManager();
        return Optional.ofNullable(em.find(Item.class, itemId));
    }
    public List<Item> getAll() {
        return null;
    }

    public void save(Item item) {
        executeInsideTransaction(entityManager -> entityManager.persist(item));
    }

    public void edit(Item t) {
    }

    public void delete(Item item) {
        executeInsideTransaction(entityManager -> {
            entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));  
        });
    }

    private static void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            action.accept(em);
            transaction.commit(); 
        }
        catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
