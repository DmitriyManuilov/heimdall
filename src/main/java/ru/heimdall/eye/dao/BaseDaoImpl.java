package ru.heimdall.eye.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings( "unchecked" )
public class BaseDaoImpl<T> implements BaseDao<T>
{
	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	private Class<T> persistentClass;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory( SessionFactory sessionFactory )
	{
		this.sessionFactory = sessionFactory;
	}
	
	public BaseDaoImpl(Class<T> persistentClass )
	{
		this.persistentClass =  persistentClass;
		
	}

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	protected Class<T> getPersistentClass()
	{
		return persistentClass;
	}
	
	protected Criteria createCriteria( Criterion... criteria )
	{
		Criteria c = getSession().createCriteria( getPersistentClass() );
		
		for( Criterion criterion : criteria )
		{
			c.add( criterion );
		}
		
		return c;
	}
	
	public T save( T entity )
	{
		try {
		getSession().saveOrUpdate( entity );
		return entity;
		} catch (ConstraintViolationException ex) {
			log.warn(ex);
			return null;
		}
	}
	
	public Collection<T> saveAll( Collection<T> entities ) {
		Collection<T> results = new ArrayList<T>();
		for(T eachEntity : entities) {
			T result = this.save(eachEntity);
			if(result!=null) {
				results.add(result);
			}
		}
		return entities;
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	public void deleteAll(Collection <T> entities) {
		for( T eachEntity : entities) {
			this.delete(eachEntity);
		}
	}
	
	public int truncateTable() {
	    String hql = String.format("delete from %s", getPersistentClass().getSimpleName());
	    Query query = getSession().createQuery(hql);
	    return query.executeUpdate();
	}

	public T findById( Long id )
	{
		return (T) getSession().get( getPersistentClass(), id );
	}

	public List<T> findAll()
	{
		return createCriteria().list();
	}
	
	public List<T> findAllWithOrder( Order order )
	{
		return createCriteria().addOrder( order ).list();
	}

	public T findOneByCriteria( Criterion... criteria )
	{
		return (T) createCriteria( criteria ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
	}

	public T findOneByCriteriaAndOrder( Order order , Criterion... criteria)
	{
		return (T) createCriteria( criteria ).addOrder(order).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
	}
	public List<T> findAllByCriteria( Criterion... criteria )
	{
		return createCriteria( criteria ).list();
	}		
	
	public List<T> findAllByCriteriaDistinct( Criterion... criteria )
	{
		return createCriteria( criteria ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public List<T> findAllByCriteriaWithOrder( Order order, Criterion... criteria )
	{
		return createCriteria( criteria ).addOrder( order ).list();
	}
	public List<T> findAllByCriteriaDistinctWithOrder( Order order, Criterion... criteria )
	{
		return createCriteria( criteria ).addOrder( order ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public long count()
	{
		return ( ( Long ) createCriteria().setProjection( Projections.rowCount() ).uniqueResult() ).longValue();
	}

	@Override
	public List<T> findAllDistinctWithOrder(Order order) {
		return createCriteria().addOrder(order).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
}
