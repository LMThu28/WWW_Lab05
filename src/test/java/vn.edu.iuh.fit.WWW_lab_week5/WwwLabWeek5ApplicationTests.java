package vn.edu.iuh.fit.WWW_lab_week5;


import com.neovisionaries.i18n.CountryCode;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;

@SpringBootTest
class LabWeek05ApplicationTests {
	@Autowired
	private AddressRepository repository;

	@PostConstruct
	void add_test() {
		Address add=new Address("11","NVQ","sg","5345643", CountryCode.AF);
		add.setId(1L);
		repository.save(add);

	}
	@Test
	void test_01(){
		Address add =repository.findById(1L).get();
		Assertions.assertEquals(add.getNumber(),"11");
	}
}
