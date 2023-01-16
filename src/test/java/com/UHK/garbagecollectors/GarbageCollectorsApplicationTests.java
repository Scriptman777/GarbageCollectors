package com.UHK.garbagecollectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class GarbageCollectorsApplicationTests {

	@Test
	void contextLoads() {
		//Test that everything loads okay
	}

}
