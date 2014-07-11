package be.post.web.data.site.csp;

import static org.junit.Assert.*;

import org.junit.Test;

import be.post.web.data.sites.csp.CspGetAddress;

public class CspGetAddressTest {

	@Test
	public void testPostAddress() throws Exception {
		CspGetAddress.httpInteract(false);
	}

}
