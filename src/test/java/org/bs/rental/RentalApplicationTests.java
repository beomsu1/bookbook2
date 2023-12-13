package org.bs.rental;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class RentalApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	public void dbConnectionTest(){

		try {
			dataSource.getConnection();

			log.info("연결 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
