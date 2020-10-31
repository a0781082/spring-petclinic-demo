package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OwnerController.class})
@ExtendWith(SpringExtension.class)
public class OwnerControllerTest {

	@Autowired
	private OwnerController ownerController;

	@MockBean(name = "ownerRepository")
	private OwnerRepository ownerRepository;

	@MockBean(name = "visitRepository")
	private VisitRepository visitRepository;

	@Test
	public void testCountDogs() {
		assertEquals(0L, this.ownerController.countDogs(new Owner()));
	}

	@Test
	public void testCountDogs2() {
		Owner owner = new Owner();
		owner.setLastName("Doe");
		assertEquals(0L, this.ownerController.countDogs(owner));
	}

	@Test
	public void testCountDogs3() {
		LocalDate birthDate = LocalDate.ofEpochDay(1L);
		Pet pet = new Pet();
		pet.setBirthDate(birthDate);
		Owner owner = new Owner();
		owner.setLastName("Doe");
		owner.setId(1);
		owner.setCity("Oxford");
		owner.setPetsInternal(new HashSet<Pet>());
		owner.setAddress("42 Main St");
		owner.setFirstName("Jane");
		owner.setTelephone("4105551212");
		pet.setOwner(owner);
		pet.setVisitsInternal(new ArrayList<Visit>());
		pet.setId(1);
		pet.setName("Bella");
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");
		pet.setType(petType);
		HashSet<Pet> petSet = new HashSet<Pet>();
		petSet.add(pet);
		Owner owner1 = new Owner();
		owner1.setPetsInternal(petSet);
		assertEquals(1L, this.ownerController.countDogs(owner1));
	}

	@Test
	public void testCountDogs4() {
		LocalDate birthDate = LocalDate.ofEpochDay(1L);
		Pet pet = new Pet();
		pet.setBirthDate(birthDate);
		Owner owner = new Owner();
		owner.setLastName("Doe");
		owner.setId(1);
		owner.setCity("Oxford");
		owner.setPetsInternal(new HashSet<Pet>());
		owner.setAddress("42 Main St");
		owner.setFirstName("Jane");
		owner.setTelephone("4105551212");
		pet.setOwner(owner);
		pet.setVisitsInternal(new ArrayList<Visit>());
		pet.setId(1);
		pet.setName("Bella");
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");
		pet.setType(petType);
		HashSet<Pet> petSet = new HashSet<Pet>();
		petSet.add(pet);
		LocalDate birthDate1 = LocalDate.ofEpochDay(1L);
		Pet pet1 = new Pet();
		pet1.setBirthDate(birthDate1);
		Owner owner1 = new Owner();
		owner1.setLastName("Doe");
		owner1.setId(1);
		owner1.setCity("Oxford");
		owner1.setPetsInternal(new HashSet<Pet>());
		owner1.setAddress("42 Main St");
		owner1.setFirstName("Jane");
		owner1.setTelephone("4105551212");
		pet1.setOwner(owner1);
		pet1.setVisitsInternal(new ArrayList<Visit>());
		pet1.setId(1);
		pet1.setName("Bella");
		PetType petType1 = new PetType();
		petType1.setId(1);
		petType1.setName("Dog");
		pet1.setType(petType1);
		petSet.add(pet1);
		Owner owner2 = new Owner();
		owner2.setPetsInternal(petSet);
		assertEquals(2L, this.ownerController.countDogs(owner2));
	}
}

