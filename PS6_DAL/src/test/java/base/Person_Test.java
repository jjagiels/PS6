package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel perTest1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1995-07-04");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		perTest1 = new PersonDomainModel();
		perTest1.setFirstName("Justin");
		perTest1.setLastName("Jagielski");
		perTest1.setBirthday(dDate);
		perTest1.setCity("Townsend");
		perTest1.setPostalCode(19734);
		perTest1.setStreet("129 Edgar Rd.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(perTest1.getPerID());
		per = PersonDAL.getPer(perTest1.getPerID());
		assertNull("The Person should have been deleted from the database",per);		
	}

	@Test
	public void AddPersonTest() {		
			PersonDomainModel per;		
			per = PersonDAL.getPer(perTest1.getPerID());		
			assertNull("The Person shouldn't have been in the database",per);		
			PersonDAL.addPerson(perTest1);	
			
			per = PersonDAL.getPer(perTest1.getPerID());
			System.out.println(perTest1.getPerID() + " found");
			assertNotNull("The Person should have been added to the database",per);
		}

		@Test
		public void UpdatePersonTest()
		{		
			PersonDomainModel per;
			final String C_FIRSTNAME = "Adam";
			
			per = PersonDAL.getPer(perTest1.getPerID());		
			assertNull("The Person shouldn't have been in the database",per);		
			PersonDAL.addPerson(perTest1);	
			
			perTest1.setLastName(C_FIRSTNAME);
			PersonDAL.updatePerson(perTest1);
			
			per = PersonDAL.getPer(perTest1.getPerID());

			assertTrue("Name Didn't Change",perTest1.getLastName() == C_FIRSTNAME);
		}
		
		@Test
		public void DeletePersonTest()
		{		
			PersonDomainModel per;		
			per = PersonDAL.getPer(perTest1.getPerID());		
			assertNull("The Person shouldn't have been in the database",per);	
			
			PersonDAL.addPerson(perTest1);			
			per = PersonDAL.getPer(perTest1.getPerID());
			System.out.println(perTest1.getPerID() + " found");
			assertNotNull("The Person should have been added to the database",per);
			
			PersonDAL.deletePerson(perTest1.getPerID());
			per = PersonDAL.getPer(perTest1.getPerID());		
			assertNull("The Person shouldn't have been in the database",per);	
			
		}
}
