package com.unitTesting.streamunitTestdemo;
//import static org.mockito.ArgumentMatchers.contains;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.unitTesting.streamunitTestdemo.model.AccountType;
import com.unitTesting.streamunitTestdemo.model.Customer;

		import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StreamUnitTestDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	/****************************************    Demonstrating forEach()  *********************************************/
	@Test
	public void testCustomerExistence() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));

		listOfCustomer.forEach(customer -> {
			System.out.println(customer.getCustomerName() + " - " + customer.getAccountType());
		});

		asserThat(listOfCustomer, contains(
				hasProperty("AccountType", equalsTo(AccountType.CURRENT)),
				hasProperty("AccountType", equalsTo(AccountType.SAVINGS))
		));


	}


	private Object hasProperty(String string, Object equalsTo) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object equalsTo(AccountType current) {
		// TODO Auto-generated method stub
		return null;
	}

	private void asserThat(List<Customer> listOfCustomer, Object contains) {
		// TODO Auto-generated method stub

	}

	private Object contains(Object object, Object object2) {
		// TODO Auto-generated method stub
		return null;
	}

	/****************************************    Demonstrating map()  ************************************************
	 * Produces a new stream
	 * The new stream could be of different type
	 *  It applies a function on each element of Stream and store return value into new Stream.
	 *****************************************************************************************************************/
	@Test
	public void checkCustomerIdOfEmployee() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));

		// define id as AtomicInteger is mutable integer value 
		AtomicInteger id = new AtomicInteger(1);
		List<String> listOfId = listOfCustomer.stream()
				.map(Customer::getCustomerId).
				collect(Collectors.toList());

		assertEquals("NAB-Cus-1", listOfId.get(0));
	}
	/****************************************    Demonstrating collect()  ********************************************
	 * its one of the common ways to get stuff out of the stream once we are done with all the processing
	 *****************************************************************************************************************/
	@Test
	public void testingCollect() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Schmidt", 55, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Jonathan", 23, AccountType.CURRENT));

		List<Customer> newList = listOfCustomer.stream()
				.filter(e -> e.getCustomerName().startsWith("J"))
				.collect(Collectors.toList());

		assertEquals(2, newList.size());


	}

	/****************************************    Demonstrating .filter()  ********************************************
	 * this produces a new stream that contains elements of the original stream that pass a given test
	 * (specified by a Predicate).
	 * ****************************************************************************************************************/
	@Test
	public void testFilter() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Schmidt", 55, AccountType.CURRENT));

		List<Integer> filteredList= listOfCustomer.stream()
				.map(Customer::getCustomerAge)
				.filter(a -> a >= 30)
				.collect(Collectors.toList());


		assertEquals(2, filteredList.size());
		assertEquals(Integer.valueOf(34), filteredList.get(0));

	}
	@Test
	public void checkValidAccountType() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));

		List<Customer> currentCustomers = listOfCustomer.stream()
				.filter(c -> c.getAccountType() == AccountType.SAVINGS)
				.collect(Collectors.toList());

		assertNotEquals(AccountType.FLEX, currentCustomers.get(0).getAccountType());
		assertEquals(AccountType.SAVINGS, currentCustomers.get(0).getAccountType());

	}


	/****************************************    Demonstrating findFirst()  ********************************************
	 * returns an optional for the first entry in the stream
	 * the optional can be empty
	 *****************************************************************************************************************/
	@Test
	public void testValidAccountType() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Schmidt", 55, AccountType.CURRENT));

		Customer customer = listOfCustomer.stream()
				.filter(e -> e.getAccountType() == AccountType.SAVINGS)
				.findFirst()
				.orElse(null);


		assertNotEquals("Jonas", customer.getCustomerName());
		assertEquals("Lisa", customer.getCustomerName());

	}

	/****************************************    Demonstrating toArray()  **********************************************
	 * Gets an array out of stream.The new array customer will be ceated and then filled with elements
	 * of stream
	 *****************************************************************************************************************/

	@Test
	public void testArrayExist() {
		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));

		Customer[] customer = listOfCustomer.stream().toArray(Customer[]::new);

		assertTrue(customer.length != 0, "Array created with 3 indexes");


	}

	/****************************************    Demonstrating flatMap()  **********************************************
	 * It helps to flatten the data
	 ******************************************************************************************************************/
	@Test
	public void testTheList() {

		List<List<Integer>> listOfLists = Arrays.asList(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5, 6),
				Arrays.asList(7, 8, 9)
		);

		List<Integer> flattenedList = listOfLists.stream()
				.flatMap(List::stream)
				.collect(Collectors.toList());

		assertNotEquals(listOfLists, null);

	}

	/****************************************    Demonstrating peek()  **********************************************
	 * It is an intermediate operation
	 *It returns a new stream which can be used further unlike the stream which is produced by forEach() which
	 * is terminal in nature
	 ******************************************************************************************************************/
	@Test
	public void testForCaseConversionAndPrinting() {
		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));

		List<Customer> customerNames = listOfCustomer.stream()
				.peek(e -> e.getCustomerName())
				.peek(System.out::println)
				.collect(Collectors.toList());

		assertFalse(customerNames.isEmpty());
	}
