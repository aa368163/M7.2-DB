package main;

import org.junit.jupiter.api.*;

public class DiscountTest {

	@DisplayName("不同年紀的折扣")
	@Nested
	class DifferentAges {
		String dateTime = "2021-05-26 週三 14:30:00";

		@Test
		void testAgeHasDiscount() throws Throwable {
			Identity identity = new Identity(10, false, false);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.8, discount.getDiscount());
		}

		@Test
		void testAgeLessThan() throws Throwable {
			Identity identity = new Identity(2, false, true);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Your age is too young.", exception.getMessage());
			}
		}

		@Test
		void testAgeMoreThan() throws Throwable {
			Identity identity = new Identity(88, true, false);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Your age doesn't meet the requirements.", exception.getMessage());
			}
		}

		@Test
		void testAgeHasDiscount_2() throws Throwable {
			Identity identity = new Identity(70, false, false);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.8, discount.getDiscount());
		}

	}

	@DisplayName("營業與非營業時間")
	@Nested
	class DifferentBusinessHours {
		@Test
		void testEarlyBirdBusiness() throws Throwable {
			Identity identity = new Identity(25, false, false);
			Discount discount = new Discount(identity, "2021-05-26 週三 06:30:00");
			Assertions.assertEquals(0.8, discount.getDiscount());
		}

		@Test
		void testHaveBusiness() throws Throwable {
			Identity identity = new Identity(25, false, false);
			Discount discount = new Discount(identity, "2021-05-26 週三 10:30:00");
			Assertions.assertEquals(1, discount.getDiscount());
		}

		@Test
		void testNoBusiness() throws Throwable {
			Identity identity = new Identity(25, true, false);
			try {
				Discount discount = new Discount(identity, "2021-05-26 週三 04:30:00");
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}

		@Test
		void testNoBusiness_2() throws Throwable {
			Identity identity = new Identity(25, false, true);
			try {
				Discount discount = new Discount(identity, "2021-05-26 週三 22:10:00");
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}

		@Test
		void testNoBusiness_3() throws Throwable {
			Identity identity = new Identity(25, false, false);
			try {
				Discount discount = new Discount(identity, "2021-05-26 週三 23:10:00");
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}

	}

	@DisplayName("是否為會員或團體")
	@Nested
	class MemberOrGroup {
		String dateTime = "2021-05-26 週三 14:30:00";

		@Test
		void testIsMember() throws Throwable {
			Identity identity = new Identity(25, true, false);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.5, discount.getDiscount());
		}

		@Test
		void testIsGroup() throws Throwable {
			Identity identity = new Identity(25, false, true);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.7, discount.getDiscount());
		}
	}

	@DisplayName("Set")
	@Nested
	class Set {
		String dateTime = "2021-05-26 週三 14:30:00";

		@Test
		void testSet() throws Throwable {
			Identity identity = new Identity(25, true, false);
			identity.setAge(30);
			identity.setMember(false);
			identity.setGroup(true);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.7, discount.getDiscount());
		}
	}

	@DisplayName("價錢")
	@Nested
	class Money {
		@Test
		void testMoney() throws Throwable {
			Identity identity = new Identity(25, true, false);
			Discount discount = new Discount(identity, "2021-05-26 週三 10:30:00");
			Payment payment = new Payment(discount, "2021-05-26 週三 10:30:00");
			Assertions.assertEquals(100.0, payment.getTotalCharge());
		}

		@Test
		void testMoney_2() throws Throwable {
			Identity identity = new Identity(25, false, false);
			Discount discount = new Discount(identity, "2021-05-29 週六 10:30:00");
			Payment payment = new Payment(discount, "2021-05-29 週六 10:30:00");
			Assertions.assertEquals(250.0, payment.getTotalCharge());
		}
	}
}
