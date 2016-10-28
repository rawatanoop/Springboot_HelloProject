package helloworld.lifeline.controller;

import java.security.Principal;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.UserModel;
import helloworld.lifeline.service.IUserService;
import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;

@Controller
@EnableOAuth2Sso
public class LoginController extends WebSecurityConfigurerAdapter {

	private static final Logger logger = Logger.getInstance(LoginController.class);
	@Autowired
	private IUserService userService;

	/****
	 * This method checks whether the user is successfully authenticated by
	 * google and if it's a valid user it associated a valid login session
	 * attribute.
	 * 
	 * @param session
	 * @param principal
	 * @return
	 * @throws ForbiddenException
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String login(HttpSession session, Principal principal) throws ForbiddenException, UnauthorizedException {
		logger.info("User is trying to login");
		if (principal == null)
			throw new ForbiddenException("User google authenticaion is missing : Access Denied");
		OAuth2Authentication auth = (OAuth2Authentication) principal;

		if (auth.isAuthenticated()) {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
					.getDetails();

			String domain = details.get("hd");
			if (!"practo.com".equalsIgnoreCase(domain))
				throw new UnauthorizedException("Domain name other than 'practo.com' are not allowed");

			String email = details.get("email");
			String name = details.get("name");

			UserModel user = userService.isValidUser(email);

			if (user == null) {
				user = new UserModel();
				user.setEmail(email);
				user.setName(name);
				user = userService.create(user);
			}
			session.setAttribute("user", user);
			logger.info("User successfully logged-in");

			return name;

		} else {
			throw new UnauthorizedException("Login failed. Please try again");
		}

	}

	/****
	 * This method invalidates the session, so that any further request will not
	 * be served .
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		logger.info("Session is invalidated : logged out from account.");
		return "redirect:/index.html";
	}

	@RequestMapping(value = "/hello")
	public String test(HttpSession session) {
		return "index2";
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**", "/login**", "/webjars/**", "/js/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}
}