/****************************************    Demonstrating Reduce()  **********************************************
 * A reduction operation (also called as fold) takes a sequence of input elements and combines them into a single summary
 * result by repeated application of a combining operation. We already saw few reduction operations like findFirst(), min() and max().
 ******************************************************************************************************************/
@Test
public void testForReduce() {

	List<Customer> listOfCustomer = new ArrayList<>();
	listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
	listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
	listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));

	int minAges = listOfCustomer.stream()
			.mapToInt(Customer::getCustomerAge)
			.reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

	assertEquals(34, minAges);

}


/****************************************    Demonstrating Sorted()  **********************************************
	 * It sorts the list with sorted operation.It sorts the stream elements based on the comparator passed we pass
	 * to it.
	 ******************************************************************************************************************/

	@Test
	public void checkSortedList() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		List<Customer> sortedList = listOfCustomer.stream()
				.sorted((e1, e2) -> e1.getCustomerName().compareTo(e2.getCustomerName()))
				.collect(Collectors.toList());

		assertEquals(sortedList.get(0).getCustomerName(), "Elsa");
		assertEquals(sortedList.get(1).getCustomerName(), "Jonas");
		assertEquals(sortedList.get(2).getCustomerName(), "Lisa");

		sortedList.forEach(customer -> {
			System.out.println(customer.getCustomerName() + " - " + customer.getAccountType());
		});

	}

	/****************************************    Demonstrating min()  **********************************************
	 * It returns the minimum element in the stream respectively
	 * based on a comparator
	 * They return an Optional since a result may or may not exist
	 ******************************************************************************************************************/
	@Test
	public void testingCustomerWithMinimumAge() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));

		Customer customerOfMinimumAge = listOfCustomer.stream()
				.min((e1, e2) -> Integer.compare(e1.getCustomerAge(), e2.getCustomerAge()))
				.orElseThrow(NoSuchElementException::new);

		assertEquals(23, customerOfMinimumAge.getCustomerAge());
		System.out.println(customerOfMinimumAge);

	}

	/****************************************    Demonstrating max()  **********************************************
	 * It returns the maximum element in the stream respectively
	 * based on a comparator
	 * They return an Optional since a result may or may not exist
	 ******************************************************************************************************************/
	@Test
	public void testingCustomerWithMaximumAge() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));

		Customer customerOfMinimumAge = listOfCustomer.stream()
				.max((e1, e2) -> Integer.compare(e1.getCustomerAge(), e2.getCustomerAge()))
				.orElseThrow(NoSuchElementException::new);
		assertEquals(34, customerOfMinimumAge.getCustomerAge());

		System.out.println(customerOfMinimumAge);

	}

	/****************************************    Demonstrating distinct()  **********************************************
	 * It returns the distinct elements in the stream
	 * it uses .equal method
	 ******************************************************************************************************************/
	@Test
	public void testingDistinctValue() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		List<AccountType> distinctAccountTypes = listOfCustomer.stream()
				.map(Customer::getAccountType)
				.distinct()
				.collect(Collectors.toList());

		assertEquals(distinctAccountTypes, List.of(AccountType.SAVINGS,AccountType.CURRENT,AccountType.FLEX));

		distinctAccountTypes.forEach(e->System.out.println(e));

	}

	/****************************************    Demonstrating allMatch()  **********************************************
	 * It takes a predicate and return a boolean
	 * allMatch() checks if the predicate is true for all the elements in the stream. Here, it returns false as
	 * soon as it encounters unmatched value
	 ******************************************************************************************************************/
	@Test
	public void testingAllMatch() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		boolean allmatch = listOfCustomer.stream()
				.allMatch(c->c.getAccountType().equals("AccountType.SAVINGS"));

		assertEquals(false, allmatch);

	}
	/****************************************    Demonstrating anyMatch()  **********************************************
	 * It takes a predicate and return a boolean
	 * anyMatch() checks if the predicate is true for any one element in the stream. Here, again short-circuiting is
	 * applied and true is returned immediately after the first element.
	 ******************************************************************************************************************/
	@Test
	public void testingAnyMatch() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		boolean anymatch = listOfCustomer.stream()
				.anyMatch(c->c.getAccountType().equals("AccountType.SAVINGS"));

	     assertTrue(true);

	}
	/****************************************    Demonstrating NoneMatch()  ********************************************
	 * It takes a predicate and return a boolean
	 * noneMatch() checks if there are no elements matching the predicate.
	 ******************************************************************************************************************/

	@Test
	public void testingNoneMatch() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		boolean nonematch = listOfCustomer.stream()
				.noneMatch(c->c.getAccountType().equals("AccountType.SAVINGS"));

		assertFalse(false, "matching found");

	}

	/****************************************    Demonstrating maxToInt()  **********************************************
	 * The most common way of creating an IntStream is to call mapToInt() on an existing stream
	 ******************************************************************************************************************/
