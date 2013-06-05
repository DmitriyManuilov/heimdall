package ru.heimdall.eye.tasks;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.heimdall.eye.domain.Group;
import ru.heimdall.eye.domain.User;
import ru.heimdall.eye.services.UserService;

import freemarker.log.Logger;

@Component
public class OnStartup implements ApplicationListener<ContextRefreshedEvent> {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	@Value( "#{wiringProperties['default.admin.username']}" )
	private String defaultAdminUsername;
	@Value( "#{wiringProperties['default.admin.password']}" )
	private String defaultAdminPassword;
	@Value( "#{wiringProperties['default.admin.installOnStartup']}" )
	private boolean installDefaultAdminUser;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if ( installDefaultAdminUser && defaultAdminUsername != null && defaultAdminPassword != null ) {
				
			User admin = userService.findByUsername("admin");
			
			if (admin == null) {
				
				admin = new User();
				admin.setUsername(defaultAdminUsername);
				admin.setPassword(defaultAdminPassword);
			
				Group userGroup = new Group();
				userGroup.setName("ROLE_USER");
				userService.installNewGroup(userGroup);
			
				Group adminGroup = new Group();
				adminGroup.setName("ROLE_ADMIN");
				userService.installNewGroup(adminGroup);
			
				admin.setGroups( new ArrayList<Group>(2));
				admin.getGroups().add(userGroup);
				admin.getGroups().add(adminGroup);
			
				userService.installNewUser(admin);
				log.info("Installed default admin account with username="+defaultAdminUsername);
			}
		}
	}

}
