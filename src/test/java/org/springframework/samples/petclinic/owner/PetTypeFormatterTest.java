package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PetTypeFormatter.class, PetRepository.class})
@ExtendWith(SpringExtension.class)
public class PetTypeFormatterTest {

	@MockBean(name = "petRepository")
	private PetRepository petRepository;

	@Autowired
	private PetTypeFormatter petTypeFormatter;

	@Test
	public void testPrint() {
		Locale locale = new Locale("Language");
		assertNull(this.petTypeFormatter.print(new PetType(), locale));
	}

	@Test
	public void testPrint2() {
		Locale locale = new Locale("Language");
		assertNull(this.petTypeFormatter.print(new PetType(), locale));
	}

	@Test
	public void testParse() throws ParseException {
		when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());
		assertThrows(ParseException.class,
			() -> this.petTypeFormatter.parse("Dog", new Locale("Language")));
	}

	@Test
	public void testParse2() throws ParseException {
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");
		ArrayList<PetType> petTypeList = new ArrayList<PetType>();
		petTypeList.add(petType);
		when(this.petRepository.findPetTypes()).thenReturn(petTypeList);
		assertSame(petType, this.petTypeFormatter.parse("Dog", new Locale("Language")));
	}

	@Test
	public void testParse3() throws ParseException {
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Bella");
		ArrayList<PetType> petTypeList = new ArrayList<PetType>();
		petTypeList.add(petType);
		when(this.petRepository.findPetTypes()).thenReturn(petTypeList);
		assertThrows(ParseException.class,
			() -> this.petTypeFormatter.parse("Dog", new Locale("Language")));
	}
}