@Test
	public void testMaxOnIntStream_thenGetMaxInteger() {

	List<Customer> listOfCustomer = new ArrayList<>();
	listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
	listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
	listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
	listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));
		Integer customerAge= listOfCustomer.stream()
				.mapToInt(Customer::getCustomerAge)
				.max()
				.orElseThrow(NoSuchElementException::new);

		assertEquals(45, customerAge);
	}
	/*********************************  Specialized operation: Demonstrating sum()  ************************************
	 *  Quite convenient when dealing with numbers.
	 ******************************************************************************************************************/
	@Test
	public void testingSum() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));
		Integer customerAge= listOfCustomer.stream()
				.mapToInt(Customer::getCustomerAge)
				.sum();

		assertEquals(128, customerAge);
	}
	/*********************************  Specialized operation: Demonstrating average()  ************************************
	 *  Quite convenient when dealing with numbers.
	 ******************************************************************************************************************/
	@Test
	public void testingAvg() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		Double customerAge= listOfCustomer.stream()
				.mapToDouble(Customer::getCustomerAge)
				.average()
				.orElseThrow(NoSuchElementException::new);

		assertEquals(32.0, customerAge);
	}
	/********************************  Specialized operation: Demonstrating range()  ************************************
	 * Quite convenient when dealing with numbers.
	 *******************************************************************************************************************/
	@Test
	public void testingRange() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));
         int minAge = 16;
		 int maxAge = 80;

		List<Customer> expectedList = listOfCustomer.stream()
				.filter(customer -> customer.getCustomerAge() >= minAge && customer.getCustomerAge() <= maxAge)
				.collect(Collectors.toList());
		List<Customer> filteredlist = listOfCustomer.stream()
				.filter(customer-> IntStream.range(minAge,maxAge+1)
						.anyMatch(age-> customer.getCustomerAge()==age))
						.collect(Collectors.toList());

		assertArrayEquals(expectedList.toArray(), filteredlist.toArray());
	}
	/********************************  Advanced Collect: Demonstrating toSet()  ************************************
	 * toSet() to get a set out of stream elements:
	 *******************************************************************************************************************/
	@Test
	public void testingToSet() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		Set<String> customerNames = listOfCustomer.stream()
						 .map(Customer::getCustomerName)
				         .collect(Collectors.toSet());
		assertEquals(4, customerNames.size());
	}
	/********************************  Advanced Collect: Demonstrating toCollection()  ********************************
	 * We can use Collectors.toCollection() to extract the elements into any other collection by passing in a
	 * Supplier<Collection>. We can also use a constructor reference for the Supplier:
	 ******************************************************************************************************************/
	@Test
	public void testingToCollection() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		Vector<String> customerNames = listOfCustomer.stream()
				.map(Customer::getCustomerName)
				.collect(Collectors.toCollection(Vector:: new));
		assertEquals(4, customerNames.size());
	}
	/********************************  Advanced Collect: Demonstrating SummarizingDouble()  ********************************
	 * summarizingDouble() is another interesting collector – which applies a double-producing mapping function
	 * to each input element and returns a special class containing statistical information for the resulting values
	 ******************************************************************************************************************/
	@Test
	public void testingSummarizingDouble() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		DoubleSummaryStatistics stats = listOfCustomer.stream()
				.collect(Collectors.summarizingDouble(Customer::getCustomerAge));

		assertEquals( 4,stats.getCount());
		assertEquals(128,stats.getSum());
		assertEquals(23,stats.getMin());
		assertEquals(45,stats.getMax());
		assertEquals(32.0, stats.getAverage());
	}
