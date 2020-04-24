package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Michal Klíma
 */

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
	public void createUser(User user) {
		em.persist(user);
	}

    @Override
	public User getUserById(long id) {
		return em.find(User.class, id);
	}
	
    @Override
	public void removeUser(User user) {
		em.remove(user);
	}


    @Override
	public List<User> findAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
	}
    
    @Override
    public void updateUser (User u) {
        em.merge(u);
    }

	@Override
	public User getUserByEmail(String email) {
		return em.find(User.class, email);
	}

	@Override
	public User getUserByLogin(String login) {
		return em.find(User.class, login);
	}

}