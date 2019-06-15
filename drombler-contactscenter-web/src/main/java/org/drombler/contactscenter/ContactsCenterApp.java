package org.drombler.contactscenter;

import ch.sbb.esta.openshift.gracefullshutdown.GracefulshutdownSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsCenterApp {

	public static void main(String[] args) {
		GracefulshutdownSpringApplication.run(ContactsCenterApp.class, args);
	}

}
