package com.assertion.passwordmanager;

import java.util.HashMap;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.assertion.passwordmanager.dao.Website;
import com.assertion.passwordmanager.operations.DeletePasswords;
import com.assertion.passwordmanager.operations.EditPassword;
import com.assertion.passwordmanager.operations.FetchSavedPasswords;
import com.assertion.passwordmanager.operations.SavePassword;


@CrossOrigin
@RestController
public class PasswordController extends SpringBootServletInitializer {

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public HashMap<String, String> home() {
		System.out.println("**********Getting Passwords**********");
		return FetchSavedPasswords.fetchPasswords();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public boolean edit(@RequestBody Website myWebsite) {
		System.out.println("**********Editing password for : " + myWebsite.getName() + "**********");
		return EditPassword.editPassword(myWebsite);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public boolean save(@RequestBody Website myWebsite) {
		System.out.println("**********Saving new password for : " + myWebsite.getName() + "**********");
		return SavePassword.insertPassword(myWebsite);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(@RequestBody String[] deletePasswords) {
		return DeletePasswords.deletePassword(deletePasswords);
	}

}
