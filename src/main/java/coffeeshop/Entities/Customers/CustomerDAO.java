package coffeeshop.Entities.Customers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import at.favre.lib.crypto.bcrypt.BCrypt;
import coffeeshop.Models.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;


public class CustomerDAO implements DAO<Customer> {
    final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    final EntityManager entityManager = factory.createEntityManager();
    
    @Override
    public Optional<Customer> get(Integer id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    public List<Customer> getAll(){
        return null;
    }

    public Boolean userNameExists(String userName)  {
        try {
            executeInsideTransaction(entityManager -> 
                                    entityManager.createQuery("SELECT u from Customer u WHERE u.user_name = :username", 
                                    Customer.class).setParameter("username", userName).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public Boolean emailExists(String email)  {
        try {
            executeInsideTransaction(entityManager -> 
                                    entityManager.createQuery("SELECT e from Customer e WHERE e.email = :email", 
                                    Customer.class).setParameter("email", email).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public Boolean phoneExists(String phone)  {
        try {
            executeInsideTransaction(entityManager -> 
                entityManager.createQuery("SELECT p from Customer p WHERE p.phone = :phone", 
                                        Customer.class).setParameter("phone", phone).getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public Customer login(String user, String pw) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        Customer customer = entityManager.createQuery("SELECT pwHash from Customer pwHash WHERE user_name = :userN",
                            Customer.class).setParameter("userN", user).getSingleResult();
        // gets pword field from customer obj
        Field pWordHash = customer.getClass().getDeclaredField("pword");
        pWordHash.setAccessible(true);
        BCrypt.Result result = BCrypt.verifyer().verify(pw.toCharArray(), pWordHash.get(customer).toString().trim());
        if (result.verified) {
            return customer;
        }
        else {
            return null;
        }
    }

    public void save(Customer customer){
        executeInsideTransaction(entityManager -> entityManager.persist(customer));
    }

    public List<Customer> listView(){
        return null;
    }

    public void update(Customer customer){

    }

    public void delete(Customer customer){

    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            action.accept(entityManager);
            transaction.commit(); 
        }
        catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
