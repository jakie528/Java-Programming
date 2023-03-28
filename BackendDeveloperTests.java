// --== CS400 Project One File Header ==--
// Name: Ziqi Liao
// CSL Username: zliao
// Email: zliao47@wisc.edu
// Lecture #: Section 3: MoWeFr 1:20PM - 2:10PM, Florian Heimerl
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BackendDeveloperTests {

	/**
	 * test for findPostsByTitleWords
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test1() {

		PostReaderInterface list = new PostReaderBD();

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysBD<String, PostInterface>();

		CHSearchBackendBD bd = new CHSearchBackendBD(hashtable, list);

		// load the data
		try {
			bd.loadData("xxx");
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			return false;
		}

		// create two posts to compare
		List<String> ls = new ArrayList<>();
		ls.add("TITLE:title1 title2URL:url1/url2BODY:body1 body2");
		ls.add("TITLE:title1 title4URL:url3/url4BODY:body3 body1");

		if (!bd.findPostsByTitleWords("title1").equals(ls)) {
			return false;
		}

		return true;
	}

	/**
	 * test for getStatisticsString
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test2() {

		PostReaderBD list = new PostReaderBD();

		HashtableWithDuplicateKeysBD<String, PostInterface> hashtable = new HashtableWithDuplicateKeysBD<String, PostInterface>();

		CHSearchBackendBD bd = new CHSearchBackendBD(hashtable, list);

		if (!bd.getStatisticsString().equals("0")) {
			return false;
		}

		return true;
	}

	/**
	 * test for findPostsByBodyWords
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test3() {
		PostReaderInterface list = new PostReaderBD();

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysBD<String, PostInterface>();

		CHSearchBackendBD bd = new CHSearchBackendBD(hashtable, list);

		// load the data
		try {
			bd.loadData("xxx");
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			return false;
		}

		// create two posts to compare
		List<String> ls = new ArrayList<>();
		ls.add("TITLE:title1 title2URL:url1/url2BODY:body1 body2");
		ls.add("TITLE:title1 title4URL:url3/url4BODY:body3 body1");

		if (!bd.findPostsByBodyWords("body1").equals(ls)) {
			return false;
		}

		return true;

	}

	/**
	 * test for findPostsByTitleOrBodyWords
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test4() {

		PostReaderInterface list = new PostReaderBD();

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysBD<String, PostInterface>();

		CHSearchBackendBD bd = new CHSearchBackendBD(hashtable, list);

		// load the data
		try {
			bd.loadData("xxx");
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			return false;
		}

		// create two posts to compare
		List<String> ls = new ArrayList<>();
		ls.add("TITLE:title1 title2URL:url1/url2BODY:body1 body2");
		ls.add("TITLE:title1 title4URL:url3/url4BODY:body3 body1");

		if (!bd.findPostsByBodyWords("title1 body1").equals(ls)) { // both
			return false;
		}

		return true;

	}

	/**
	 * test the loadData() exception
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test5() {

		PostReaderInterface list = new PostReaderBD();

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysBD<String, PostInterface>();

		CHSearchBackendBD bd = new CHSearchBackendBD(hashtable, list);

		// load the data
		try {
			bd.loadData("xxx");
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * integration test 1 include AE,DW,FD,BD
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test6() {

		try {
			PostReaderInterface list = new PostReaderDW();

			HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
			CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, list);

			// load the large.txt and find by post by title "grow"
			TextUITester tester = new TextUITester("L\n ./data/fake.txt\n T\n grow \n\n Q \n");

			Scanner scan = new Scanner(System.in);

			CHSearchFrontendFD fd = new CHSearchFrontendFD(scan, backend);

			fd.runCommandLoop();

			String output = tester.checkOutput();

			if (output.contains("Can I grow stevia in Madison?"))

				System.out.println("Test PASSED.");

			else
				System.out.println("Test FAILED.");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * integration test 2 include AE,DW,FD,BD
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test7() {
		try {
			PostReaderInterface list = new PostReaderDW();

			HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
			CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, list);

			// load the large.txt and find by post by key "dairy"
			TextUITester tester = new TextUITester("L\n ./data/large.txt\n P\n dairy \n\n Q \n");

			Scanner scan = new Scanner(System.in);

			CHSearchFrontendFD fd = new CHSearchFrontendFD(scan, backend);

			fd.runCommandLoop();

			String output = tester.checkOutput();

			if (output.contains("Meal")) {
				System.out.println("Test PASSED.");
			} else
				System.out.println("Test FAILED.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * test front-kend's implementation 1
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test8() {
		TextUITester tester = new TextUITester("T\ntest\n\nQ\n");

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
		PostReaderInterface list = new PostReaderDW();

		try (Scanner scan = new Scanner(System.in)) {

			CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, list);

			CHSearchFrontendFD frontend = new CHSearchFrontendFD(scan, backend);
			frontend.runCommandLoop();

			String output = tester.checkOutput();

			if (output.contains("List of words to search for:")) {
				System.out.println("Test PASSED.");
			} else
				System.out.println("Test FAILED.");
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	/**
	 * test front-kend's implementation 2
	 * 
	 * @return true if all test case pass
	 */
	public static boolean test9() {

		TextUITester tester = new TextUITester("A\n\nQ\n");

		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
		PostReaderInterface list = new PostReaderDW();

		try (Scanner scan = new Scanner(System.in)) {

			CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, list);

			CHSearchFrontendFD frontend = new CHSearchFrontendFD(scan, backend);
			frontend.runCommandLoop();

			String output = tester.checkOutput();

			if (output.contains("Didn't recognize that command")) {
				System.out.println("Test PASSED.");
			} else
				System.out.println("Test FAILED.");
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println("BD Individual Test 1:" + test1());
		System.out.println("BD Individual Test 2:" + test2());
		System.out.println("BD Individual Test 3:" + test3());
		System.out.println("BD Individual Test 4:" + test4());
		System.out.println("BD Individual Test 5:" + test5());

		System.out.println("Integration Test 6:" + test6());
		System.out.println("Integration Test 7:" + test7());

		System.out.println("ROLE Partner (FD) Test 8:" + test8());
		System.out.println("ROLE Partner (FD) Test 9:" + test9());

	}

}