/********************************  Advanced Collect: Demonstrating summaryStatistics()  *****************************
 *SummaryStatistics() can be used to generate similar result when we’re using one of the specialized streams:
 ******************************************************************************************************************/
@Test
public void testingSummaryStatistics() {

	List<Customer> listOfCustomer = new ArrayList<>();
	listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
	listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
	listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
	listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

	DoubleSummaryStatistics stats = listOfCustomer.stream()
					.mapToDouble(Customer::getCustomerAge)
			        .summaryStatistics();

	assertEquals( 4,stats.getCount());
	assertEquals(128,stats.getSum());
	assertEquals(23,stats.getMin());
	assertEquals(45,stats.getMax());
	assertEquals(32.0, stats.getAverage());
}
	/********************************  Advanced Collect: Demonstrating partitioningBy()  **********************************
	 * We can partition a stream into two – based on whether the elements satisfy certain criteria or not.
	 ******************************************************************************************************************/
	@Test
	public void testingPartBy() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		Map<Boolean, List<Integer>> isEven = listOfCustomer.stream()
				.map(Customer::getCustomerAge)
				.collect(Collectors.partitioningBy(i -> i % 2 == 0));

		assertEquals(2,isEven.get(true).size());
		assertEquals(2,isEven.get(false).size());

	}

	/********************************  Advanced Collect: Demonstrating groupBy()  **********************************
	 * groupingBy() offers advanced partitioning – where we can partition the stream into more than just two groups.
	 ******************************************************************************************************************/
	@Test
	public void testingGroupBy() {

		List<Customer> listOfCustomer = new ArrayList<>();
		listOfCustomer.add(new Customer("Lisa", 23, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Jonas", 34, AccountType.CURRENT));
		listOfCustomer.add(new Customer("Elsa", 26, AccountType.SAVINGS));
		listOfCustomer.add(new Customer("Joseph", 45, AccountType.FLEX));

		Map<Character, List<Customer>> groupByAlphabet = listOfCustomer.stream().collect(
				Collectors.groupingBy(e -> (e.getCustomerName().charAt(0))));

		assertEquals("Jonas",groupByAlphabet.get('J').get(0).getCustomerName());
		assertEquals("Joseph",groupByAlphabet.get('J').get(1).getCustomerName());


	}

}