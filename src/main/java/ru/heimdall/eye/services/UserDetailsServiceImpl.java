package ru.heimdall.eye.services;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.heimdall.eye.domain.User;
import ru.heimdall.eye.dao.DaoFactory;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	private DaoFactory daoFactory;
	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = daoFactory.getDao(User.class).findOneByCriteria(Restrictions.eq("username",  username));
		if ( user == null ) { throw new UsernameNotFoundException(username+" not found");}
		return user;
	}
}
