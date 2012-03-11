package org.jtalks.tests.jcommune.tests.post;

import org.jtalks.tests.jcommune.pages.PostPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.StringHelp;

import static org.jtalks.tests.jcommune.Assert.Exsistence.assertNotEmptyCollection;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.clickOnRandomBranch;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.createAnswerForTest;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.createTopicForTest;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.driver;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.logOut;
import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.signIn;

/**
 * @autor masyan
 */
public class JC72PagingShowPagesButton {
	PostPage postPage;


	@BeforeMethod
	@Parameters({"app-url", "uUsername", "uPassword"})
	public void setupCase(String appUrl, String username, String password) {
		driver.get(appUrl);
		signIn(username, password);
		clickOnRandomBranch();
		createTopicForTest();
		createAnswerForTest(StringHelp.getRandomString(10));
		createAnswerForTest(StringHelp.getRandomString(10));
		createAnswerForTest(StringHelp.getRandomString(10));
		createAnswerForTest(StringHelp.getRandomString(10));
		createAnswerForTest(StringHelp.getRandomString(10));
		createAnswerForTest(StringHelp.getRandomString(10));
		postPage = new PostPage(driver);
		postPage.getShowAllButton().click();

	}

	@AfterMethod
	@Parameters({"app-url"})
	public void destroy(String appUrl) {
		logOut(appUrl);
	}

	@Test
	public void pagingShowAllButtonTest() {
		postPage.getShowPagesButton().click();
		assertNotEmptyCollection(postPage.getPagesButtons());
	}
}
