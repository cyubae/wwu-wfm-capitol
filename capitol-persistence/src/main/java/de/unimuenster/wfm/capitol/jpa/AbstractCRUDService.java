package de.unimuenster.wfm.capitol.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Abstract class implementing the {@code AbstractCRUDService<T>} interface. It implements all the defined CRUD methods
 * with generics, so that each Service Bean extending this class does not need to implement this functionality
 * separately. This reduces code duplication and ensures that most Beans (all Beans extending this class) will provide
 * a common interface and can be used similarly.
 * The generic parameter {@code T} will be replaced with a concrete object type for each concrete implementation of
 * this class.
 */
@Stateless
@Named
public abstract class AbstractCRUDService<T> {
    /**
     * {@code Class<T>} variable storing the class type/object type of the concrete implementation of the
     * {@code AbstractCRUDServiceBean<T>}.
     */
    private final Class<T> entityClass;

    /**
     * {@code EntityManager} object that will be dependency injected at runtime. The {@code EntityManager} is used for
     * persistence, which means that it provides 'an interface' to the database. So via the {@code EntityManager} later
     * on all CRUD operations (create, read, update and remove) are carried out.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Constructor for the {@code AbstractCRUDServiceBean} objects. Presets the classes {@code Class<T>}
     * {@code entityClass} attribute with the class type given via the parameter {@code Class<T> entityClass}.
     *
     * @param entityClass {@code Class<T>} specifying the class type the concrete {@code AbstractCRUDServiceBean<T>}
     *                    will handle.
     */
    public AbstractCRUDService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     * Method implementing the CREATE operation that will create a database entry for the provided {@code T} entity,
     * which for this {@code AbstractCRUDServiceBean<T>} will have the class type specified in {@code this.entityClass}.
     * After the entity has been written to the database the method will return the entity back to the caller.
     *
     * @param entity {@code T} object being of type {@code this.entityClass} which will be stored to the database.
     * @return {@code T} object being of type {@code this.entityClass} which has been stored to the database.
     */
    public T create(T entity) {
        //  Use the entity manager to persist the entity to the database.
        entityManager.persist(entity);
        //  Return the inputted and saved database.
        return entity;
    }

    /**
     * Method implementing one specific READ operation that will fetch exactly one object from the database
     * corresponding to a given {@code int} ID. The returned object with type {@code T} will have the type specified in
     * {@code this.entityClass}.
     *
     * @param id {@code int} ID specifying a concrete object that should be fetched from the database.
     * @return {@code T} object being of type {@code this.entityClass} corresponding to the ID {@code id}
     * @throws IllegalArgumentException whenever no object could be found in the database for a given ID
     */
    public T find(int id) throws IllegalArgumentException {
        //  Use the EntityManager to find the searched item via the class of the item (to be able to access the right
        //  database table) as well as the ID to access the specific object.
        T entity = entityManager.find(entityClass, id);
        //  If entity is null after the previous step, it is known that no object with the given ID exists in the
        //  database. In this case an Exception has to be raised to indicate this READ failure.
        if (entity == null)
            throw new IllegalArgumentException(String.format("Entity with ID %s not found", id));
        //  Return the entity which has been fetched from the DB based on a given ID.
        return entity;
    }

    /**
     * Method implementing one specific READ operation that will fetch all objects of one type from the database. The
     * returned objects with type {@code T} will have the type specified in {@code this.entityClass}.
     *
     * @return {@code List<T>} containing all objects of a type {@code this.entityClass} which can be found in the
     * database.
     */
    public List<T> findAll() {
        //  Make use of the Criteria API to define a database query to obtain all objects for the current class type
        //  (for the concrete implementation of this class). Is type-safe and allows to create the query based on Java
        //  interfaces rather than plain SQL.
        //  Use the helper method createQuery() to get a pre-configured CriteriaQuery.
        CriteriaQuery<T> q = createQuery();
        //  Set the entityClass variable containing the class type as a query parameter, as this way the ORM will know
        //  which database table it needs to access and which type the nodes need to have when returned.
        q.select(q.from(entityClass));
        //  Return the full list of objects that has been found in the database for the current class type.
        return entityManager.createQuery(q).getResultList();
    }

    /**
     * Method implementing the UPDATE operation that will update an object/entity that has already been persisted to
     * the database with a new version. Specifically, it will update the database entry for the provided {@code T}
     * entity, which for a concrete instantiation of {@code AbstractCRUDServiceBean<T>} will have the class type
     * specified in {@code this.entityClass}.
     *
     * @param entity {@code T} object being of type {@code this.entityClass} which will be updated in the database.
     */    
    public void update(T entity) {
        entityManager.merge(entity);
    }

    /**
     * Method implementing the DELETE operation that will remove a specified entity from the database. Specifically, it
     * will delete the database entry corresponding to the provided {@code T} entity, which for a concrete instantiation
     * of {@code AbstractCRUDServiceBean<T>} will have the class type specified in {@code this.entityClass}.
     *
     * @param entity {@code T} object being of type {@code this.entityClass} which will be deleted from the database.
     */
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Helper method that creates a {@code CriteriaQuery<T>} object with the result type specified by
     * {@code this.entityClass}.
     *
     * @return {@code CriteriaQuery<T>} with a preset result type {@code this.entityClass}
     */
    protected CriteriaQuery<T> createQuery() {
        return entityManager.getCriteriaBuilder().createQuery(entityClass);
    }
}
