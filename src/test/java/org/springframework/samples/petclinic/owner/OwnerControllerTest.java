package org.springframework.samples.petclinic.owner;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {OwnerController.class})
@ExtendWith(SpringExtension.class)
public class OwnerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean(name = "ownerRepository")
	private OwnerRepository ownerRepository;

	@MockBean(name = "visitRepository")
	private VisitRepository visitRepository;

	@Test
	public void testProcessFindForm_singleOwner() throws Exception {
		Owner owner = new Owner();
		owner.setLastName("Doe");
		owner.setId(1);
		owner.setCity("Oxford");
		owner.setAddress("42 Main St");
		owner.setFirstName("Jane");
		owner.setTelephone("4105551212");
		when(this.ownerRepository.findByLastName(or(isA(String.class), isNull())))
			.thenReturn(Collections.singletonList(owner));

		this.mockMvc.perform(get("/owners").param("lastName", "Doe"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));
	}

	@Test
	public void testProcessFindForm_severalOwners() throws Exception {
		Owner owner1 = new Owner();
		owner1.setLastName("Doe");
		owner1.setId(1);
		owner1.setCity("Oxford");
		owner1.setAddress("42 Main St");
		owner1.setFirstName("Jane");
		owner1.setTelephone("4105551212");

		Owner owner2 = new Owner();
		owner2.setLastName("Doe");
		owner2.setId(1);
		owner2.setCity("Oxford");
		owner2.setAddress("1 High St");
		owner2.setFirstName("John");
		owner2.setTelephone("5121241055");

		when(this.ownerRepository.findByLastName(or(isA(String.class), isNull())))
			.thenReturn(Arrays.asList(owner1, owner2));

		this.mockMvc.perform(get("/owners").param("lastName", "Doe"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownersList"));
	}
}

