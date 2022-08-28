package com.heitor.projeto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ProjetoDeTestesApplicationTests {

	@Test
	void main() {
		ProjetoDeTestesApplication.main(new String[] {});
	}

}
